package com.example.ecomtest.data.model.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "cartitem")
public class CartItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String imageUrl;
    public String price;
    public String rating;
    public int type;//1 = in cart, 2 = prev order
}
