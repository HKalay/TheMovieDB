package com.kalay.themoviedb.data.remote.datasource.detail

import com.kalay.themoviedb.data.remote.dto.Result
import com.kalay.themoviedb.data.remote.service.ApiDetailService
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(
    private val apiDetailService: ApiDetailService
) : DetailRemoteDataSource {
    override suspend fun getMovieDetail(id: Int): Result {
        return apiDetailService.getMovieDetail(movieId = id)
    }

    override suspend fun getTvShowDetail(id: Int): Result {
        return apiDetailService.getTvShowDetail(tvShowId = id)
    }
}
