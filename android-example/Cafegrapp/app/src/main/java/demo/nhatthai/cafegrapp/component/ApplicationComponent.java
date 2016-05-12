package demo.nhatthai.cafegrapp.component;

import android.app.Application;

import demo.nhatthai.cafegrapp.CafeGrappApplication;
import demo.nhatthai.cafegrapp.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


/**
 * Created by nhatthai on 5/12/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application application();
    CafeGrappApplication cafeGrappApplication();
}