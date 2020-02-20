package com.jameshayward.marvelapp.ui.di

import androidx.lifecycle.ViewModel
import com.jameshayward.marvelapp.presentation.randomhero.RandomHeroViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class RandomHeroModule {

    @Binds
    @IntoMap
    @ViewModelKey(RandomHeroViewModel::class)
    abstract fun bindRandomHeroViewModel(viewModel: RandomHeroViewModel): ViewModel
}
