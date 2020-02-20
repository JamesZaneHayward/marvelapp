package com.jameshayward.marvelapp.data.comicslist

import com.jameshayward.marvelapp.BuildConfig
import com.jameshayward.marvelapp.common.Utils
import com.jameshayward.marvelapp.common.di.IoScheduler
import com.jameshayward.marvelapp.common.di.UiScheduler
import com.jameshayward.marvelapp.domain.comic.Comic
import com.jameshayward.marvelapp.domain.comicslist.repository.ComicsListRepositoryInterface
import io.reactivex.Observable
import io.reactivex.Scheduler
import java.util.*
import javax.inject.Inject

class ComicsListRepository @Inject constructor(
    private val comicDataSource: ComicsListDataSource
) : ComicsListRepositoryInterface {

    override fun observeComics(): Observable<List<Comic>> {
        val timestamp = Date().time

        val hash = Utils.md5(timestamp.toString() + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY)

        return comicDataSource
            .fetchComicsList("100", timestamp.toString(), BuildConfig.MARVEL_PUBLIC_KEY, hash)
            .map {
                it.data.results.toList()
            }
    }
}
