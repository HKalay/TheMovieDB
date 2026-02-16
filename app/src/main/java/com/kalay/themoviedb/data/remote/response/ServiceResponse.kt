package com.kalay.themoviedb.data.remote.response

import com.google.gson.annotations.SerializedName
import com.kalay.themoviedb.data.remote.dto.ResultDTO

data class ServiceResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("resultDTOS") val resultDTOS: List<ResultDTO>,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)
