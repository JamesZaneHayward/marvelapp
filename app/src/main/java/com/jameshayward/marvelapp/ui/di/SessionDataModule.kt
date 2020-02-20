package com.jameshayward.marvelapp.ui.di

import android.content.Context
import android.content.SharedPreferences
import com.jameshayward.marvelapp.common.di.SessionDataPreferences
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object SessionDataModule {

    private const val MARVEL_APP_SESSION_PREFERENCES: String = "session_preferences"

    @Provides
    @Reusable
    @JvmStatic
    @SessionDataPreferences
    fun providePreferences(@Singleton context: Context): SharedPreferences =
        context.getSharedPreferences(MARVEL_APP_SESSION_PREFERENCES, Context.MODE_PRIVATE)
}
