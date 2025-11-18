package com.kalay.themoviedb.data.remote.datasource.search

import com.kalay.themoviedb.data.remote.response.ServiceResponseDTO

interface SearchRemoteDataSource {
    suspend fun getSearchMovies(movieName: String, page: Int): ServiceResponseDTO
    suspend fun getSearchTv(tvName: String, page: Int): ServiceResponseDTO
}
