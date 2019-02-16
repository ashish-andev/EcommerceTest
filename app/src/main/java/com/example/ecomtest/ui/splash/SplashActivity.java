package com.example.ecomtest.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ecomtest.BR;
import com.example.ecomtest.R;
import com.example.ecomtest.databinding.ActivitySplashBinding;
import com.example.ecomtest.ui.base.BaseActivity;
import com.example.ecomtest.ui.dashboard.DashboardActivity;

import javax.inject.Inject;


public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.splashViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        mSplashViewModel.checkUserSession();
    }
}
