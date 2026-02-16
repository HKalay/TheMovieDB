package com.kalay.themoviedb.domain.mapper

import com.kalay.themoviedb.domain.model.local.Favorite
import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.model.remote.Discover

fun Discover.toDetail(): Detail {
    return Detail(
        id = id,
        posterPath = posterPath,
        detailType = detailType,
        title = title,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
    )
}

fun Favorite.toDetail(): Detail {
    return Detail(
        id = id,
        posterPath = posterPath,
        detailType = detailType,
        title = title,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
    )
}