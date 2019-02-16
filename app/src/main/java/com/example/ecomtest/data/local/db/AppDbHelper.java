package com.example.ecomtest.data.local.db;


import com.example.ecomtest.data.model.db.CartItem;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> addToCart(final CartItem cartItem) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                mAppDatabase.cartItemsDao().insert(cartItem);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> addToPrevOrders(final int id) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                mAppDatabase.cartItemsDao().updateType(id, 2);
                return true;
            }
        });
    }

    @Override
    public Observable<List<CartItem>> getCartItems() {
        return Observable.fromCallable(new Callable<List<CartItem>>() {
            @Override
            public List<CartItem> call() {
                return mAppDatabase.cartItemsDao().loadAllByType(1);
            }
        });
    }

    @Override
    public Observable<List<CartItem>> getPrevOrders() {
        return Observable.fromCallable(new Callable<List<CartItem>>() {
            @Override
            public List<CartItem> call() {
                return mAppDatabase.cartItemsDao().loadAllByType(2);
            }
        });
    }

}
