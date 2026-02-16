package com.kalay.themoviedb.domain.usecase.remote.detail

import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.repository.remote.DetailRepository
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(id: Int): Detail {
        return repository.getDetailMovie(id)
    }
}