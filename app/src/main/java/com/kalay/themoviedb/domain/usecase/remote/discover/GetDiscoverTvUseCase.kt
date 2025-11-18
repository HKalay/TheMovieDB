package com.kalay.themoviedb.domain.usecase.remote.discover

import com.kalay.themoviedb.domain.model.remote.DiscoverDTO
import com.kalay.themoviedb.domain.repository.remote.DiscoverRepository
import javax.inject.Inject

class GetDiscoverTvUseCase @Inject constructor(
    private val repository: DiscoverRepository
) {
    suspend operator fun invoke(page: Int): List<DiscoverDTO> {
        return repository.getDiscoverTv(page)
    }
}