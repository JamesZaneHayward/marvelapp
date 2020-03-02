package com.jameshayward.marvelapp.presentation.randomhero

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jameshayward.marvelapp.common.BaseViewModel
import com.jameshayward.marvelapp.common.addTo
import com.jameshayward.marvelapp.domain.hero.Hero
import com.jameshayward.marvelapp.domain.hero.Thumbnail
import com.jameshayward.marvelapp.domain.randomhero.usecase.GetRandomHeroUseCase
import javax.inject.Inject
import kotlin.random.Random

class RandomHeroViewModel @Inject constructor(
    private val getRandomHeroUseCase: GetRandomHeroUseCase
) : BaseViewModel() {

    val randomHero: LiveData<Hero>
        get() = _randomHero

    private val _randomHero = MutableLiveData<Hero>().apply {
        value = Hero("Find your new favourite Comic Book Hero", Thumbnail("", ""), "")
    }

    fun getRandomHero() {
        getRandomHeroUseCase
            .execute(HeroIds.idList[Random.nextInt(0, HeroIds.size)].toString())
            .subscribe({ hero ->
                _randomHero.postValue(hero)
            },
                { throwable ->
                    Log.d("error", throwable.message)
                })
            .addTo(disposables)
    }
}

object HeroIds {
    val idList = listOf(
        1010354,
        1010755,
        1009596,
        1011346,
        1017109
    )
    val size = idList.size
}
