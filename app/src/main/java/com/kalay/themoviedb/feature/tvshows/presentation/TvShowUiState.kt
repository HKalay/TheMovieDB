package com.kalay.themoviedb.feature.tvshows.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.remote.Discover

data class TvShowUiState(
    val tvShowListResource: Resource<List<Discover>> = Resource.Loading,
    val searchQuery: String = "",
    val isPaginating: Boolean = false,
    val isSearchMode: Boolean = false
)

