package com.kalay.themoviedb.feature.tvshows.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

data class TvShowUiState(
    val tvShowListResource: Resource<List<DiscoverDTO>> = Resource.Loading,
    val searchQuery: String = "",
    val isPaginating: Boolean = false,
    val isSearchMode: Boolean = false
)

