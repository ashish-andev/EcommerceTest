package com.example.ecomtest.ui.cart;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.example.ecomtest.data.DataManager;
import com.example.ecomtest.data.model.db.CartItem;
import com.example.ecomtest.ui.base.BaseViewModel;
import com.example.ecomtest.utils.rx.SchedulerProvider;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MyCartViewModel extends BaseViewModel<MyCartNavigator> {

    public final ObservableList<CartItem> cartItemObservableArrayList = new ObservableArrayList<>();

    private final MutableLiveData<List<CartItem>> cartItemLiveData;

    MyCartViewModel(DataManager dataManager,
                    SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        cartItemLiveData = new MutableLiveData<>();
        fetchMyCart();
    }

    void addItemsToList(List<CartItem> cartItems) {
        cartItemObservableArrayList.clear();
        cartItemObservableArrayList.addAll(cartItems);
    }

    private void fetchMyCart() {
        setIsEmpty(false);
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getCartItems()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<CartItem>>() {
                    @Override
                    public void accept(List<CartItem> cartItems) {
                        if (cartItems != null) {
                            cartItemLiveData.setValue(cartItems);
                            if (cartItems.size() == 0) {
                                setIsEmpty(true);
                            }
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

    MutableLiveData<List<CartItem>> getCartItemLiveData() {
        return cartItemLiveData;
    }

    public ObservableList<CartItem> getCartItemObservableList() {
        return cartItemObservableArrayList;
    }

    void placeOrder(CartItem cartItem) {
        getCompositeDisposable()
                .add(getDataManager()
                        .addToPrevOrders(cartItem.id)
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
