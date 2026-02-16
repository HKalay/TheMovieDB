package com.kalay.themoviedb.data.mapper

import com.kalay.themoviedb.data.remote.dto.ResultDTO
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Discover

fun ResultDTO.toDiscover(detailType: DetailType): Discover {
    return Discover(
        id = id,
        title = titleFormatted,
        posterPath = posterPathFormatted,
        releaseDate = dateFormatted,
        voteAverage = voteAverageFormatted,
        isFavorite = false,
        detailType = detailType
    )
}

