package com.thanh.tran.structure;

import android.app.Application;

import com.thanh.tran.structure.di.component.ApplicationComponent;
import com.thanh.tran.structure.di.component.DaggerApplicationComponent;
import com.thanh.tran.structure.di.module.ContextModule;
import com.thanh.tran.structure.di.module.RetrofitModule;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .retrofitModule(new RetrofitModule())
                .contextModule(new ContextModule(this)).build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
