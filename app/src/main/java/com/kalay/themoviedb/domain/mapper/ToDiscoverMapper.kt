package com.kalay.themoviedb.domain.mapper

import com.kalay.themoviedb.domain.model.local.FavoriteDTO
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO


fun FavoriteDTO.toDiscover(): DiscoverDTO {
    return DiscoverDTO(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        isFavorite = true,
        detailType = detailType
    )
}
