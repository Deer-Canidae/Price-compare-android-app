package dev.projet_final;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RvClickHandler {

    private RecyclerView rc;
    private MyViewModel viewModel;
    private ActivityResultLauncher<Intent> newProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Mandatory super
        super.onCreate(savedInstanceState);

        // ViewModel setup
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Dataset
        LiveData<List<Product>> productLiveData = viewModel.getProductLiveData();
        productLiveData.observe(this, this::update);

        // Content Setup
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.rc_main);
        rc.setLayoutManager(new LinearLayoutManager(rc.getContext(), LinearLayoutManager.VERTICAL, false));

        //Button setup
        Button button = findViewById(R.id.add_item_button);
        button.setOnClickListener(this::newItem);

        // Product from activity
        newProduct = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() != RESULT_OK)
                return;
            assert result.getData() != null;
            Product product = (Product) result.getData().getSerializableExtra("product");
            viewModel.addProduct(product);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() != R.id.delete_all) return true;
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete)
                .setMessage(R.string.delete_all_prompt)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> viewModel.deleteAll())
                .setNegativeButton(android.R.string.no, null).show();
            return true;
    }

    private void update(List<Product> productList) {
        rc.setAdapter(new ProductListAdapter(productList, getApplicationContext(), this));
    }

    @SuppressWarnings("unused")
    private void newItem(View view) {
        newProduct.launch(new Intent(this, NewProductActivity.class));
    }

    public void deleteSelectedItem(Product product) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.delete)
                .setMessage(R.string.delete_prompt)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> viewModel.deleteProduct(product))
                .setNegativeButton(android.R.string.no, null).show();
    }
}