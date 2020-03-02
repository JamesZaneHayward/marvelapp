package com.jameshayward.marvelapp.data.comicslist

import com.jameshayward.marvelapp.domain.comic.Comic
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsListDataSource {
    @GET("v1/public/comics")
    fun fetchComicsList(
        @Query("limit") limit: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Single<Datum>
}

data class Datum(
    val data: Results
)

data class Results(
    val results: List<Comic>
)
