package com.kalay.themoviedb.data.di.network

import com.kalay.themoviedb.data.remote.service.ApiDetailService
import com.kalay.themoviedb.data.remote.service.ApiDiscoverService
import com.kalay.themoviedb.data.remote.service.ApiSearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideDiscoverService(retrofit: Retrofit): ApiDiscoverService {
        return retrofit.create(ApiDiscoverService::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailService(retrofit: Retrofit): ApiDetailService {
        return retrofit.create(ApiDetailService::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): ApiSearchService {
        return retrofit.create(ApiSearchService::class.java)
    }
}