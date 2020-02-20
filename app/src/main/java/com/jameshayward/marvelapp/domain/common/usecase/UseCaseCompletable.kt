package com.jameshayward.marvelapp.domain.common.usecase

import io.reactivex.Completable
import io.reactivex.Scheduler


abstract class UseCaseCompletable<in Params>(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    fun execute(params: Params): Completable = buildUseCaseCompletable(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(observeScheduler)

    protected abstract fun buildUseCaseCompletable(params: Params): Completable

    // No params
    object None

}
