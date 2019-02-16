package com.example.ecomtest.data.local.db;

import com.example.ecomtest.data.model.db.CartItem;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<Boolean> addToCart(CartItem cartItem);

    Observable<Boolean> addToPrevOrders(int id);

    Observable<List<CartItem>> getCartItems();

    Observable<List<CartItem>> getPrevOrders();
}
