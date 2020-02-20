package com.jameshayward.marvelapp.ui.di

import android.app.Application
import com.jameshayward.marvelapp.ui.MarvelApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        UiModule::class,
        PresentationModule::class,
        DomainModule::class,
        DataModule::class,
        SessionDataModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<MarvelApp> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }
}
