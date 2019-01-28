package com.thanh.tran.structure.di.component;

import android.content.Context;

import com.thanh.tran.structure.MainActivity;
import com.thanh.tran.structure.MyApplication;
import com.thanh.tran.structure.di.module.ContextModule;
import com.thanh.tran.structure.di.module.RetrofitModule;
import com.thanh.tran.structure.di.qualifiers.ApplicationContext;
import com.thanh.tran.structure.di.retrofit.APIInterface;
import com.thanh.tran.structure.di.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {
    public APIInterface getApiInterface();

    @ApplicationContext
    public Context getContext();

    void inject(MyApplication myApplication);

    void inject(MainActivity mainActivity);
}
