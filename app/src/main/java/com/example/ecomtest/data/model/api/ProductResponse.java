package com.example.ecomtest.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {
    @SerializedName("products")
    @Expose
    public List<Product> products = null;

    public class Product {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("price")
        @Expose
        public String price;
        @SerializedName("image_url")
        @Expose
        public String imageUrl;
        @SerializedName("rating")
        @Expose
        public float rating;

    }
}