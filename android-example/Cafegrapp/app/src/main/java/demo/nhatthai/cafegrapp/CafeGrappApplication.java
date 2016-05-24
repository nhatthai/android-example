package demo.nhatthai.cafegrapp;

import android.app.Application;
import android.util.Log;

import demo.nhatthai.cafegrapp.module.ApplicationModule;
import demo.nhatthai.cafegrapp.component.ApplicationComponent;
import demo.nhatthai.cafegrapp.component.DaggerApplicationComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by nhatthai on 5/12/16.
 */
public class CafeGrappApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();

        // initalize Calligraphy fonts
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/TitilliumWeb-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    private void initializeInjector() {
        // Dagger%COMPONENT_NAME%
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }


}
