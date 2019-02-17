package com.example.ecomtest.ui.dashboard;

import com.example.ecomtest.data.DataManager;
import com.example.ecomtest.data.model.api.ProductResponse;
import com.example.ecomtest.utils.rx.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DashboardViewModelTest {

    @Mock
    DashboardNavigator mDashboardCallback;
    @Mock
    DataManager mMockDataManager;
    private DashboardViewModel mDashboardViewModel;
    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mDashboardViewModel = new DashboardViewModel(mMockDataManager, testSchedulerProvider);
        mDashboardViewModel.setNavigator(mDashboardCallback);
    }

    @After
    public void tearDown() throws Exception {
        mTestScheduler = null;
        mDashboardViewModel = null;
        mDashboardCallback = null;
    }

    @Test
    public void testProductResponseSuccess() {
        ProductResponse productResponse = new ProductResponse();

//        doReturn(Single.just(productResponse))
//                .when(mMockDataManager)
//                .getAllProductsApiCall();

//        mDashboardViewModel.addToCart(productResponse.products.get(0));
        mTestScheduler.triggerActions();

        verify(mDashboardCallback).openMyCart();
    }
}
