package com.jameshayward.marvelapp.domain.randomhero.repository

import com.jameshayward.marvelapp.domain.hero.Hero
import io.reactivex.Single

interface RandomHeroRepositoryInterface {
    fun getRandomHero(heroId: String): Single<Hero>
}
