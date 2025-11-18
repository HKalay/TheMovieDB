package com.kalay.themoviedb.feature.tvshows.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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
import com.kalay.themoviedb.feature.tvshows.presentation.TvShowsViewModel
import com.kalay.themoviedb.feature.tvshows.ui.component.TvShowListDialog
import com.kalay.themoviedb.feature.tvshows.ui.component.TvShowListGrid
import com.kalay.themoviedb.feature.tvshows.ui.component.TvShowScreenEmpty

@Composable
fun TvShowsScreen(navController: NavController) {
    val tvShowsViewModel: TvShowsViewModel = hiltViewModel()
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()

    val uiState by tvShowsViewModel.uiState.collectAsState()
    val favoriteMap by favoriteViewModel.favoritesMap.collectAsState()

    if (uiState.isSearchMode.not()) {
        TvShowListDialog(viewModel = tvShowsViewModel)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchHeader (
            screenTitle = stringResource(R.string.tv_shows),
            hintText = stringResource(R.string.search_tv_shows),
            currentText = uiState.searchQuery,
            isSearchMode = uiState.isSearchMode,
            onSearchModeChange = { tvShowsViewModel.setSearchMode(it) },
            onSearchQueryChange = { query -> tvShowsViewModel.updateSearchQuery(query) }
        )

        when (val state = uiState.tvShowListResource) {
            is Resource.Success -> {
                val tvShows = state.data
                if (tvShows.isEmpty()) {
                    val text = if (uiState.isSearchMode.not()) {
                        stringResource(R.string.no_tv_shows_found)
                    } else {
                        stringResource(R.string.no_tv_show_found_search)
                    }
                    TvShowScreenEmpty(text = text)
                } else {
                    TvShowListGrid(
                        navController = navController,
                        tvShows = tvShows,
                        favoriteMap = favoriteMap,
                        onReachBottom = { tvShowsViewModel.fetchDiscoverTv() },
                        updateFavoriteStatus = { tvShow ->
                            favoriteViewModel.toggleFavorite(tvShow)
                        },
                        onSyncFavorite = { tvShow ->
                            favoriteViewModel.syncFavoriteState(tvShow)
                        }
                    )
                }
            }

            else -> Unit
        }
    }
}
