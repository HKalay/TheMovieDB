package com.kalay.themoviedb.data.remote.datasource.discover

import com.kalay.themoviedb.data.remote.response.ServiceResponseDTO

interface DiscoverRemoteDataSource {
    suspend fun getDiscoverMovies(page: Int): ServiceResponseDTO
    suspend fun getDiscoverTv(page: Int): ServiceResponseDTO
}
