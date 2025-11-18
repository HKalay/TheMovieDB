package com.kalay.themoviedb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kalay.themoviedb.domain.enums.DetailType


@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: String,
    val detailType: DetailType
)
