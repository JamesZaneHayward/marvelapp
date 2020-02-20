package com.jameshayward.marvelapp.domain.randomhero.repository

import com.jameshayward.marvelapp.domain.hero.Hero
import io.reactivex.Observable

interface RandomHeroRepositoryInterface {
    fun getRandomHero(heroId: String): Observable<Hero>
}
