package com.kalay.themoviedb.feature.favorites.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.local.Favorite

data class FavoriteUiState(
    val favoriteResource: Resource<List<Favorite>> = Resource.Loading,
    val searchQuery: String = "",
    val isSearchMode: Boolean = false
)

