package com.example.ecomtest.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.ecomtest.data.local.db.dao.CartItemsDao;
import com.example.ecomtest.data.model.db.CartItem;


@Database(entities = {CartItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CartItemsDao cartItemsDao();

    @Override
    public void clearAllTables() {

    }
}
