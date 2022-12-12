package dev.projet_final;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.proto.ProtoOutputStream;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class NewProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        // Spinner
        spinner = findViewById(R.id.spinner_units);

        ArrayAdapter<Unit> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Unit.getUnitList(getApplicationContext()));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        //Confirm
        Button button = findViewById(R.id.product_confirm);
        button.setOnClickListener(view -> {
            Product prod = new Product("test", 12f, Unit.PER_KILO, new Date().getTime() / 1000L);
            Intent intent = new Intent();
            intent.putExtra("product", prod);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Unit unit = (Unit)parent.getItemAtPosition(position);

        Toast.makeText(this,String.format(Locale.getDefault(),"%d : %s", unit.getUnitId(), unit.toString()) , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}