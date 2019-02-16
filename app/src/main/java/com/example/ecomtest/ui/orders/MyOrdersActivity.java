package com.example.ecomtest.ui.orders;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.example.ecomtest.BR;
import com.example.ecomtest.R;
import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.databinding.ActivityMyOrdersBinding;
import com.example.ecomtest.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class MyOrdersActivity extends BaseActivity<ActivityMyOrdersBinding, MyOrdersViewModel>
        implements MyOrdersNavigator {

    @Inject
    MyOrdersAdapter mAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ActivityMyOrdersBinding mBinding;
    private MyOrdersViewModel mMyCartViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_orders;
    }

    @Override
    public MyOrdersViewModel getViewModel() {
        mMyCartViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MyOrdersViewModel.class);
        return mMyCartViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mMyCartViewModel.setNavigator(this);
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(mLayoutManager);
        mBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    private void subscribeToLiveData() {
        mMyCartViewModel.getCartItemLiveData().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> productList) {
                mMyCartViewModel.addItemsToList(productList);
            }
        });
    }
}
