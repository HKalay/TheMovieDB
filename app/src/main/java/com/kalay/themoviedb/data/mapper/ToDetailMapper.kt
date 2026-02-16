package com.kalay.themoviedb.data.mapper

import com.kalay.themoviedb.data.remote.dto.ResultDTO
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Detail

fun ResultDTO.toDetail(detailType: DetailType): Detail {
    return Detail(
        id = id,
        title = titleFormatted,
        overview = overview,
        posterPath = posterPathFormatted,
        backdropPath = backdropPathFormatted,
        voteAverage = voteAverageFormatted,
        voteCount = voteCount,
        releaseDate = dateFormatted,
        genres = genreDTOS.map { it.name },
        detailType = detailType
    )
}

