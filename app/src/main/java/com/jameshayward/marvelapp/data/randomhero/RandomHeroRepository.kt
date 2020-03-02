package com.jameshayward.marvelapp.data.randomhero

import com.jameshayward.marvelapp.BuildConfig
import com.jameshayward.marvelapp.common.Utils
import com.jameshayward.marvelapp.domain.hero.Hero
import com.jameshayward.marvelapp.domain.randomhero.repository.RandomHeroRepositoryInterface
import io.reactivex.Single
import java.util.Date
import javax.inject.Inject

class RandomHeroRepository @Inject constructor(
    private val randomHeroDataSource: RandomHeroDataSource
) : RandomHeroRepositoryInterface {
    override fun getRandomHero(heroId: String): Single<Hero> {
        val timestamp = Date().time

        val hash =
            Utils.md5(timestamp.toString() + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY)

        return randomHeroDataSource
            .fetchRandomHero(heroId, timestamp.toString(), BuildConfig.MARVEL_PUBLIC_KEY, hash)
            .map {
                it.data.results[0]
            }
    }
}
