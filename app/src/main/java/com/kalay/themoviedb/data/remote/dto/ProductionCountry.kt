package com.kalay.themoviedb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1") val iso: String,
    @SerializedName("name") val name: String
)