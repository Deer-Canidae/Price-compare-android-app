package dev.projet_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class NewProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView outUnit;
    private Unit inputUnit, outputUnit;
    private EditText priceEdit, mvEdit, nameEdit, packageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        //Name
        setTitle(R.string.np_prompt);

        // Spinner
        Spinner spinner = findViewById(R.id.np_unit_spinner);

        ArrayAdapter<Unit> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Unit.getUnitList(getApplicationContext()));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        //Confirm
        Button button = findViewById(R.id.np_ok);
        button.setOnClickListener(this::OnButtonClicked);

        //Output Unit
        this.outUnit = findViewById(R.id.np_output_unit);

        //EditText(s)
        this.nameEdit = findViewById(R.id.np_name);
        this.mvEdit = findViewById(R.id.np_mass_volume);
        this.packageEdit = findViewById(R.id.np_package_deal);
        this.priceEdit = findViewById(R.id.np_price);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        inputUnit = (Unit) parent.getItemAtPosition(position);
        outputUnit = Unit.getMatchingSiUnit(inputUnit, getApplicationContext());
        this.outUnit.setText(outputUnit.getUnitStringRes());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressWarnings("unused")
    private void OnButtonClicked(View v) {
        final String name = this.nameEdit.getText().toString();
        if (name.isBlank()) {
            Snackbar.make(this.nameEdit, R.string.np_blank_name_error, Snackbar.LENGTH_SHORT).show();
            return;
        }
        float price;
        try {
            price = Float.parseFloat(this.priceEdit.getText().toString());
            if (price <= 0d)
                throw new NegativeOrNullException();
        } catch (NumberFormatException e) {
            Snackbar.make(this.priceEdit, R.string.np_invalid_price, Snackbar.LENGTH_SHORT).show();
            return;
        } catch (NegativeOrNullException e) {
            Snackbar.make(this.priceEdit, R.string.np_negative_or_null, Snackbar.LENGTH_SHORT).show();
            return;
        }
        float mv;
        try {
            mv = Float.parseFloat(this.mvEdit.getText().toString());
            if (mv <= 0d)
                throw new NegativeOrNullException();
        } catch (NumberFormatException e) {
            Snackbar.make(this.mvEdit, R.string.np_invalid_mv, Snackbar.LENGTH_SHORT).show();
            return;
        } catch (NegativeOrNullException e) {
            Snackbar.make(this.mvEdit, R.string.np_negative_or_null, Snackbar.LENGTH_SHORT).show();
            return;
        }
        float packageDeal;
        String packageString = this.packageEdit.getText().toString();
        if (packageString.isBlank())
            packageDeal = 1f;
        else {
            try {
                packageDeal = Float.parseFloat(packageString);
                if (packageDeal <= 0)
                    throw new NegativeOrNullException();
            } catch (NumberFormatException e) {
                Snackbar.make(this.packageEdit, R.string.np_invalid_package, Snackbar.LENGTH_SHORT).show();
                return;
            } catch (NegativeOrNullException e) {
                Snackbar.make(this.packageEdit, R.string.np_negative_or_null, Snackbar.LENGTH_SHORT).show();
                return;
            }
        }
        final float conversionFactor = this.inputUnit.getConversionFactor();
        final float finalPrice = price / mv * packageDeal * conversionFactor;
        Product prod = new Product(name, finalPrice, this.outputUnit.getUnitId(), new Date().getTime() / 1000L);
        Intent intent = new Intent();
        intent.putExtra("product", prod);
        setResult(RESULT_OK, intent);
        finish();
    }
}