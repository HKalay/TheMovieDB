package com.kalay.themoviedb.domain.model.remote

import com.kalay.themoviedb.domain.enums.DetailType
import kotlinx.serialization.Serializable

@Serializable
data class DetailDTO(
    val id: Int,
    val title: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: String? = null,
    val voteCount: Int? = null,
    val releaseDate: String? = null,
    val genres: List<String> = emptyList(),
    val detailType: DetailType
)
