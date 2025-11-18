package com.kalay.themoviedb.data.mapper

import com.kalay.themoviedb.data.remote.dto.Result
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

fun Result.toDiscover(detailType: DetailType): DiscoverDTO {
    return DiscoverDTO(
        id = id,
        title = titleFormatted,
        posterPath = posterPathFormatted,
        releaseDate = dateFormatted,
        voteAverage = voteAverageFormatted,
        isFavorite = false,
        detailType = detailType
    )
}

