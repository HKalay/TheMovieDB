package com.kalay.themoviedb.data.repository.remote

import com.kalay.themoviedb.data.mapper.toDiscover
import com.kalay.themoviedb.data.remote.datasource.search.SearchRemoteDataSource
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Discover
import com.kalay.themoviedb.domain.repository.remote.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource
) : SearchRepository {

    override suspend fun getSearchMovies(movieName: String, page: Int): List<Discover> {
        val response = remoteDataSource.getSearchMovies(movieName = movieName, page = page)
        return response.resultDTOS.map { it.toDiscover(detailType = DetailType.MOVIE) }
    }

    override suspend fun getSearchTv(tvName: String, page: Int): List<Discover> {
        val response = remoteDataSource.getSearchTv(tvName=tvName,page = page)
        return response.resultDTOS.map { it.toDiscover(detailType = DetailType.TV_SHOW) }
    }
}