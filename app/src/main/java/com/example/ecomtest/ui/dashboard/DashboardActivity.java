package com.example.ecomtest.ui.dashboard;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ecomtest.BR;
import com.example.ecomtest.R;
import com.example.ecomtest.data.model.api.ProductResponse;
import com.example.ecomtest.databinding.ActivityDashboardBinding;
import com.example.ecomtest.ui.base.BaseActivity;
import com.example.ecomtest.ui.cart.MyCartActivity;
import com.example.ecomtest.ui.orders.MyOrdersActivity;

import java.util.List;

import javax.inject.Inject;

public class DashboardActivity extends BaseActivity<ActivityDashboardBinding, DashboardViewModel>
        implements DashboardNavigator, ProductAdapter.ProductAdapterListener {

    @Inject
    ProductAdapter mProductAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ActivityDashboardBinding mBinding;
    private DashboardViewModel mMainViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_dashboard;
    }

    @Override
    public DashboardViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DashboardViewModel.class);
        return mMainViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void openMyCart() {
        startActivity(new Intent(DashboardActivity.this, MyCartActivity.class));
    }

    @Override
    public void openMyOrder() {
        startActivity(new Intent(DashboardActivity.this, MyOrdersActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);
        setUp();
        subscribeToLiveData();
    }

    private void setUp() {
        mProductAdapter.setListener(this);
        setSupportActionBar(mBinding.toolbar);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mBinding.recyclerView.setLayoutManager(mLayoutManager);
        mBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        mBinding.recyclerView.setAdapter(mProductAdapter);
    }

    private void subscribeToLiveData() {
        mMainViewModel.getProductListLiveData().observe(this, new Observer<List<ProductResponse.Product>>() {
            @Override
            public void onChanged(@Nullable List<ProductResponse.Product> productList) {
                mMainViewModel.addItemsToList(productList);
            }
        });
    }

    @Override
    public void onAddToCartClick(ProductResponse.Product product) {
        mMainViewModel.addToCart(product);
        Toast.makeText(DashboardActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_my_cart:
                openMyCart();
                return true;
            case R.id.action_my_order:
                openMyOrder();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
