package com.kalay.themoviedb.domain.mapper

import com.kalay.themoviedb.domain.model.local.Favorite
import com.kalay.themoviedb.domain.model.remote.Discover


fun Favorite.toDiscover(): Discover {
    return Discover(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        isFavorite = true,
        detailType = detailType
    )
}
