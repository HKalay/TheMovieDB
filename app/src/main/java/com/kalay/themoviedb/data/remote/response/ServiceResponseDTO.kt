package com.kalay.themoviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.kalay.themoviedb.data.remote.dto.Result

data class ServiceResponseDTO(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<Result>,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)
