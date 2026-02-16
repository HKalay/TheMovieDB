package com.kalay.themoviedb.feature.detail.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.remote.DetailDTO

data class DetailUiState(
    val detailResource: Resource<DetailDTO> = Resource.Loading,
    val isFavorite: Boolean = false
)
