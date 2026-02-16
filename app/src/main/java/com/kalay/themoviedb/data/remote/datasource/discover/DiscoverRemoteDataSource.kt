package com.kalay.themoviedb.data.remote.datasource.discover

import com.kalay.themoviedb.data.remote.response.ServiceResponse

interface DiscoverRemoteDataSource {
    suspend fun getDiscoverMovies(page: Int): ServiceResponse
    suspend fun getDiscoverTv(page: Int): ServiceResponse
}
