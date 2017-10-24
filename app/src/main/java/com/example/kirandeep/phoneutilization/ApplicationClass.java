package com.example.kirandeep.phoneutilization;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by abc on 18-10-2017.
 */

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration
                .Builder(getApplicationContext())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
