package com.thanh.tran.structure.di.qualifiers;

import javax.inject.Qualifier;

/*
 * a Context can be either of the Activity or the Application so Qualifier help you clear context
 * that you used
 * */

@Qualifier
public @interface ApplicationContext {
}
