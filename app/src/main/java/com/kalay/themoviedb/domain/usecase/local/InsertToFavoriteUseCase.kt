package com.kalay.themoviedb.domain.usecase.local

import com.kalay.themoviedb.domain.model.local.Favorite
import com.kalay.themoviedb.domain.repository.local.FavoriteRepository
import javax.inject.Inject

class InsertToFavoriteUseCase @Inject constructor(
    private val repository: FavoriteRepository
) {
    suspend operator fun invoke(favorite: Favorite) = repository.insertFavorite(favorite)
}