package com.kalay.themoviedb.feature.tvshows.presentation

import androidx.lifecycle.viewModelScope
import com.kalay.themoviedb.core.util.ErrorResponse
import com.kalay.themoviedb.core.state.PaginationState
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.core.base.BaseViewModel
import com.kalay.themoviedb.domain.model.remote.Discover
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverTvUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchTvUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val getDiscoverTvUseCase: GetDiscoverTvUseCase,
    private val getSearchTvUseCase: GetSearchTvUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(TvShowUiState())
    val uiState: StateFlow<TvShowUiState> = _uiState.asStateFlow()

    private val searchQueryFlow = uiState
        .map { it.searchQuery }
        .distinctUntilChanged()

    private val _paginationState = MutableStateFlow(PaginationState<Discover>())
    private val paginationState: PaginationState<Discover> get() = _paginationState.value

    init {
        ensureFirstPageLoaded()
        observeSearchQuery()
    }

    fun ensureFirstPageLoaded() {
        if (paginationState.items.isEmpty() && !paginationState.isLoading) {
            fetchDiscoverTv()
        }
    }

    fun fetchDiscoverTv() {
        val state = paginationState
        if (state.isLoading || state.isLastPage) return

        _paginationState.update { it.startLoading() }
        if (state.items.isEmpty()) {
            _uiState.update { it.copy(tvShowListResource = Resource.Loading) }
        } else {
            _uiState.update { it.copy(isPaginating = true) }
        }

        safeLaunch(
            block = {
                if (_uiState.value.isSearchMode) {
                    getSearchTvUseCase(state.currentQuery, state.currentPage)
                } else {
                    getDiscoverTvUseCase(state.currentPage)
                }
            },
            onSuccess = { result ->
                _paginationState.update { it.appendPage(result) }
                _uiState.update {
                    it.copy(
                        tvShowListResource = Resource.Success(_paginationState.value.items),
                        isPaginating = false
                    )
                }
            },
            onError = { error ->
                _paginationState.update { it.setLoadingFailed() }
                _uiState.update {
                    it.copy(
                        tvShowListResource = Resource.Error(
                            error = ErrorResponse(message = error.message ?: "Unknown error")
                        ),
                        isPaginating = false
                    )
                }
            }
        )
    }

    fun startSearch(query: String) {
        resetState(true, query)
        fetchDiscoverTv()
    }

    fun cancelSearch() {
        resetState(false)
        fetchDiscoverTv()
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun setSearchMode(enabled: Boolean) {
        _uiState.update { it.copy(isSearchMode = enabled) }
    }

    private fun resetState(isSearch: Boolean, query: String = "") {
        _uiState.update {
            it.copy(
                isSearchMode = isSearch,
                tvShowListResource = Resource.Loading
            )
        }
        _paginationState.update { it.reset(query) }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(500)
                .filter { it.length >= 3 || it.isEmpty() }
                .drop(1)
                .collect { query ->
                    if (query.isNotEmpty()) startSearch(query) else cancelSearch()
                }
        }
    }

    fun hideDialog() {
        if (_uiState.value.tvShowListResource is Resource.Error || _uiState.value.tvShowListResource is Resource.Empty) {
            _uiState.update { it.copy(tvShowListResource = Resource.Success(paginationState.items)) }
        }
    }
}