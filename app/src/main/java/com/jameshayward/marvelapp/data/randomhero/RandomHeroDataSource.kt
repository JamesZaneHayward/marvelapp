package com.jameshayward.marvelapp.data.randomhero

import com.jameshayward.marvelapp.domain.hero.Hero
import io.reactivex.Single
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
    ): Single<Datum>
}

data class Datum(
    val data: Results
)

data class Results(
    val results: List<Hero>
)
