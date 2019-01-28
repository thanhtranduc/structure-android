package com.thanh.tran.structure.di.module;

import android.content.Context;

import com.thanh.tran.structure.di.qualifiers.ApplicationContext;
import com.thanh.tran.structure.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }


    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
