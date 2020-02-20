package com.jameshayward.marvelapp.presentation.comicslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jameshayward.marvelapp.common.BaseViewModel
import com.jameshayward.marvelapp.common.addTo
import com.jameshayward.marvelapp.domain.comic.Comic
import com.jameshayward.marvelapp.domain.comicslist.usecase.GetComicsListUseCase
import com.jameshayward.marvelapp.domain.common.usecase.UseCaseObservable
import javax.inject.Inject

class ComicsListViewModel @Inject constructor(
    private val getComicsListUseCase: GetComicsListUseCase
) : BaseViewModel(), OnComicDetailsListener {

    val comicsList = MutableLiveData<List<Comic>>()
    val isListRefreshing = MutableLiveData<Boolean>()

    init {
        comicsList.value = emptyList()
        refreshComicsList()
    }

    fun actionRefresh() {
        refreshComicsList()
    }

    private fun refreshComicsList() {
        isListRefreshing.postValue(true)
        getComicsListUseCase
            .execute(UseCaseObservable.None)
            .subscribe(
                { comics ->
                    comicsList.postValue(comics)
                    isListRefreshing.postValue(false)
                },
                { throwable ->
                    Log.d("error", throwable.message)
                    isListRefreshing.postValue(false)
                }
            )
            .addTo(disposables)
    }
}

interface OnComicDetailsListener
