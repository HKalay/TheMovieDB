package com.kalay.themoviedb.domain.repository.remote

import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

interface SearchRepository {
    suspend fun getSearchMovies(movieName: String, page: Int): List<DiscoverDTO>
    suspend fun getSearchTv(tvName: String, page: Int): List<DiscoverDTO>
}
