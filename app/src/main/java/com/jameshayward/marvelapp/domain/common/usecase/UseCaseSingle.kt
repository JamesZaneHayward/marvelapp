package com.jameshayward.marvelapp.domain.common.usecase

import io.reactivex.Scheduler
import io.reactivex.Single

abstract class UseCaseSingle<T, in Params>(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    fun execute(params: Params): Single<T> = buildUseCaseSingle(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(observeScheduler)

    protected abstract fun buildUseCaseSingle(params: Params): Single<T>

    // No params
    object None
}
