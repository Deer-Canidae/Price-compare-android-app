package dev.projet_final;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        rc.setLayoutManager(new LinearLayoutManager(null, RecyclerView.VERTICAL, false));

        //Button setup
        Button button = findViewById(R.id.add_item_button);
        button.setOnClickListener(this::newItem);

        newProduct = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() != RESULT_OK)
                return;
             Product product = (Product)result.getData().getSerializableExtra("product");
             viewModel.addProduct(product);
        });
    }

    private void update(List<Product> productList) {
        rc.setAdapter(new ProductListAdapter(productList, getApplicationContext()));
    }

    private void newItem(View view) {


        newProduct.launch(new Intent(this, NewProductActivity.class));
    }
}