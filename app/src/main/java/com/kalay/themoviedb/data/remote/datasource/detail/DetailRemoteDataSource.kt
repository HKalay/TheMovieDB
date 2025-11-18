package com.kalay.themoviedb.data.remote.datasource.detail

import com.kalay.themoviedb.data.remote.dto.Result

interface DetailRemoteDataSource {
    suspend fun getMovieDetail(id: Int): Result

    suspend fun getTvShowDetail(id: Int): Result
}
