package com.kalay.themoviedb.feature.tvshows.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverTvUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchTvUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TvShowsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `updateSearchQuery updates uiState searchQuery`() {
        val getDiscoverTvUseCase = mockk<GetDiscoverTvUseCase>(relaxed = true)
        val getSearchTvUseCase = mockk<GetSearchTvUseCase>(relaxed = true)
        coEvery { getDiscoverTvUseCase(any()) } returns emptyList()
        coEvery { getSearchTvUseCase(any(), any()) } returns emptyList()
        val viewModel = TvShowsViewModel(getDiscoverTvUseCase, getSearchTvUseCase)

        viewModel.updateSearchQuery("breaking")

        assertEquals("breaking", viewModel.uiState.value.searchQuery)
    }

    @Test
    fun `setSearchMode enabled updates uiState isSearchMode`() {
        val getDiscoverTvUseCase = mockk<GetDiscoverTvUseCase>(relaxed = true)
        val getSearchTvUseCase = mockk<GetSearchTvUseCase>(relaxed = true)
        coEvery { getDiscoverTvUseCase(any()) } returns emptyList()
        coEvery { getSearchTvUseCase(any(), any()) } returns emptyList()
        val viewModel = TvShowsViewModel(getDiscoverTvUseCase, getSearchTvUseCase)

        viewModel.setSearchMode(true)

        assertEquals(true, viewModel.uiState.value.isSearchMode)
    }

    @Test
    fun `setSearchMode disabled updates uiState isSearchMode`() {
        val getDiscoverTvUseCase = mockk<GetDiscoverTvUseCase>(relaxed = true)
        val getSearchTvUseCase = mockk<GetSearchTvUseCase>(relaxed = true)
        coEvery { getDiscoverTvUseCase(any()) } returns emptyList()
        coEvery { getSearchTvUseCase(any(), any()) } returns emptyList()
        val viewModel = TvShowsViewModel(getDiscoverTvUseCase, getSearchTvUseCase)
        viewModel.setSearchMode(true)

        viewModel.setSearchMode(false)

        assertEquals(false, viewModel.uiState.value.isSearchMode)
    }
}
