package com.jameshayward.marvelapp.ui.di

import com.jameshayward.marvelapp.data.comicslist.ComicsListDataSource
import com.jameshayward.marvelapp.data.comicslist.ComicsListRepository
import com.jameshayward.marvelapp.data.randomhero.RandomHeroDataSource
import com.jameshayward.marvelapp.data.randomhero.RandomHeroRepository
import com.jameshayward.marvelapp.domain.comicslist.repository.ComicsListRepositoryInterface
import com.jameshayward.marvelapp.domain.hero.Hero
import com.jameshayward.marvelapp.domain.hero.Thumbnail
import com.jameshayward.marvelapp.domain.randomhero.repository.RandomHeroRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable

@Module
object DataModule {

    @Provides
    @Reusable
    @JvmStatic
    fun providesComicsListRepository(): ComicsListRepositoryInterface {
        val comicsListRepository: ComicsListRepository = mockk()
        every { comicsListRepository.observeComics() } returns Observable.just(emptyList())
        return comicsListRepository
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideComicsListDataSource(): ComicsListDataSource = mockk()

    @Provides
    @Reusable
    @JvmStatic
    fun providesRandomHeroRepository(): RandomHeroRepositoryInterface {
        val randomHeroRepository: RandomHeroRepository = mockk()
        every { randomHeroRepository.getRandomHero(any()) } returns Observable.just(SPIDERMAN)
        return randomHeroRepository
    }

    @Provides
    @Reusable
    @JvmStatic
    fun provideRandomHeroDataSource(): RandomHeroDataSource = mockk()

    private val SPIDERMAN = Hero("Spider-Man", Thumbnail("", ""), "Peter Parker is just your friendly neighbourhood Spider-Man")
}
