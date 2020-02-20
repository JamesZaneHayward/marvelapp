package com.jameshayward.marvelapp.ui.di

import com.jameshayward.marvelapp.data.comicslist.ComicsListRepository
import com.jameshayward.marvelapp.data.randomhero.RandomHeroRepository
import com.jameshayward.marvelapp.domain.comicslist.repository.ComicsListRepositoryInterface
import com.jameshayward.marvelapp.domain.randomhero.repository.RandomHeroRepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module(includes = [RetrofitModule::class])
abstract class DataModule {

    @Binds
    @Reusable
    abstract fun bindRandomHeroRepository(randomHeroRepository: RandomHeroRepository): RandomHeroRepositoryInterface

    @Binds
    @Reusable
    abstract fun bindComicsListRepository(comicsListRepository: ComicsListRepository): ComicsListRepositoryInterface
}
