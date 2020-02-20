package com.jameshayward.marvelapp.ui.di

import androidx.lifecycle.ViewModelProvider
import com.jameshayward.marvelapp.presentation.common.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
