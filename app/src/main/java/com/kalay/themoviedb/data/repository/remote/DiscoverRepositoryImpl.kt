package com.kalay.themoviedb.data.repository.remote

import com.kalay.themoviedb.data.mapper.toDiscover
import com.kalay.themoviedb.data.remote.datasource.discover.DiscoverRemoteDataSource
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.Discover
import com.kalay.themoviedb.domain.repository.remote.DiscoverRepository
import javax.inject.Inject

class DiscoverRepositoryImpl @Inject constructor(
    private val remoteDataSource: DiscoverRemoteDataSource
) : DiscoverRepository {

    override suspend fun getDiscoverMovies(page: Int): List<Discover> {
        val response = remoteDataSource.getDiscoverMovies(page = page)
        return response.results.map { it.toDiscover(detailType = DetailType.MOVIE) }
    }

    override suspend fun getDiscoverTv(page: Int): List<Discover> {
        val response = remoteDataSource.getDiscoverTv(page = page)
        return response.results.map { it.toDiscover(detailType = DetailType.TV_SHOW) }
    }
}