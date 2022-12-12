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

    public Product(String name, float price, int unitId, long timestamp) {
        this.name = name;
        this.price = price;
        this.unitId = unitId;
        this.timestamp = timestamp;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getUnitId() {
        return unitId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
