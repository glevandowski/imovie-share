package com.levandowski.imovieshare

import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.levandowski.imovieshare.ui.movies.MoviesFragment
import com.google.gson.Gson
import com.levandowski.imovieshare.model.Movie
import com.levandowski.imovieshare.ui.movies.MoviesFragmentDirections
import com.levandowski.imovieshare.util.RecyclerViewMatcher
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {

    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var navController: NavController

    @Before
    fun setup() {
        FragmentScenario.launchInContainer(MoviesFragment::class.java).onFragment {
            Navigation.setViewNavController(
                it.requireView(), navController
            )
        }
    }

    @Test
    fun movieDescriptionWasCalled() {
        RecyclerViewMatcher.clickOnPosition(R.id.rv_movies, 0)
        val action = MoviesFragmentDirections.actionMoviesFragmentToAboutMovieFragment().apply {
            movie = Gson().toJson(
                Movie(
                    title = "Tarantino",
                    overview = "Is happy",
                    voteAverage = 1.0
                )
            )
        }
        verify(navController).navigate(action)
    }

    @Test
    fun checkItemWhenScroll() {
        RecyclerViewMatcher.scrollToPosition(R.id.rv_movies, 9)
        RecyclerViewMatcher.hasViewInPosition(R.id.rv_movies, 9)
        RecyclerViewMatcher.scrollToPosition(R.id.rv_movies, 2)
        RecyclerViewMatcher.hasViewInPosition(R.id.rv_movies, 2)
    }
}
