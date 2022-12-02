package dev.projet_final;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private final ProductDatabase productDatabse;
    private final LiveData<List<Product>> productLiveData;

    public MyViewModel(@NonNull Application application) {
        super(application);
        productDatabse = Room.databaseBuilder(application.getApplicationContext(), ProductDatabase.class, "product-database").build();
        ProductDao dao = productDatabse.productDao();
        productLiveData = dao.getAll();
    }

    public LiveData<List<Product>> getProductLiveData() {
        return productLiveData;
    }
}
