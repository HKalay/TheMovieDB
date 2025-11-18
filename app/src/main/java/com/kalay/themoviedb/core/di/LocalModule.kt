package com.kalay.themoviedb.core.di

import android.content.Context
import com.kalay.themoviedb.core.sharedpref.SharedPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideSharedPrefManager(
        @ApplicationContext context: Context
    ): SharedPrefManager {
        return SharedPrefManager(context)
    }
}