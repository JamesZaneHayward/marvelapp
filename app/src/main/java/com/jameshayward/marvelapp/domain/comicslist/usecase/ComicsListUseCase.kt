package com.jameshayward.marvelapp.domain.comicslist.usecase

import com.jameshayward.marvelapp.common.di.IoScheduler
import com.jameshayward.marvelapp.common.di.UiScheduler
import com.jameshayward.marvelapp.domain.comic.Comic
import com.jameshayward.marvelapp.domain.comicslist.repository.ComicsListRepositoryInterface
import com.jameshayward.marvelapp.domain.common.usecase.UseCaseSingle
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetComicsListUseCase @Inject constructor(
    private val comicsListRepositoryInterface: ComicsListRepositoryInterface,
    @IoScheduler subscriberScheduler: Scheduler,
    @UiScheduler observeScheduler: Scheduler
) : UseCaseSingle<List<Comic>, UseCaseSingle.None>(subscriberScheduler, observeScheduler) {
    override fun buildUseCaseSingle(params: None): Single<List<Comic>> {
        return comicsListRepositoryInterface.getComics()
    }
}
