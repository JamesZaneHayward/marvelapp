package com.jameshayward.marvelapp.domain.common.usecase

import io.reactivex.Observable
import io.reactivex.Scheduler

abstract class UseCaseObservable<T, in Params>(
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) {

    fun execute(params: Params): Observable<T> = buildUseCaseObservable(params)
        .subscribeOn(subscribeScheduler)
        .observeOn(observeScheduler)

    protected abstract fun buildUseCaseObservable(params: Params): Observable<T>

    // No params
    object None
}
