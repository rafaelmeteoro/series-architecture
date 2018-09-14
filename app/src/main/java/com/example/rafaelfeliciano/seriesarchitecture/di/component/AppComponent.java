package com.example.rafaelfeliciano.seriesarchitecture.di.component;

import android.app.Application;

import com.example.rafaelfeliciano.seriesarchitecture.App;
import com.example.rafaelfeliciano.seriesarchitecture.di.modules.ActivityModule;
import com.example.rafaelfeliciano.seriesarchitecture.di.modules.AppModule;
import com.example.rafaelfeliciano.seriesarchitecture.di.modules.FragmentModule;
import com.example.rafaelfeliciano.seriesarchitecture.di.modules.NetServicesModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        FragmentModule.class,
        NetServicesModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        Builder appModule(AppModule appModule);

        AppComponent build();
    }

    void inject(App app);
}
