package com.cd.jyf.studio;

import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

public class Kapplication   extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bundle metaData = getApplicationInfo().metaData;
    }

    //改变后 会调用onCreate
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
