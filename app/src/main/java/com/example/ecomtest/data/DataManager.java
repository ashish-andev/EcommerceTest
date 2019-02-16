package com.example.ecomtest.data;

import com.example.ecomtest.data.local.db.DbHelper;
import com.example.ecomtest.data.local.prefs.PreferencesHelper;
import com.example.ecomtest.data.remote.ApiHelper;

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

}
