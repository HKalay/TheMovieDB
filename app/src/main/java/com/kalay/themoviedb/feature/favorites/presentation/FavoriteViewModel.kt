package com.kalay.themoviedb.feature.favorites.presentation

import com.kalay.themoviedb.core.util.ErrorResponse
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.core.base.BaseViewModel
import com.kalay.themoviedb.domain.mapper.toFavorite
import com.kalay.themoviedb.domain.model.remote.Detail
import com.kalay.themoviedb.domain.model.remote.Discover
import com.kalay.themoviedb.domain.usecase.local.DeleteFromFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.GetAllFavoritesUseCase
import com.kalay.themoviedb.domain.usecase.local.InsertToFavoriteUseCase
import com.kalay.themoviedb.domain.usecase.local.IsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val insertToFavoriteUseCase: InsertToFavoriteUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : BaseViewModel() {

    private val _favoritesMap = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val favoritesMap: StateFlow<Map<Int, Boolean>> = _favoritesMap.asStateFlow()

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    fun syncFavoriteState(discover: Discover) {
        safeLaunch(
            block = { isFavoriteUseCase(discover.id) },
            onSuccess = { isFav ->
                _favoritesMap.value = _favoritesMap.value.toMutableMap().apply {
                    put(discover.id, isFav)
                }
            }
        )
    }

    fun syncFavoriteState(detail: Detail) {
        safeLaunch(
            block = { isFavoriteUseCase(detail.id) },
            onSuccess = { isFav ->
                _favoritesMap.value = _favoritesMap.value.toMutableMap().apply {
                    put(detail.id, isFav)
                }
            }
        )
    }

    fun toggleFavorite(discover: Discover) {
        safeLaunch(
            block = { isFavoriteUseCase(discover.id) },
            onSuccess = { current ->
                val favoriteDTO = discover.toFavorite()
                safeLaunch(
                    block = {
                        if (current) {
                            deleteFromFavoritesUseCase(discover.id)
                        } else {
                            insertToFavoriteUseCase(favoriteDTO)
                        }
                    },
                    onSuccess = {
                        _favoritesMap.value = _favoritesMap.value.toMutableMap().apply {
                            put(discover.id, !current)
                        }
                        fetchFavorites()
                    }
                )
            }
        )
    }

    fun toggleFavorite(detail: Detail) {
        safeLaunch(
            block = { isFavoriteUseCase(detail.id) },
            onSuccess = { current ->
                val favoriteDTO = detail.toFavorite()
                safeLaunch(
                    block = {
                        if (current) {
                            deleteFromFavoritesUseCase(detail.id)
                        } else {
                            insertToFavoriteUseCase(favoriteDTO)
                        }
                    },
                    onSuccess = {
                        _favoritesMap.value = _favoritesMap.value.toMutableMap().apply {
                            put(detail.id, !current)
                        }
                        fetchFavorites()
                    }
                )
            }
        )
    }

    fun deleteById(id: Int) {
        safeLaunch(
            block = { deleteFromFavoritesUseCase(id) },
            onSuccess = {
                _favoritesMap.value = _favoritesMap.value.toMutableMap().apply {
                    put(id, false)
                }
                fetchFavorites()
            }
        )
    }

    fun fetchFavorites() {
        collectFlow(
            flow = getAllFavoritesUseCase(),
            onEach = { data ->
                _uiState.update { it.copy(favoriteResource = Resource.Success(data)) }
            },
            onError = { error ->
                _uiState.update {
                    it.copy(
                        favoriteResource = Resource.Error(
                            ErrorResponse(message = error.message ?: "An unknown error has occurred.")
                        )
                    )
                }
            }
        )
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        applySearch()
    }

    private fun applySearch() {
        collectFlow(
            flow = getAllFavoritesUseCase(),
            onEach = { data ->
                // getAllFavoritesUseCase already returns Flow<List<Favorite>>, no mapping needed
                val filtered = if (_uiState.value.searchQuery.isBlank()) {
                    data
                } else {
                    data.filter { fav ->
                        fav.title.contains(_uiState.value.searchQuery, ignoreCase = true)
                    }
                }
                _uiState.update { it.copy(favoriteResource = Resource.Success(filtered)) }
            },
            onError = { error ->
                _uiState.update {
                    it.copy(
                        favoriteResource = Resource.Error(
                            ErrorResponse(message = error.message ?: "An unknown error has occurred.")
                        )
                    )
                }
            }
        )
    }

    fun setSearchMode(enabled: Boolean) {
        _uiState.update { it.copy(isSearchMode = enabled) }
    }

    fun hideDialog() {
        if (_uiState.value.favoriteResource is Resource.Error || _uiState.value.favoriteResource is Resource.Empty) {
            val prev = (_uiState.value.favoriteResource as? Resource.Success)?.data
            if (prev != null) {
                _uiState.update { it.copy(favoriteResource = Resource.Success(prev)) }
            }
        }
    }
}