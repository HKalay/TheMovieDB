package com.kalay.themoviedb.data.remote.datasource.discover

import com.kalay.themoviedb.data.remote.response.ServiceResponseDTO
import com.kalay.themoviedb.data.remote.service.ApiDiscoverService
import javax.inject.Inject

class DiscoverRemoteDataSourceImpl @Inject constructor(
    private val apiDiscoverService: ApiDiscoverService
) : DiscoverRemoteDataSource {

    override suspend fun getDiscoverMovies(page: Int): ServiceResponseDTO {
        return apiDiscoverService.getDiscoverMovies(page = page)
    }

    override suspend fun getDiscoverTv(page: Int): ServiceResponseDTO {
        return apiDiscoverService.getDiscoverTv(page = page)
    }
}
