package com.eigendaksh.vivid.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Ashutosh Purushottam on 02/04/18.
 * Eigendaksh Development Studio
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
