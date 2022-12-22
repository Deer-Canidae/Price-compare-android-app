package dev.projet_final;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

public class MyViewModel extends AndroidViewModel {

    private final LiveData<List<Product>> productLiveData;
    private final ProductDao dao;

    public MyViewModel(@NonNull Application application) {
        super(application);
        ProductDatabase productDatabase = ProductDatabase.getDbInstance(application.getApplicationContext());
        dao = productDatabase.productDao();
        productLiveData = dao.getAll();
    }

    public LiveData<List<Product>> getProductLiveData() {
        return productLiveData;
    }

    public void addProduct(Product product) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(product));
    }

    public void deleteProduct(Product product) {
        Executors.newSingleThreadExecutor().execute(() -> dao.delete(product));
    }

    public void deleteAll() {
        Executors.newSingleThreadExecutor().execute(dao::deleteAll);
    }
}
