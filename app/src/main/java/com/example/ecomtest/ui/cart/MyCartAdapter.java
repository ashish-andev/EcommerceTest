package com.example.ecomtest.ui.cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.databinding.ItemCartBinding;
import com.example.ecomtest.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MyCartAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<CartItem> mCartList;
    private ProductAdapterListener listener;

    public MyCartAdapter(ArrayList<CartItem> mCartList) {
        this.mCartList = mCartList;
    }

    public void setListener(ProductAdapterListener listener) {
        this.listener = listener;
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
        ItemCartBinding cartBinding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ItemViewHolder(cartBinding);
    }

    public void addItems(List<CartItem> productList) {
        mCartList.addAll(productList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mCartList.clear();
    }

    public interface ProductAdapterListener {

        void onAddToCartClick(CartItem cartItem);
    }

    public class ItemViewHolder extends BaseViewHolder implements View.OnClickListener {

        private ItemCartBinding mBinding;

        public ItemViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
            mBinding.btnCheckout.setOnClickListener(this);
        }

        @Override
        public void onBind(int position) {
            mBinding.setCartItem(mCartList.get(position));
            mBinding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                CartItem cartItem = mCartList.get(getAdapterPosition());
                mCartList.remove(getAdapterPosition());
                notifyDataSetChanged();
                listener.onAddToCartClick(cartItem);

            }
        }
    }

}