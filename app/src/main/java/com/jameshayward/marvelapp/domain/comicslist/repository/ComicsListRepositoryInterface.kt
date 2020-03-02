package com.jameshayward.marvelapp.domain.comicslist.repository

import com.jameshayward.marvelapp.domain.comic.Comic
import io.reactivex.Single

interface ComicsListRepositoryInterface {
    fun getComics(): Single<List<Comic>>
}
