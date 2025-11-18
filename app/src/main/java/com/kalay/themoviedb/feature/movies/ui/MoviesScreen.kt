package com.kalay.themoviedb.feature.movies.ui

import MovieListGrid
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
import com.kalay.themoviedb.feature.movies.presentation.MoviesViewModel
import com.kalay.themoviedb.feature.movies.ui.component.MovieListDialog
import com.kalay.themoviedb.feature.movies.ui.component.MovieScreenEmpty

@Composable
fun MoviesScreen(navController: NavController) {
    val moviesViewModel: MoviesViewModel = hiltViewModel()
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()

    val uiState by moviesViewModel.uiState.collectAsState()
    val favoriteMap by favoriteViewModel.favoritesMap.collectAsState()

    if (uiState.isSearchMode.not()) {
        MovieListDialog(viewModel = moviesViewModel)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SearchHeader(
            screenTitle = stringResource(R.string.movies),
            hintText = stringResource(R.string.search_movies),
            currentText = uiState.searchQuery,
            isSearchMode = uiState.isSearchMode,
            onSearchModeChange = { moviesViewModel.setSearchMode(it) },
            onSearchQueryChange = { query ->
                moviesViewModel.updateSearchQuery(query)
            }
        )

        when (val state = uiState.movieListResource) {
            is Resource.Success -> {
                val movies = state.data
                if (movies.isEmpty()) {
                    val text = if (uiState.isSearchMode.not()) {
                        stringResource(R.string.no_movie_found)
                    } else {
                        stringResource(R.string.no_movie_found_search)
                    }
                    MovieScreenEmpty(text = text)
                } else {
                    MovieListGrid(
                        navController = navController,
                        movies = movies,
                        favoriteMap = favoriteMap,
                        onReachBottom = { moviesViewModel.fetchDiscoverMovies() },
                        updateFavoriteStatus = { movie ->
                            favoriteViewModel.toggleFavorite(movie)
                        },
                        syncFavoriteState = { movie ->
                            favoriteViewModel.syncFavoriteState(movie)
                        }
                    )
                }
            }

            else -> Unit
        }
    }
}