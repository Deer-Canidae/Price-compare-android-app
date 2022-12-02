package dev.projet_final;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product ORDER BY timestamp DESC")
    LiveData<List<Product>> getAll();

    @Insert
    void insert(Product... products);

    @Delete
    void delete(Product product);
}
