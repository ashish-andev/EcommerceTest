package com.example.ecomtest.ui.dashboard;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.ecomtest.data.DataManager;
import com.example.ecomtest.data.model.api.ProductResponse;
import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.ui.base.BaseViewModel;
import com.example.ecomtest.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class DashboardViewModel extends BaseViewModel<DashboardNavigator> {

    public final ObservableList<ProductResponse.Product> productObservableArrayList = new ObservableArrayList<>();

    private final MutableLiveData<List<ProductResponse.Product>> productListLiveData;

    DashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        productListLiveData = new MutableLiveData<>();
        fetchProducts();
    }

    void addItemsToList(List<ProductResponse.Product> products) {
        productObservableArrayList.clear();
        productObservableArrayList.addAll(products);
    }

    private void fetchProducts() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getAllProductsApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ProductResponse>() {
                    @Override
                    public void accept(ProductResponse productResponse) {
                        if (productResponse != null && productResponse.products != null) {
                            productListLiveData.setValue(productResponse.products);
                        }
                        setIsLoading(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        setIsLoading(false);
                        getNavigator().handleError(throwable);
                    }
                }));
    }

    MutableLiveData<List<ProductResponse.Product>> getProductListLiveData() {
        return productListLiveData;
    }

    public ObservableList<ProductResponse.Product> getProductObservableList() {
        return productObservableArrayList;
    }

    void addToCart(ProductResponse.Product product) {
        CartItem cartItem = new CartItem();
        cartItem.price = product.price;
        cartItem.rating = String.valueOf(product.rating);
        cartItem.name = product.name;
        cartItem.imageUrl = product.imageUrl;
        cartItem.type = 1;
        getCompositeDisposable()
                .add(getDataManager()
                        .addToCart(cartItem)
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) {

                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {

                            }
                        }));
    }
}
