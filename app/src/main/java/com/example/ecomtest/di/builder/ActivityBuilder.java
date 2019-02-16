package com.example.ecomtest.di.builder;


import com.example.ecomtest.ui.cart.MyCartActivity;
import com.example.ecomtest.ui.cart.MyCartActivityModule;
import com.example.ecomtest.ui.dashboard.DashboardActivity;
import com.example.ecomtest.ui.dashboard.DashboardActivityModule;
import com.example.ecomtest.ui.orders.MyOrdersActivity;
import com.example.ecomtest.ui.orders.MyOrdersActivityModule;
import com.example.ecomtest.ui.splash.SplashActivity;
import com.example.ecomtest.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();


    @ContributesAndroidInjector(modules = DashboardActivityModule.class)
    abstract DashboardActivity bindDashboardActivity();


    @ContributesAndroidInjector(modules = MyCartActivityModule.class)
    abstract MyCartActivity bindMyCartActivity();

    @ContributesAndroidInjector(modules = MyOrdersActivityModule.class)
    abstract MyOrdersActivity bindMyOrdersActivity();

}
