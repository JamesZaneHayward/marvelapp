package com.jameshayward.marvelapp.data.comicslist

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jameshayward.marvelapp.domain.comic.Comic
import com.jameshayward.marvelapp.domain.comic.Thumbnail
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ComicsListRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var comicsListDataSource: ComicsListDataSource

    private val repository by lazy {
        ComicsListRepository(
            comicDataSource = comicsListDataSource
        )
    }

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `GIVEN no comics from data source WHEN calling data source THEN empty list of comics returned`() {
        every {
            comicsListDataSource.fetchComicsList(
                any(),
                any(),
                any(),
                any()
            )
        } returns Single.just(Datum(Results(emptyList())))

        var listOfComics: List<Comic> = listOf(Comic(0, "", 0, "desc", Thumbnail("", "")))
        repository
            .getComics()
            .subscribe(
                { listOfComics = it },
                { throwable ->
                    Log.d("error", throwable.message)
                }
            )

        val emptyList: List<Comic> = emptyList()

        assertEquals(emptyList, listOfComics)
    }

    @Test
    fun `GIVEN comics from data source WHEN calling data source THEN mapped list of comics returned`() {
        val expectedList = listOf(
            Comic(0, "comic", 1, "", Thumbnail("", "")),
            Comic(
                1,
                "comic2",
                5,
                "empty test description",
                Thumbnail("https://www.example.com/42174", "png")
            ),
            Comic(
                2,
                "comic3",
                1000,
                "120398!__!_@_@_",
                Thumbnail("http://www.example.com/banana", "jpg")
            )
        )
        every {
            comicsListDataSource.fetchComicsList(
                any(),
                any(),
                any(),
                any()
            )
        } returns Single.just(
            Datum(
                Results(
                    listOf(
                        Comic(0, "comic", 1, "", Thumbnail("", "")),
                        Comic(
                            1,
                            "comic2",
                            5,
                            "empty test description",
                            Thumbnail("https://www.example.com/42174", "png")
                        ),
                        Comic(
                            2,
                            "comic3",
                            1000,
                            "120398!__!_@_@_",
                            Thumbnail("http://www.example.com/banana", "jpg")
                        )
                    )
                )
            )
        )

        var listOfComics: List<Comic> = listOf(Comic(0, "", 0, "desc", Thumbnail("", "")))
        repository
            .getComics()
            .subscribe(
                { listOfComics = it },
                { throwable ->
                    Log.d("error", throwable.message)
                }
            )

        assertEquals(expectedList, listOfComics)
    }
}
