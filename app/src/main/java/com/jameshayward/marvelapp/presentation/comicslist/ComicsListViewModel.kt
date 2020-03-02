package com.jameshayward.marvelapp.presentation.comicslist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jameshayward.marvelapp.common.BaseViewModel
import com.jameshayward.marvelapp.common.addTo
import com.jameshayward.marvelapp.domain.comic.Comic
import com.jameshayward.marvelapp.domain.comicslist.usecase.GetComicsListUseCase
import com.jameshayward.marvelapp.domain.common.usecase.UseCaseSingle
import javax.inject.Inject

class ComicsListViewModel @Inject constructor(
    private val getComicsListUseCase: GetComicsListUseCase
) : BaseViewModel() {

    val comicsList: LiveData<List<Comic>>
        get() = _comicsList
    private val _comicsList = MutableLiveData<List<Comic>>()

    val isListRefreshing: LiveData<Boolean>
        get() = _isListRefreshing
    private val _isListRefreshing = MutableLiveData<Boolean>()

    init {
        _comicsList.value = emptyList()
        refreshComicsList()
    }

    fun actionRefresh() {
        refreshComicsList()
    }

    private fun refreshComicsList() {
        _isListRefreshing.postValue(true)
        getComicsListUseCase
            .execute(UseCaseSingle.None)
            .subscribe(
                { comics ->
                    _comicsList.postValue(comics)
                    _isListRefreshing.postValue(false)
                },
                { throwable ->
                    Log.d("error", throwable.message)
                    _isListRefreshing.postValue(false)
                }
            )
            .addTo(disposables)
    }
}
