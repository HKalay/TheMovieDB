package com.kalay.themoviedb.feature.movies.presentation

import app.cash.turbine.test
import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverMoviesUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchMoviesUseCase
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
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
    fun `Given viewModel created, When updateSearchQuery called with query, Then uiState searchQuery is updated`() = runTest {
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
    fun `Given viewModel created, When setSearchMode called with true, Then uiState isSearchMode is true`() = runTest {
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
    fun `Given searchMode is true, When setSearchMode called with false, Then uiState isSearchMode is false`() = runTest {
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

    @Test
    fun `Given viewModel, When collect uiState with Turbine and updateSearchQuery, Then flow emits expected state`() = runTest {
        // Given
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)
        val expectedQuery = "batman"

        // When & Then - Flow test with Turbine (step-by-step verification, 3.2.2)
        viewModel.uiState.test {
            awaitItem() // initial state
            viewModel.updateSearchQuery(expectedQuery)
            Truth.assertThat(awaitItem().searchQuery).isEqualTo(expectedQuery)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Given viewModel, When collect uiState with toList and updateSearchQuery, Then last value has expected searchQuery`() = runTest {
        // Given
        val viewModel = MoviesViewModel(getDiscoverMoviesUseCase, getSearchMoviesUseCase)
        val expectedQuery = "batman"

        // When
        viewModel.updateSearchQuery(expectedQuery)
        val collected: List<MovieUiState> = viewModel.uiState.take(1).toList()

        // Then - state test with toList() (3.2.1: last known value)
        val lastState = collected.last()
        Truth.assertThat(lastState.searchQuery).isEqualTo(expectedQuery)
    }
}
