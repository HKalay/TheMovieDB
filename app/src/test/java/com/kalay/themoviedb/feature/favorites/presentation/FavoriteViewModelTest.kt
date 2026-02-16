package com.kalay.themoviedb.feature.favorites.presentation

import com.kalay.themoviedb.core.util.MainDispatcherRule
import com.kalay.themoviedb.domain.usecase.local.DeleteFromFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.GetAllFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.InsertToFavoriteUseCase
import com.kalay.themoviedb.domain.usecase.local.IsFavoriteUseCase
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `updateSearchQuery updates uiState searchQuery`() {
        val insertToFavoriteUseCase = mockk<InsertToFavoriteUseCase>(relaxed = true)
        val deleteFromFavoritesUseCase = mockk<DeleteFromFavoritesUseCase>(relaxed = true)
        val isFavoriteUseCase = mockk<IsFavoriteUseCase>(relaxed = true)
        val getAllFavoritesUseCase = mockk<GetAllFavoritesUseCase>(relaxed = true)
        every { getAllFavoritesUseCase() } returns flowOf(emptyList())
        val viewModel = FavoriteViewModel(
            insertToFavoriteUseCase,
            deleteFromFavoritesUseCase,
            isFavoriteUseCase,
            getAllFavoritesUseCase
        )

        viewModel.updateSearchQuery("favori")

        assertEquals("favori", viewModel.uiState.value.searchQuery)
    }

    @Test
    fun `setSearchMode enabled updates uiState isSearchMode`() {
        val insertToFavoriteUseCase = mockk<InsertToFavoriteUseCase>(relaxed = true)
        val deleteFromFavoritesUseCase = mockk<DeleteFromFavoritesUseCase>(relaxed = true)
        val isFavoriteUseCase = mockk<IsFavoriteUseCase>(relaxed = true)
        val getAllFavoritesUseCase = mockk<GetAllFavoritesUseCase>(relaxed = true)
        every { getAllFavoritesUseCase() } returns flowOf(emptyList())
        val viewModel = FavoriteViewModel(
            insertToFavoriteUseCase,
            deleteFromFavoritesUseCase,
            isFavoriteUseCase,
            getAllFavoritesUseCase
        )

        viewModel.setSearchMode(true)

        assertEquals(true, viewModel.uiState.value.isSearchMode)
    }

    @Test
    fun `setSearchMode disabled updates uiState isSearchMode`() {
        val insertToFavoriteUseCase = mockk<InsertToFavoriteUseCase>(relaxed = true)
        val deleteFromFavoritesUseCase = mockk<DeleteFromFavoritesUseCase>(relaxed = true)
        val isFavoriteUseCase = mockk<IsFavoriteUseCase>(relaxed = true)
        val getAllFavoritesUseCase = mockk<GetAllFavoritesUseCase>(relaxed = true)
        every { getAllFavoritesUseCase() } returns flowOf(emptyList())
        val viewModel = FavoriteViewModel(
            insertToFavoriteUseCase,
            deleteFromFavoritesUseCase,
            isFavoriteUseCase,
            getAllFavoritesUseCase
        )
        viewModel.setSearchMode(true)

        viewModel.setSearchMode(false)

        assertEquals(false, viewModel.uiState.value.isSearchMode)
    }
}
