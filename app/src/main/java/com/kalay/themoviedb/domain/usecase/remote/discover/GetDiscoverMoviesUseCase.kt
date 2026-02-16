package com.kalay.themoviedb.domain.usecase.remote.discover

import com.kalay.themoviedb.domain.model.remote.Discover
import com.kalay.themoviedb.domain.repository.remote.DiscoverRepository
import javax.inject.Inject

class GetDiscoverMoviesUseCase @Inject constructor(
    private val repository: DiscoverRepository
) {
    suspend operator fun invoke(page: Int): List<Discover> {
        return repository.getDiscoverMovies(page)
    }
}