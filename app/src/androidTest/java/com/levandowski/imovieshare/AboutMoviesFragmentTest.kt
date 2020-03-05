package com.levandowski.imovieshare

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.levandowski.imovieshare.ui.about.AboutMovieFragment
import com.levandowski.imovieshare.model.Movie
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutMoviesFragmentTest {

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
        private const val MOVIE_RELEASE_DATE = "2039-02-01"
    }

    @Before
    fun setup() {
        val movie = Movie(
            title = TITLE_MOVIE,
            overview = OVERVIEW_MOVIE,
            voteAverage = MOVIE_VOTE_AVERAGE,
            releaseDate = MOVIE_RELEASE_DATE
        )
        val fragmentArgs = Bundle().apply {
            putString(
                MOVIE_BUNDLE_REF, movie.wrap()
            )
        }
        FragmentScenario.launchInContainer(
            AboutMovieFragment::class.java,
            fragmentArgs,
            R.style.Theme_AppCompat,
            null
        )
    }

    @Test
    fun viewsAreOnScreen() {
        onView(withId(R.id.tv_about_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date_about_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview_about_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
    }

    @Test
    fun checkDataOnScreen() {
        onView(withText(TITLE_MOVIE)).check(matches(isDisplayed()))
        onView(withText(OVERVIEW_MOVIE)).check(matches(isDisplayed()))
        onView(withText(MOVIE_VOTE_AVERAGE.toString())).check(matches(isDisplayed()))
        onView(withText(MOVIE_RELEASE_DATE)).check(matches(isDisplayed()))
    }
}
