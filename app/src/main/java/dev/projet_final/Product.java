package dev.projet_final;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Defines the properties of a product
 */
@Entity(tableName = "product")
public class Product {
    /**
     * The unique product id
     */
    @PrimaryKey(autoGenerate = true)
    public long productId;

    /**
     * The name of the product
     */
    @ColumnInfo(name = "name")
    public String name;

    /**
     * The price of a product
     */
    @ColumnInfo(name = "price")
    public float price;

    /**
     * The id of the product's unit
     */
    @ColumnInfo(name = "unitId")
    public int unitId;

    /**
     * The product's POSIX timestamp
     */
    @ColumnInfo(name = "timestamp")
    public long timestamp;
}
