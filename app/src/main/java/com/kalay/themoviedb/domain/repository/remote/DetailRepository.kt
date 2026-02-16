package com.kalay.themoviedb.domain.repository.remote

import com.kalay.themoviedb.domain.model.remote.Detail

interface DetailRepository {
    suspend fun getDetailMovie(id: Int): Detail

    suspend fun getDetailTvShow(id: Int): Detail
}
