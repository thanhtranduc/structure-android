package com.thanh.tran.structure.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/*
 * define where all those Component would be used,
 * in this application there are many scope that be used
 * */

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
