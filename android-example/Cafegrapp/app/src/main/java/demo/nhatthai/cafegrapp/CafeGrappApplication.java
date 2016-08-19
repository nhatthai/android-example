package demo.nhatthai.cafegrapp;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by nhatthai on 5/12/16.
 */
public class CafeGrappApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // initalize Calligraphy fonts
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/TitilliumWeb-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }
}
