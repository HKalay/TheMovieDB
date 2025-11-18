package com.kalay.themoviedb.data.remote.service

import com.kalay.themoviedb.data.remote.response.ServiceResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDiscoverService {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("page") page: Int
    ): ServiceResponseDTO

    @GET("discover/tv")
    suspend fun getDiscoverTv(
        @Query("page") page: Int
    ): ServiceResponseDTO
}
