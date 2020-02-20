package com.jameshayward.marvelapp.data.randomhero

import com.jameshayward.marvelapp.domain.hero.Hero
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomHeroDataSource {
    @GET("v1/public/characters/{heroid}")
    fun fetchRandomHero(
        @Path(value = "heroid", encoded = true) heroId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    ): Observable<Datum>
}

data class Datum(
    val data: Results
)

data class Results(
    val results: Array<Hero>
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
