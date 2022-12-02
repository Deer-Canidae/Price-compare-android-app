package dev.projet_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Mandatory super
        super.onCreate(savedInstanceState);

        // ViewModel setup
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //Dataset
        LiveData<List<Product>> productLiveData = viewModel.getProductLiveData();
        productLiveData.observe(this, this::update);

        // Content Setup
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.rc_main);
        rc.setLayoutManager(new LinearLayoutManager(null, RecyclerView.VERTICAL, false));

    }

    private void update(List<Product> productList) {
        rc.setAdapter(new ProductListAdapter(productList));
    }
}