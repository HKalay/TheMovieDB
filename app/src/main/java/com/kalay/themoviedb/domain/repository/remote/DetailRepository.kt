package com.kalay.themoviedb.domain.repository.remote

import com.kalay.themoviedb.domain.model.remote.DetailDTO

interface DetailRepository {
    suspend fun getDetailMovie(id: Int): DetailDTO

    suspend fun getDetailTvShow(id: Int): DetailDTO
}
