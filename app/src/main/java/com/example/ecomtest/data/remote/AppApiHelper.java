package com.example.ecomtest.data.remote;

import com.example.ecomtest.data.model.api.ProductResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    private RepoService repoService;

    @Inject
    public AppApiHelper(RepoService repoService) {
        this.repoService = repoService;
    }


    @Override
    public Single<ProductResponse> getAllProductsApiCall() {
        return repoService.getAllProducts();
    }


}
