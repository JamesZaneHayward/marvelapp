package com.jameshayward.marvelapp.ui

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import com.jameshayward.marvelapp.ui.di.DaggerAppComponent

open class MarvelApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)
}
