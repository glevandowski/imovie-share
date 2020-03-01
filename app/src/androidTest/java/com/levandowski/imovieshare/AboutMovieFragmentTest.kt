package com.levandowski.imovieshare

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.levandowski.imovieshare.about.AboutMovieFragment
import com.levandowski.imovieshare.model.Movie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutMovieFragmentTest {

    companion object {
        private const val MOVIE_BUNDLE_REF = "movie"
        private const val TITLE_MOVIE = "Lord of the Rings"
        private const val OVERVIEW_MOVIE = "In a fantastic and unique land, " +
                "a hobbit receives from his uncle a magic and evil ring that needs to be " +
                "destroyed before it falls into the hands of evil. For that, the hobbit Frodo has" +
                " an arduous path ahead, where he finds danger, fear and bizarre beings. At his side " +
                "for the completion of this journey, he can gradually count on other hobbits, an elf, " +
                "a dwarf, two humans and a magician, totaling nine people who form the Society of the Ring"
        private const val MOVIE_VOTE_AVERAGE = 4.0
    }

    @Before
    fun setup() {
        val fragmentArgs = Bundle().apply {
            putString(
                MOVIE_BUNDLE_REF, Gson().toJson(
                    Movie(
                        title = TITLE_MOVIE,
                        overview = OVERVIEW_MOVIE,
                        voteAverage = MOVIE_VOTE_AVERAGE
                    )
                )
            )
        }
        FragmentScenario.launchInContainer(AboutMovieFragment::class.java, fragmentArgs)
    }

    @Test
    fun viewsAreOnScreen() {
        onView(withText(TITLE_MOVIE)).check(matches(isDisplayed()))
        onView(withText(OVERVIEW_MOVIE)).check(matches(isDisplayed()))
    }
}
