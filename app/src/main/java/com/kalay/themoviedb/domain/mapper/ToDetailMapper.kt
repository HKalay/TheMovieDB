package com.kalay.themoviedb.domain.mapper

import com.kalay.themoviedb.domain.model.local.FavoriteDTO
import com.kalay.themoviedb.domain.model.remote.DetailDTO
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

fun DiscoverDTO.toDetail(): DetailDTO {
    return DetailDTO(
        id = id,
        posterPath = posterPath,
        detailType = detailType,
        title = title,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
    )
}

fun FavoriteDTO.toDetail(): DetailDTO {
    return DetailDTO(
        id = id,
        posterPath = posterPath,
        detailType = detailType,
        title = title,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
    )
}