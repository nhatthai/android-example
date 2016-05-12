package demo.nhatthai.cafegrapp;

import android.app.Application;

import demo.nhatthai.cafegrapp.module.ApplicationModule;
import demo.nhatthai.cafegrapp.component.ApplicationComponent;
import demo.nhatthai.cafegrapp.component.DaggerApplicationComponent;

/**
 * Created by nhatthai on 5/12/16.
 */
public class CafeGrappApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
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
