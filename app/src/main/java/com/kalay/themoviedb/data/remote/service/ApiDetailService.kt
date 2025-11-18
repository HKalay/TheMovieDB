package com.kalay.themoviedb.data.remote.service

import com.kalay.themoviedb.data.remote.dto.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDetailService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int
    ): Result

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(
        @Path("tv_id") tvShowId: Int
    ): Result

}
