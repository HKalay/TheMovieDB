package com.kalay.themoviedb.domain.model.local

import com.kalay.themoviedb.domain.enums.DetailType

data class FavoriteDTO(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val detailType: DetailType
)
