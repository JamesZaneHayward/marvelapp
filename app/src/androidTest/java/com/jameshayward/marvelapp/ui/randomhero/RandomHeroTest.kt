package com.jameshayward.marvelapp.ui.randomhero

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.jameshayward.marvelapp.MainActivity
import com.jameshayward.marvelapp.R
import io.mockk.MockKAnnotations
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RandomHeroTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        Intents.init()
        MockKAnnotations.init(this)
    }

    @Test
    fun randomHeroDefaultText() {
        activityRule.launchActivity(null)

        onView(withId(R.id.navigation_random_hero)).perform(click())

        onView(withId(R.id.text_random_hero)).check(matches(withText(DEFAULT_HERO_TEXT)))
    }

    @Test
    fun randomHeroSpidermanText() {
        activityRule.launchActivity(null)

        onView(withId(R.id.navigation_random_hero)).perform(click())

        onView(withId(R.id.btn_random_comic)).perform(click())

        onView(withId(R.id.text_random_hero)).check(matches(withText(SPIDERMAN_NAME)))
        onView(withId(R.id.text_random_hero_description)).check(matches(withText(SPIDERMAN_DESC)))
    }

    @After
    fun teardown() {
        Intents.release()
    }

    companion object {
        const val DEFAULT_HERO_TEXT = "Find your new favourite Comic Book Hero"
        const val SPIDERMAN_NAME = "Spider-Man"
        const val SPIDERMAN_DESC = "Peter Parker is just your friendly neighbourhood Spider-Man"
    }
}
