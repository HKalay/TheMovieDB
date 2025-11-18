package com.kalay.themoviedb.feature.detail.presentation

import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.domain.model.remote.DetailDTO

data class DetailUiState(
    var detailResource: Resource<DetailDTO> = Resource.Loading,
    var isFavorite: Boolean = false
)
