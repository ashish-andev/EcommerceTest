package com.example.ecomtest.ui.dashboard;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecomtest.data.model.api.ProductResponse;
import com.example.ecomtest.databinding.ItemProductBinding;
import com.example.ecomtest.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<ProductResponse.Product> mProductList;
    private ProductAdapterListener listener;

    public ProductAdapter(ArrayList<ProductResponse.Product> mProductList) {
        this.mProductList = mProductList;
    }

    public void setListener(ProductAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding productBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemViewHolder(productBinding);
    }

    public void addItems(List<ProductResponse.Product> productList) {
        mProductList.addAll(productList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mProductList.clear();
    }

    public interface ProductAdapterListener {

        void onAddToCartClick(ProductResponse.Product product);
    }

    public class ItemViewHolder extends BaseViewHolder implements View.OnClickListener {

        private ItemProductBinding mBinding;

        public ItemViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mBinding.btnAddToCart.setOnClickListener(this);
        }

        @Override
        public void onBind(int position) {
            mBinding.setProduct(mProductList.get(position));
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onAddToCartClick(mProductList.get(getAdapterPosition()));
            }
        }
    }

}