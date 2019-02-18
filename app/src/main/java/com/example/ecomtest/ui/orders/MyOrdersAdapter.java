package com.example.ecomtest.ui.orders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.databinding.ItemOrdersBinding;
import com.example.ecomtest.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MyOrdersAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<CartItem> mCartList;

    public MyOrdersAdapter(ArrayList<CartItem> mCartList) {
        this.mCartList = mCartList;
    }

    @Override
    public int getItemCount() {
        return mCartList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrdersBinding ordersBinding = ItemOrdersBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemViewHolder(ordersBinding);
    }

    public void addItems(List<CartItem> productList) {
        mCartList.addAll(productList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mCartList.clear();
    }

    public class ItemViewHolder extends BaseViewHolder {

        private ItemOrdersBinding mBinding;

        public ItemViewHolder(ItemOrdersBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            mBinding.setCartItem(mCartList.get(position));
            mBinding.executePendingBindings();
        }
    }
}