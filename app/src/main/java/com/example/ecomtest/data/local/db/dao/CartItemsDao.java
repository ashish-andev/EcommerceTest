package com.example.ecomtest.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.ecomtest.data.model.db.CartItem;

import java.util.List;

@Dao
public interface CartItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CartItem cartItem);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CartItem> cartItems);

    @Query("UPDATE CartItem SET type=:type WHERE id = :id")
    void updateType(int id, int type);

    @Query("SELECT * FROM CartItem")
    List<CartItem> loadAll();

    @Query("SELECT * FROM CartItem WHERE type = :mType")
    List<CartItem> loadAllByType(int mType);
}
