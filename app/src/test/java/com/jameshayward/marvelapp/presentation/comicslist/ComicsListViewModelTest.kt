package com.jameshayward.marvelapp.presentation.comicslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jameshayward.marvelapp.domain.comic.Comic
import com.jameshayward.marvelapp.domain.comic.Thumbnail
import com.jameshayward.marvelapp.domain.comicslist.usecase.GetComicsListUseCase
import com.jameshayward.marvelapp.domain.common.usecase.UseCaseObservable
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ComicsListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getComicsListUseCase: GetComicsListUseCase

    @MockK
    private lateinit var compositeDisposable: CompositeDisposable

    private val comicsSubject = PublishSubject.create<List<Comic>>()
    private lateinit var comicsListViewModel: ComicsListViewModel
    private lateinit var comicsList: List<Comic>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { getComicsListUseCase.execute(any()) } answers { comicsSubject }
        every { compositeDisposable.add(any()) } answers { true }

        comicsListViewModel = ComicsListViewModel(getComicsListUseCase)

        comicsListViewModel.comicsList.observeForever { t -> comicsList = t }
    }

    private val emptyList: List<Comic> = emptyList()

    @Test
    fun `GIVEN an empty list returned WHEN action refresh THEN comicList LiveData contains empty list`() {
        every { getComicsListUseCase.execute(UseCaseObservable.None) } returns
                Observable.just(emptyList)

        comicsListViewModel.actionRefresh()

        assertEquals(emptyList, comicsList)
    }

    @Test
    fun `GIVEN a list of comics returned WHEN action refresh THEN comicList LiveData contains same list of comics`() {
        val listOfComics = listOf(
            Comic(0, "comic", 1, "", Thumbnail("", "")),
            Comic(1, "comic2", 5, "empty test description", Thumbnail("https://www.example.com/42174", "png")),
            Comic(2, "comic3", 1000, "120398!__!_@_@_", Thumbnail("http://www.example.com/banana", "jpg"))
        )

        every { getComicsListUseCase.execute(UseCaseObservable.None) } returns
                Observable.just(listOfComics)

        comicsListViewModel.actionRefresh()

        assertEquals(listOfComics, comicsList)
    }
}
