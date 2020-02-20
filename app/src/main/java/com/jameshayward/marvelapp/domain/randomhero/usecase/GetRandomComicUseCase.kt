package com.jameshayward.marvelapp.domain.randomhero.usecase

import com.jameshayward.marvelapp.common.di.IoScheduler
import com.jameshayward.marvelapp.common.di.UiScheduler
import com.jameshayward.marvelapp.domain.common.usecase.UseCaseObservable
import com.jameshayward.marvelapp.domain.hero.Hero
import com.jameshayward.marvelapp.domain.randomhero.repository.RandomHeroRepositoryInterface
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetRandomHeroUseCase @Inject constructor(
    private val randomHeroRepositoryInterface: RandomHeroRepositoryInterface,
    @IoScheduler subscriberScheduler: Scheduler,
    @UiScheduler observeScheduler: Scheduler
) : UseCaseObservable<Hero, String>(subscriberScheduler, observeScheduler) {
    override fun buildUseCaseObservable(params: String): Observable<Hero> {
        return randomHeroRepositoryInterface.getRandomHero(params)
    }
}
