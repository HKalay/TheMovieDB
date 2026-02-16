package com.kalay.themoviedb.data.remote.datasource.search

import com.kalay.themoviedb.data.remote.response.ServiceResponseDTO
import com.kalay.themoviedb.data.remote.service.ApiSearchService
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
    private val apiSearchService: ApiSearchService
) : SearchRemoteDataSource {

    override suspend fun getSearchMovies(movieName: String, page: Int) =
        apiSearchService.getSearchMovies(query = movieName, page = page)

    override suspend fun getSearchTv(tvName: String, page: Int) =
        apiSearchService.getSearchTv(query = tvName, page = page)
}
