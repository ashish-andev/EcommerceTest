package com.example.ecomtest.ui.dashboard;


public interface DashboardNavigator {

    void handleError(Throwable throwable);

    void openMyCart();

    void openMyOrder();

}
