package com.kalay.themoviedb.data.remote.service

import com.kalay.themoviedb.data.remote.response.ServiceResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSearchService {
    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): ServiceResponseDTO

    @GET("search/tv")
    suspend fun getSearchTv(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): ServiceResponseDTO
}
