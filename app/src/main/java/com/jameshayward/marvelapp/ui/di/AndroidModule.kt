package com.jameshayward.marvelapp.ui.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.jameshayward.marvelapp.MainActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AndroidModule {
    @Provides
    fun provideMainActivityPendingIntent(
        @Singleton appContext: Context
    ): PendingIntent {
        val intent = Intent(appContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(appContext, 0, intent, 0)
    }
}
