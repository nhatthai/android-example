package demo.nhatthai.cafegrapp.module;

import android.app.Application;

import demo.nhatthai.cafegrapp.CafeGrappApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nhatthai on 5/12/16.
 */
@Module
public class ApplicationModule {

    private final CafeGrappApplication application;

    public ApplicationModule(CafeGrappApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public CafeGrappApplication provideMvpApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

}
