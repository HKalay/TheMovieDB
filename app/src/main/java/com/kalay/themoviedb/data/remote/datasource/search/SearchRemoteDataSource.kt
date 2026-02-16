package com.kalay.themoviedb.data.remote.datasource.search

import com.kalay.themoviedb.data.remote.response.ServiceResponse

interface SearchRemoteDataSource {
    suspend fun getSearchMovies(movieName: String, page: Int): ServiceResponse
    suspend fun getSearchTv(tvName: String, page: Int): ServiceResponse
}
