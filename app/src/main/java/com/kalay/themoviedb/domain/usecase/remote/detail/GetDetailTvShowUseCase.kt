package com.kalay.themoviedb.domain.usecase.remote.detail

import com.kalay.themoviedb.domain.model.remote.DetailDTO
import com.kalay.themoviedb.domain.repository.remote.DetailRepository
import javax.inject.Inject

class GetDetailTvShowUseCase @Inject constructor(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(id: Int): DetailDTO {
        return repository.getDetailTvShow(id)
    }
}