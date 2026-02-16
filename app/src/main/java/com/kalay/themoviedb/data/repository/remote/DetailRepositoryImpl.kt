package com.kalay.themoviedb.data.repository.remote

import com.kalay.themoviedb.data.mapper.toDetail
import com.kalay.themoviedb.data.remote.datasource.detail.DetailRemoteDataSource
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.repository.remote.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: DetailRemoteDataSource
) : DetailRepository {

    override suspend fun getDetailMovie(id: Int): Detail {
        val response = remoteDataSource.getMovieDetail(id = id)
        return response.toDetail(detailType = DetailType.MOVIE)
    }

    override suspend fun getDetailTvShow(id: Int): Detail {
        val response = remoteDataSource.getTvShowDetail(id = id)
        return response.toDetail(detailType = DetailType.TV_SHOW)
    }
}