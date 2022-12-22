package dev.projet_final;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.jetbrains.annotations.NotNull;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao productDao();

    private static ProductDatabase dbInstance;

    @NotNull
    public static ProductDatabase getDbInstance(@NotNull Context appContext) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(appContext, ProductDatabase.class, "product-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
