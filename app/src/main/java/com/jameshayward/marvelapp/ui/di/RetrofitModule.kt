package com.jameshayward.marvelapp.ui.di

import com.jameshayward.marvelapp.BuildConfig
import com.jameshayward.marvelapp.common.di.LoggingInterceptor
import com.jameshayward.marvelapp.common.di.MoshiConverter
import com.jameshayward.marvelapp.common.di.RxJavaCallAdapter
import com.jameshayward.marvelapp.data.comicslist.ComicsListDataSource
import com.jameshayward.marvelapp.data.randomhero.RandomHeroDataSource
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object RetrofitModule {

    @Provides @LoggingInterceptor
    @Reusable @JvmStatic
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC }

    @Provides @Reusable @JvmStatic
    fun provideOkHttpClient(
        @LoggingInterceptor loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides @RxJavaCallAdapter
    @Reusable @JvmStatic
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides @MoshiConverter
    @Reusable @JvmStatic
    fun provideConverterFactory(): Converter.Factory = MoshiConverterFactory.create()

    @Provides @Reusable @JvmStatic
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        @RxJavaCallAdapter callAdapterFactory: CallAdapter.Factory,
        @MoshiConverter converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .baseUrl("http://gateway.marvel.com")
            .build()
    }

    @Provides @Reusable @JvmStatic
    fun provideRandomHeroDataSource(retrofit: Retrofit): RandomHeroDataSource =
        retrofit.create(RandomHeroDataSource::class.java)

    @Provides @Reusable @JvmStatic
    fun provideComicsListDataSource(retrofit: Retrofit): ComicsListDataSource =
        retrofit.create(ComicsListDataSource::class.java)
}
