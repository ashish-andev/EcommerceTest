package com.example.ecomtest.data;

import android.content.Context;

import com.example.ecomtest.data.local.db.DbHelper;
import com.example.ecomtest.data.local.prefs.PreferencesHelper;
import com.example.ecomtest.data.model.api.ProductResponse;
import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.data.remote.ApiHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;


@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;


    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }


    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public Single<ProductResponse> getAllProductsApiCall() {
        return mApiHelper.getAllProductsApiCall();
    }

    @Override
    public Observable<Boolean> addToCart(CartItem cartItem) {
        return mDbHelper.addToCart(cartItem);
    }

    @Override
    public Observable<Boolean> addToPrevOrders(int id) {
        return mDbHelper.addToPrevOrders(id);
    }

    @Override
    public Observable<List<CartItem>> getCartItems() {
        return mDbHelper.getCartItems();
    }

    @Override
    public Observable<List<CartItem>> getPrevOrders() {
        return mDbHelper.getPrevOrders();
    }
}
