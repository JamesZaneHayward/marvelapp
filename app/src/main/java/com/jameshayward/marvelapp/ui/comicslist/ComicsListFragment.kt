package com.jameshayward.marvelapp.ui.comicslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jameshayward.marvelapp.presentation.comicslist.ComicsListViewModel
import com.jameshayward.marvelapp.R
import com.jameshayward.marvelapp.presentation.common.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_comics_list.*
import javax.inject.Inject

class ComicsListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val comicsListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ComicsListViewModel::class.java)
    }

    private lateinit var comicsAdapter: ComicListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comics_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onViewCreated(view, savedInstanceState)

        comicsListViewModel.comicsList.observe(this, Observer {
            comicsAdapter.items = it
            comicsAdapter.notifyDataSetChanged()
        })

        comicsListViewModel.isListRefreshing.observe(this, Observer {
            layoutComics.isRefreshing = it
        })

        comicsAdapter = ComicListAdapter(comicsListViewModel.comicsList.value!!, context!!)

        recyclerComics.layoutManager = LinearLayoutManager(context)
        recyclerComics.adapter = comicsAdapter

        search_comics.imeOptions = EditorInfo.IME_ACTION_DONE
        search_comics.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                comicsAdapter.filter.filter(newText)
                return true
            }
        })

        layoutComics.setOnRefreshListener { comicsListViewModel.actionRefresh() }
    }
}
