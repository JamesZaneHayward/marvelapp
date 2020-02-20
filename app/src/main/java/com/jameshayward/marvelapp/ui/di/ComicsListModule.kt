package com.jameshayward.marvelapp.ui.di

import androidx.lifecycle.ViewModel
import com.jameshayward.marvelapp.presentation.comicslist.ComicsListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
abstract class ComicsListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ComicsListViewModel::class)
    abstract fun bindComicsListViewModel(viewModel: ComicsListViewModel): ViewModel
}
