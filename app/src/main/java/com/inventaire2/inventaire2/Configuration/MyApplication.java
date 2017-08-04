package com.inventaire2.inventaire2.Configuration;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static io.realm.RealmConfiguration.*;

/**
 * Created by Fal on 01/08/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(config);
    }
}