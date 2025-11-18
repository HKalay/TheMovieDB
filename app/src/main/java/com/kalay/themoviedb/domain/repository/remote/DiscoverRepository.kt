package com.kalay.themoviedb.domain.repository.remote

import com.kalay.themoviedb.domain.model.remote.DiscoverDTO

interface DiscoverRepository {
    suspend fun getDiscoverMovies(page: Int): List<DiscoverDTO>
    suspend fun getDiscoverTv(page: Int): List<DiscoverDTO>
}
