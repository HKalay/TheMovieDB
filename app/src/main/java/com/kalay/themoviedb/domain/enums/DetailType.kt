package com.kalay.themoviedb.domain.enums

import kotlinx.serialization.Serializable

@Serializable
enum class DetailType {
    MOVIE,
    TV_SHOW
}