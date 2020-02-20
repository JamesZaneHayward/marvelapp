package com.jameshayward.marvelapp.data.comicslist

import com.jameshayward.marvelapp.domain.comic.Comic
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsListDataSource {
    @GET("v1/public/comics")
    fun fetchComicsList(
        @Query("limit") limit: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Observable<Datum>
}

data class Datum(
    val data: Results
)

data class Results(
    val results: Array<Comic>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Results

        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        return results.contentHashCode()
    }
}
