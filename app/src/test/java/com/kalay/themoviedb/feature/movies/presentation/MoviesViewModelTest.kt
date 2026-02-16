package com.kalay.themoviedb.feature.movies.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverMoviesUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `updateSearchQuery updates uiState searchQuery`() {
        val getDiscoverMoviesUseCase = mockk<GetDiscoverMoviesUseCase>(relaxed = true)
        val getSearchMoviesUseCase = mockk<GetSearchMoviesUseCase>(relaxed = true)
        coEvery { getDiscoverMoviesUseCase(any()) } returns emptyList()
        coEvery { getSearchMoviesUseCase(any(), any()) } returns emptyList()
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)

        viewModel.updateSearchQuery("batman")

        assertEquals("batman", viewModel.uiState.value.searchQuery)
    }

    @Test
    fun `setSearchMode enabled updates uiState isSearchMode`() {
        val getDiscoverMoviesUseCase = mockk<GetDiscoverMoviesUseCase>(relaxed = true)
        val getSearchMoviesUseCase = mockk<GetSearchMoviesUseCase>(relaxed = true)
        coEvery { getDiscoverMoviesUseCase(any()) } returns emptyList()
        coEvery { getSearchMoviesUseCase(any(), any()) } returns emptyList()
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)

        viewModel.setSearchMode(true)

        assertEquals(true, viewModel.uiState.value.isSearchMode)
    }

    @Test
    fun `setSearchMode disabled updates uiState isSearchMode`() {
        val getDiscoverMoviesUseCase = mockk<GetDiscoverMoviesUseCase>(relaxed = true)
        val getSearchMoviesUseCase = mockk<GetSearchMoviesUseCase>(relaxed = true)
        coEvery { getDiscoverMoviesUseCase(any()) } returns emptyList()
        coEvery { getSearchMoviesUseCase(any(), any()) } returns emptyList()
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)
        viewModel.setSearchMode(true)

        viewModel.setSearchMode(false)

        assertEquals(false, viewModel.uiState.value.isSearchMode)
    }
}
