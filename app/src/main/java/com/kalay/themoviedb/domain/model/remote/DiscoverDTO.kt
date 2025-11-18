package com.kalay.themoviedb.domain.model.remote

import com.kalay.themoviedb.domain.enums.DetailType

data class DiscoverDTO(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val isFavorite: Boolean,
    val detailType: DetailType
)
