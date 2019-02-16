package com.example.ecomtest.data.remote;


import com.example.ecomtest.data.model.api.ProductResponse;

import io.reactivex.Single;

public interface ApiHelper {
    Single<ProductResponse> getAllProductsApiCall();

}
