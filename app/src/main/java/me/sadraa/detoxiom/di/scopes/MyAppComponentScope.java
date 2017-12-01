package me.sadraa.detoxiom.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by sadra on 11/23/17.
 */
//Apparently @Singleton is just misleading us and It is'nt make just one instance
    //I'm not sure how it works but for making sure everything is work right we
    //create our annotation scope.
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface MyAppComponentScope {
}
