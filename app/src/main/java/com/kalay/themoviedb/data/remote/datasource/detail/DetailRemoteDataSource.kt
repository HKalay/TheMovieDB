package com.kalay.themoviedb.data.remote.datasource.detail

import com.kalay.themoviedb.data.remote.dto.ResultDTO

interface DetailRemoteDataSource {
    suspend fun getMovieDetail(id: Int): ResultDTO

    suspend fun getTvShowDetail(id: Int): ResultDTO
}
