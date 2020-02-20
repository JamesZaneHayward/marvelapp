package com.jameshayward.marvelapp.ui.randomhero

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jameshayward.marvelapp.R
import com.jameshayward.marvelapp.presentation.common.ViewModelFactory
import com.jameshayward.marvelapp.presentation.randomhero.RandomHeroViewModel
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_random_hero.*
import javax.inject.Inject

class RandomHeroFragment : DaggerFragment() {

    @Inject
    lateinit var randomHeroViewModelFactory: ViewModelFactory

    private val randomHeroViewModel: RandomHeroViewModel by lazy {
        ViewModelProviders.of(this, randomHeroViewModelFactory).get(RandomHeroViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_hero, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_random_hero_description.movementMethod = ScrollingMovementMethod()
        btn_random_comic.setOnClickListener { randomHeroViewModel.getRandomHero() }
        randomHeroViewModel.randomHero.observe(this, Observer {
            text_random_hero.text = it.name
            text_random_hero_description.text = it.description
            if (it.thumbnail.path.isNotEmpty()) {
                Picasso.with(context).load(it.thumbnail.path + "/standard_large.jpg").into(img_random_hero)
            }
        })
    }
}
