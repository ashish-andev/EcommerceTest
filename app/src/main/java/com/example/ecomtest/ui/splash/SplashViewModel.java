package com.example.ecomtest.ui.splash;

import com.example.ecomtest.data.DataManager;
import com.example.ecomtest.ui.base.BaseViewModel;
import com.example.ecomtest.utils.rx.SchedulerProvider;

public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void checkUserSession() {
        //TODO check user session
        getNavigator().openMainActivity();
    }
}
