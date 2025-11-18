package com.kalay.themoviedb.feature.tvshows.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

data class TvShowUiState(
    var tvShowListResource: Resource<List<DiscoverDTO>> = Resource.Loading,
    var searchQuery: String = "",
    var isPaginating: Boolean = false,
    var isSearchMode: Boolean = false
)

