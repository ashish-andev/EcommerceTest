package com.example.ecomtest.ui.cart;

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
public class MyCartActivityModule {

    @Provides
    ViewModelProvider.Factory mainViewModelProvider(MyCartViewModel mainViewModel) {
        return new ViewModelProviderFactory<>(mainViewModel);
    }

    @Provides
    MyCartViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        return new MyCartViewModel(dataManager, schedulerProvider);
    }

    @Provides
    MyCartAdapter provideProductAdapter() {
        return new MyCartAdapter(new ArrayList<CartItem>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(MyCartActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
