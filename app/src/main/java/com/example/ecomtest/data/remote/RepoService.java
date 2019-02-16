package com.example.ecomtest.data.remote;

import com.example.ecomtest.data.model.api.ProductResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RepoService {
    @GET("bins/i5qto")
    Single<ProductResponse> getAllProducts();

}
