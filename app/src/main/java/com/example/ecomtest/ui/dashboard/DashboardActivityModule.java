package com.example.ecomtest.ui.dashboard;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.ecomtest.ViewModelProviderFactory;
import com.example.ecomtest.data.DataManager;
import com.example.ecomtest.data.model.api.ProductResponse;
import com.example.ecomtest.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class DashboardActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(DashboardViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    DashboardViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new DashboardViewModel(dataManager, schedulerProvider);
    }

    @Provides
    ProductAdapter provideProductAdapter() {
        return new ProductAdapter(new ArrayList<ProductResponse.Product>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(DashboardActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
