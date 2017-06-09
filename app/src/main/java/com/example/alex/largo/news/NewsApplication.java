package com.example.alex.largo.news;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class NewsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
    }

}
