package com.kalay.themoviedb.feature.detail.presentation

import androidx.lifecycle.ViewModel
import com.kalay.themoviedb.core.util.ErrorResponse
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.core.util.safeLaunch
import com.kalay.themoviedb.domain.enums.DetailType
import com.kalay.themoviedb.domain.model.remote.DetailDTO
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailMovieUseCase
import com.kalay.themoviedb.domain.usecase.remote.detail.GetDetailTvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val getDetailTvShowUseCase: GetDetailTvShowUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun fetchDetail(detailDTO: DetailDTO) {
        safeLaunch(
            block = {
                when (detailDTO.detailType) {
                    DetailType.MOVIE -> getDetailMovieUseCase(detailDTO.id)
                    DetailType.TV_SHOW -> getDetailTvShowUseCase(detailDTO.id)
                }
            },
            onSuccess = { data ->
                _uiState.update { it.copy(detailResource = Resource.Success(data)) }
            },
            onError = { error ->
                _uiState.update {
                    it.copy(
                        detailResource = Resource.Error(
                            error = ErrorResponse(
                                message = error.message ?: "An unknown error has occurred."
                            )
                        )
                    )
                }
            }
        )
    }

    fun updateIsFavorite(isFavorite: Boolean) {
        _uiState.update { it.copy(isFavorite = isFavorite) }
    }

    fun hideDialog() {
        if (_uiState.value.detailResource is Resource.Error || _uiState.value.detailResource is Resource.Empty) {
            _uiState.update { it.copy(detailResource = Resource.Empty) }
        }
    }
}