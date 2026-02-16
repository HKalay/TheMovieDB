package com.kalay.themoviedb.domain.repository.remote

import com.kalay.themoviedb.domain.model.remote.Discover

interface SearchRepository {
    suspend fun getSearchMovies(movieName: String, page: Int): List<Discover>
    suspend fun getSearchTv(tvName: String, page: Int): List<Discover>
}
