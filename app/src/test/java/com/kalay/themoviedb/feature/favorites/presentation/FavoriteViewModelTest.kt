package com.kalay.themoviedb.feature.favorites.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.domain.usecase.local.DeleteFromFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.GetAllFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.InsertToFavoriteUseCase
import com.kalay.themoviedb.domain.usecase.local.IsFavoriteUseCase
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var insertToFavoriteUseCase: InsertToFavoriteUseCase
    private lateinit var deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase
    private lateinit var isFavoriteUseCase: IsFavoriteUseCase
    private lateinit var getAllFavoritesUseCase: GetAllFavoritesUseCase

    @Before
    fun setUp() {
        insertToFavoriteUseCase = mockk(relaxed = true)
        deleteFromFavoritesUseCase = mockk(relaxed = true)
        isFavoriteUseCase = mockk(relaxed = true)
        getAllFavoritesUseCase = mockk(relaxed = true)
        every { getAllFavoritesUseCase() } returns flowOf(emptyList())
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `Given viewModel created, When updateSearchQuery called with query, Then uiState searchQuery is updated`() {
        // Given
        val viewModel = FavoriteViewModel(
            insertToFavoriteUseCase,
            deleteFromFavoritesUseCase,
            isFavoriteUseCase,
            getAllFavoritesUseCase
        )
        val expectedQuery = "favori"

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
        val viewModel = FavoriteViewModel(
            insertToFavoriteUseCase,
            deleteFromFavoritesUseCase,
            isFavoriteUseCase,
            getAllFavoritesUseCase
        )

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
        val viewModel = FavoriteViewModel(
            insertToFavoriteUseCase,
            deleteFromFavoritesUseCase,
            isFavoriteUseCase,
            getAllFavoritesUseCase
        )
        viewModel.setSearchMode(true)

        // When
        viewModel.setSearchMode(false)

        // Then
        viewModel.uiState.value.apply {
            Truth.assertThat(isSearchMode).isFalse()
        }
    }
}
