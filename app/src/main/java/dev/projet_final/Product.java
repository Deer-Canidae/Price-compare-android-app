package dev.projet_final;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Defines the properties of a product
 */
@Entity(tableName = "product")
public class Product implements Serializable {
    /**
     * The unique product id
     */
    @PrimaryKey(autoGenerate = true)
    public long productId;

    /**
     * The name of the product
     */
    @ColumnInfo(name = "name")
    public final String name;

    /**
     * The price of a product
     */
    @ColumnInfo(name = "price")
    public final float price;

    /**
     * The id of the product's unit
     */
    @ColumnInfo(name = "unitId")
    public final int unitId;

    /**
     * The product's POSIX timestamp
     */
    @ColumnInfo(name = "timestamp")
    public final long timestamp;

    public Product(String name, float price, int unitId, long timestamp) {
        this.name = name;
        this.price = price;
        this.unitId = unitId;
        this.timestamp = timestamp;
    }
}
