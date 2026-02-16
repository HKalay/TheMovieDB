package com.kalay.themoviedb.domain.mapper

import com.kalay.themoviedb.domain.model.local.Favorite
import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.model.remote.Discover

fun Discover.toFavorite(): Favorite {
    return Favorite(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        detailType = detailType
    )
}

fun Detail.toFavorite(): Favorite {
    return Favorite(
        id = id,
        title = title ?: "Unknown",
        posterPath = posterPath ?: "Unknown",
        releaseDate = releaseDate ?: "Unknown",
        voteAverage = voteAverage ?: "Unknown",
        detailType = detailType
    )
}

