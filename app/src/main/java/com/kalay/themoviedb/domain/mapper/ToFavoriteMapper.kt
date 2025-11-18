package com.kalay.themoviedb.domain.mapper

import com.kalay.themoviedb.domain.model.local.FavoriteDTO
import com.kalay.themoviedb.domain.model.remote.DetailDTO
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

fun DiscoverDTO.toFavorite(): FavoriteDTO {
    return FavoriteDTO(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        detailType = detailType
    )
}

fun DetailDTO.toFavorite(): FavoriteDTO {
    return FavoriteDTO(
        id = id,
        title = title ?: "Unknown",
        posterPath = posterPath ?: "Unknown",
        releaseDate = releaseDate ?: "Unknown",
        voteAverage = voteAverage ?: "Unknown",
        detailType = detailType
    )
}

