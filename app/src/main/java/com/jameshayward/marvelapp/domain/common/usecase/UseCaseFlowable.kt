package com.jameshayward.marvelapp.domain.common.usecase

import io.reactivex.Flowable
import io.reactivex.Scheduler


abstract class UseCaseFlowable<T, in Params>(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    fun execute(params: Params): Flowable<T> = buildUseCaseFlowable(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(observeScheduler)

    protected abstract fun buildUseCaseFlowable(params: Params): Flowable<T>

    // No params
    object None

}
