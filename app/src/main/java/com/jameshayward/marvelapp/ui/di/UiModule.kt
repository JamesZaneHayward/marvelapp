package com.jameshayward.marvelapp.ui.di

import com.jameshayward.marvelapp.common.di.FragmentScope
import com.jameshayward.marvelapp.ui.comicslist.ComicsListFragment
import com.jameshayward.marvelapp.ui.randomhero.RandomHeroFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidModule::class])
abstract class UiModule {

    @ContributesAndroidInjector(modules = [ComicsListModule::class]) @FragmentScope
    abstract fun comicsListFragment(): ComicsListFragment

    @ContributesAndroidInjector(modules = [RandomHeroModule::class]) @FragmentScope
    abstract fun randomHeroFragment(): RandomHeroFragment
}
