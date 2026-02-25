package com.kalay.themoviedb.feature.tvshows.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverTvUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchTvUseCase
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TvShowsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @RelaxedMockK
    lateinit var getDiscoverTvUseCase: GetDiscoverTvUseCase

    @RelaxedMockK
    lateinit var getSearchTvUseCase: GetSearchTvUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { getDiscoverTvUseCase(any()) } returns emptyList()
        coEvery { getSearchTvUseCase(any(), any()) } returns emptyList()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given viewModel created, When updateSearchQuery called with query, Then uiState searchQuery is updated`() = runTest {
        // Given
        val viewModel = TvShowsViewModel(getDiscoverTvUseCase, getSearchTvUseCase)
        val expectedQuery = "breaking"

        // When
        viewModel.updateSearchQuery(expectedQuery)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(searchQuery).isEqualTo(expectedQuery)
        }
    }

    @Test
    fun `Given viewModel created, When setSearchMode called with true, Then uiState isSearchMode is true`() = runTest {
        // Given
        val viewModel = TvShowsViewModel(getDiscoverTvUseCase, getSearchTvUseCase)

        // When
        viewModel.setSearchMode(true)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(isSearchMode).isTrue()
        }
    }

    @Test
    fun `Given searchMode is true, When setSearchMode called with false, Then uiState isSearchMode is false`() = runTest {
        // Given
        val viewModel = TvShowsViewModel(getDiscoverTvUseCase, getSearchTvUseCase)
        viewModel.setSearchMode(true)

        // When
        viewModel.setSearchMode(false)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(isSearchMode).isFalse()
        }
    }
}
