package com.example.ecomtest.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ecomtest.data.model.api.ProductResponse;
import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.ui.cart.MyCartAdapter;
import com.example.ecomtest.ui.dashboard.ProductAdapter;
import com.example.ecomtest.ui.orders.MyOrdersAdapter;

import java.util.List;


public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"productAdapter"})
    public static void addProductItems(RecyclerView recyclerView, List<ProductResponse.Product> products) {
        ProductAdapter adapter = (ProductAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(products);
        }
    }


    @BindingAdapter({"cartAdapter"})
    public static void addCartItems(RecyclerView recyclerView, List<CartItem> cartItems) {
        MyCartAdapter adapter = (MyCartAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(cartItems);
        }
    }


    @BindingAdapter({"orderAdapter"})
    public static void addOrderItems(RecyclerView recyclerView, List<CartItem> cartItems) {
        MyOrdersAdapter adapter = (MyOrdersAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(cartItems);
        }
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
