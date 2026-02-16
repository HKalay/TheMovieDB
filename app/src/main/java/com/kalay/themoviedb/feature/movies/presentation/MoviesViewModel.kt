package com.kalay.themoviedb.feature.movies.presentation

import androidx.lifecycle.viewModelScope
import com.kalay.themoviedb.core.util.ErrorResponse
import com.kalay.themoviedb.core.state.PaginationState
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.core.viewmodel.BaseViewModel
import com.kalay.themoviedb.domain.model.remote.DiscoverDTO
import com.kalay.themoviedb.domain.usecase.remote.discover.GetDiscoverMoviesUseCase
import com.kalay.themoviedb.domain.usecase.remote.search.GetSearchMoviesUseCase
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
class MoviesViewModel @Inject constructor(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    private val searchQueryFlow = uiState
        .map { it.searchQuery }
        .distinctUntilChanged()

    private val _paginationState = MutableStateFlow(PaginationState<DiscoverDTO>())
    private val paginationState: PaginationState<DiscoverDTO> get() = _paginationState.value

    init {
        ensureFirstPageLoaded()
        observeSearchQuery()
    }

    fun ensureFirstPageLoaded() {
        if (paginationState.items.isEmpty() && !paginationState.isLoading) {
            fetchDiscoverMovies()
        }
    }

    fun fetchDiscoverMovies() {
        val state = paginationState
        if (state.isLoading || state.isLastPage) return

        _paginationState.update { it.startLoading() }
        if (state.items.isEmpty()) {
            _uiState.update { it.copy(movieListResource = Resource.Loading) }
        } else {
            _uiState.update { it.copy(isPaginating = true) }
        }

        safeLaunch(
            block = {
                if (_uiState.value.isSearchMode) {
                    getSearchMoviesUseCase(state.currentQuery, state.currentPage)
                } else {
                    getDiscoverMoviesUseCase(state.currentPage)
                }
            },
            onSuccess = { result ->
                _paginationState.update { it.appendPage(result) }
                _uiState.update {
                    it.copy(
                        movieListResource = Resource.Success(_paginationState.value.items),
                        isPaginating = false
                    )
                }
            },
            onError = { error ->
                _paginationState.update { it.setLoadingFailed() }
                _uiState.update {
                    it.copy(
                        movieListResource = Resource.Error(
                            error = ErrorResponse(
                                message = error.message ?: "An unknown error has occurred."
                            )
                        ),
                        isPaginating = false
                    )
                }
            }
        )
    }

    fun startSearch(query: String) {
        resetState(true, query)
        fetchDiscoverMovies()
    }

    fun cancelSearch() {
        resetState(false)
        fetchDiscoverMovies()
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun setSearchMode(enabled: Boolean) {
        _uiState.update { it.copy(isSearchMode = enabled) }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(500)
                .filter { it.length >= 3 || it.isEmpty() }
                .drop(1)
                .collect { query ->
                    if (query.isNotEmpty()) {
                        startSearch(query)
                    } else {
                        cancelSearch()
                    }
                }
        }
    }

    private fun resetState(isSearch: Boolean, query: String = "") {
        _uiState.update {
            it.copy(
                isSearchMode = isSearch,
                movieListResource = Resource.Loading
            )
        }
        _paginationState.update { it.reset(query) }
    }

    fun hideDialog() {
        if (_uiState.value.movieListResource is Resource.Error || _uiState.value.movieListResource is Resource.Empty) {
            _uiState.update { it.copy(movieListResource = Resource.Success(paginationState.items)) }
        }
    }
}