package com.kalay.themoviedb.feature.favorites.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.ui.search.SearchHeader
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.feature.favorites.presentation.FavoriteViewModel
import com.kalay.themoviedb.feature.favorites.ui.component.FavoriteListGrid
import com.kalay.themoviedb.feature.favorites.ui.component.FavoriteScreenEmpty
import com.kalay.themoviedb.feature.favorites.ui.component.FavoritesDialog

@Composable
fun FavoritesScreen(navController: NavController) {

    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val uiState by favoriteViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        favoriteViewModel.fetchFavorites()
    }

    if (uiState.isSearchMode.not()) {
        FavoritesDialog(favoriteViewModel = favoriteViewModel)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchHeader(
            screenTitle = stringResource(R.string.favorites),
            hintText = stringResource(R.string.search_movies),
            currentText = uiState.searchQuery,
            isSearchMode = uiState.isSearchMode,
            onSearchModeChange = { favoriteViewModel.setSearchMode(it) },
            onSearchQueryChange = { query ->
                favoriteViewModel.updateSearchQuery(query)
            }
        )

        when (val state = uiState.favoriteResource) {
            is Resource.Success -> {
                val favorites = state.data
                if (favorites.isEmpty()) {
                    val text = if (uiState.isSearchMode) {
                        stringResource(R.string.no_movie_found_search)
                    } else {
                        stringResource(R.string.no_movie_found)
                    }
                    FavoriteScreenEmpty(text=text)
                } else {
                    FavoriteListGrid(
                        navController = navController,
                        favorites = favorites,
                        updateFavoriteStatus = { id -> favoriteViewModel.deleteById(id) }
                    )
                }
            }

            else -> Unit
        }
    }
}