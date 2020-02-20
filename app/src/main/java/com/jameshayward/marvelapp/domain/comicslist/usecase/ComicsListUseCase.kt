package com.jameshayward.marvelapp.domain.comicslist.usecase

import com.jameshayward.marvelapp.common.di.IoScheduler
import com.jameshayward.marvelapp.common.di.UiScheduler
import com.jameshayward.marvelapp.domain.common.usecase.UseCaseObservable
import com.jameshayward.marvelapp.domain.comic.Comic
import com.jameshayward.marvelapp.domain.comicslist.repository.ComicsListRepositoryInterface
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetComicsListUseCase @Inject constructor(
    private val comicsListRepositoryInterface: ComicsListRepositoryInterface,
    @IoScheduler subscriberScheduler: Scheduler,
    @UiScheduler observeScheduler: Scheduler
) : UseCaseObservable<List<Comic>, UseCaseObservable.None>(subscriberScheduler, observeScheduler) {
    override fun buildUseCaseObservable(params: None): Observable<List<Comic>> {
        return comicsListRepositoryInterface.observeComics()
    }
}
