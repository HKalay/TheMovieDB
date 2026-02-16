package com.kalay.themoviedb.feature.movies.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

data class MovieUiState(
    val movieListResource: Resource<List<DiscoverDTO>> = Resource.Loading,
    val searchQuery: String = "",
    val isPaginating: Boolean = false,
    val isSearchMode: Boolean = false
)

