package com.kalay.themoviedb.domain.repository.remote

import com.kalay.themoviedb.domain.model.remote.Discover

interface DiscoverRepository {
    suspend fun getDiscoverMovies(page: Int): List<Discover>
    suspend fun getDiscoverTv(page: Int): List<Discover>
}
