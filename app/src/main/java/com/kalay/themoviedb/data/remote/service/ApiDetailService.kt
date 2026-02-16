package com.kalay.themoviedb.data.remote.service

import com.kalay.themoviedb.data.remote.dto.ResultDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): ResultDTO

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tvShowId: Int
    ): ResultDTO

}
