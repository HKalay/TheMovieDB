package com.kalay.themoviedb.data.mapper

import com.kalay.themoviedb.data.local.entity.FavoriteEntity
import com.kalay.themoviedb.domain.model.local.Favorite


fun Favorite.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        detailType = detailType
    )
}

