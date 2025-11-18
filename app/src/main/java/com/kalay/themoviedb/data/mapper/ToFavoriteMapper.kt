package com.kalay.themoviedb.data.mapper

import com.kalay.themoviedb.data.local.entity.FavoriteEntity
import com.kalay.themoviedb.domain.model.local.FavoriteDTO


fun FavoriteEntity.toFavorite(): FavoriteDTO {
    return FavoriteDTO(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        detailType = detailType
    )
}

