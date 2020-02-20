package com.jameshayward.marvelapp.domain.comicslist.repository

import com.jameshayward.marvelapp.domain.comic.Comic
import io.reactivex.Observable

interface ComicsListRepositoryInterface {
    fun observeComics(): Observable<List<Comic>>
}
