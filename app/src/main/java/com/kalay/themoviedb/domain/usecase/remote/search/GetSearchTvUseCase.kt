package com.kalay.themoviedb.domain.usecase.remote.search

import com.kalay.themoviedb.domain.model.remote.Discover
import com.kalay.themoviedb.domain.repository.remote.SearchRepository
import javax.inject.Inject

class GetSearchTvUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(tvName: String, page: Int): List<Discover> {
        return repository.getSearchTv(tvName = tvName, page = page)
    }
}