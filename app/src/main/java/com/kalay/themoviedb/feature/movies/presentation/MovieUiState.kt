package com.kalay.themoviedb.feature.movies.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

data class MovieUiState(
    var movieListResource: Resource<List<DiscoverDTO>> = Resource.Loading,
    var searchQuery: String = "",
    var isPaginating: Boolean = false,
    var isSearchMode: Boolean = false
)

