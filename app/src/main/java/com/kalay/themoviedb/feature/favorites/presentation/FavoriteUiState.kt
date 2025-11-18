package com.kalay.themoviedb.feature.favorites.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.local.FavoriteDTO

data class FavoriteUiState(
    var favoriteResource: Resource<List<FavoriteDTO>> = Resource.Loading,
    var searchQuery: String = "",
    var isSearchMode: Boolean = false
)

