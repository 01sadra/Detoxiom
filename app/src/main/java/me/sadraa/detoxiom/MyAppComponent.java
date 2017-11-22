package me.sadraa.detoxiom;

import dagger.Component;
import me.sadraa.detoxiom.di.CalligraphyModule;
import me.sadraa.detoxiom.di.NetworkModule;
import me.sadraa.detoxiom.network.QuoteClient;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sadra on 11/22/17.
 */

@Component(modules= {
        NetworkModule.class,
        CalligraphyModule.class
})
public interface MyAppComponent {

    QuoteClient getQCService();
    CalligraphyConfig getCalligraphyConfig();
}
