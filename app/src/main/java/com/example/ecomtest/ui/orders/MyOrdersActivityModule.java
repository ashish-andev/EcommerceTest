package com.example.ecomtest.ui.orders;

import android.arch.lifecycle.ViewModelProvider;
import android.support.v7.widget.LinearLayoutManager;

import com.example.ecomtest.ViewModelProviderFactory;
import com.example.ecomtest.data.DataManager;
import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class MyOrdersActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MyOrdersViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MyOrdersViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MyOrdersViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MyOrdersAdapter provideProductAdapter() {
        return new MyOrdersAdapter(new ArrayList<CartItem>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MyOrdersActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
