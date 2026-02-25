package com.kalay.themoviedb.feature.movies.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverMoviesUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchMoviesUseCase
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase
    private lateinit var getSearchMoviesUseCase: GetSearchMoviesUseCase

    @Before
    fun setUp() {
        getDiscoverMoviesUseCase = mockk(relaxed = true)
        getSearchMoviesUseCase = mockk(relaxed = true)
        coEvery { getDiscoverMoviesUseCase(any()) } returns emptyList()
        coEvery { getSearchMoviesUseCase(any(), any()) } returns emptyList()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given viewModel created, When updateSearchQuery called with query, Then uiState searchQuery is updated`() {
        // Given
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)
        val expectedQuery = "batman"

        // When
        viewModel.updateSearchQuery(expectedQuery)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(searchQuery).isEqualTo(expectedQuery)
        }
    }

    @Test
    fun `Given viewModel created, When setSearchMode called with true, Then uiState isSearchMode is true`() {
        // Given
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)

        // When
        viewModel.setSearchMode(true)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(isSearchMode).isTrue()
        }
    }

    @Test
    fun `Given searchMode is true, When setSearchMode called with false, Then uiState isSearchMode is false`() {
        // Given
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)
        viewModel.setSearchMode(true)

        // When
        viewModel.setSearchMode(false)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(isSearchMode).isFalse()
        }
    }
}
