package com.jameshayward.marvelapp.ui.di

import com.jameshayward.marvelapp.common.di.IoScheduler
import com.jameshayward.marvelapp.common.di.UiScheduler
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
object DomainModule {

    @Provides
    @IoScheduler
    @Reusable
    @JvmStatic
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @UiScheduler
    @Reusable
    @JvmStatic
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
