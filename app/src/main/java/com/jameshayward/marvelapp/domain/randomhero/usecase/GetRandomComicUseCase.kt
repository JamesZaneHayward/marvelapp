package com.jameshayward.marvelapp.domain.randomhero.usecase

import com.jameshayward.marvelapp.common.di.IoScheduler
import com.jameshayward.marvelapp.common.di.UiScheduler
import com.jameshayward.marvelapp.domain.common.usecase.UseCaseSingle
import com.jameshayward.marvelapp.domain.hero.Hero
import com.jameshayward.marvelapp.domain.randomhero.repository.RandomHeroRepositoryInterface
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetRandomHeroUseCase @Inject constructor(
    private val randomHeroRepositoryInterface: RandomHeroRepositoryInterface,
    @IoScheduler subscriberScheduler: Scheduler,
    @UiScheduler observeScheduler: Scheduler
) : UseCaseSingle<Hero, String>(subscriberScheduler, observeScheduler) {
    override fun buildUseCaseSingle(params: String): Single<Hero> {
        return randomHeroRepositoryInterface.getRandomHero(params)
    }
}
