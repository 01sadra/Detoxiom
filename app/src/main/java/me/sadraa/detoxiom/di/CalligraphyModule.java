package me.sadraa.detoxiom.di;

import dagger.Module;
import dagger.Provides;
import me.sadraa.detoxiom.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by sadra on 11/22/17.
 */
@Module
public class CalligraphyModule {
    @MyAppComponentScope
    @Provides
    CalligraphyConfig calligraphyConfig(){
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Vazir-Medium.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
