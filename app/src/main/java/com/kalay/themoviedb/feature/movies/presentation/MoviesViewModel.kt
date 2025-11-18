package com.kalay.themoviedb.feature.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kalay.themoviedb.core.util.ErrorResponse
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.core.util.safeLaunch
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
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    private val searchQueryFlow = uiState
        .map { it.searchQuery }
        .distinctUntilChanged()

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false
    private var currentQuery: String = ""
    private val movies = mutableListOf<DiscoverDTO>()

    init {
        ensureFirstPageLoaded()
        observeSearchQuery()
    }

    fun ensureFirstPageLoaded() {
        if (movies.isEmpty() && !isLoading) {
            fetchDiscoverMovies()
        }
    }

    fun fetchDiscoverMovies() {
        if (isLoading || isLastPage) return
        isLoading = true

        if (movies.isEmpty()) {
            _uiState.update { it.copy(movieListResource = Resource.Loading) }
        } else {
            _uiState.update { it.copy(isPaginating = true) }
        }

        safeLaunch(
            block = {
                if (_uiState.value.isSearchMode) {
                    getSearchMoviesUseCase(currentQuery, currentPage)
                } else {
                    getDiscoverMoviesUseCase(currentPage)
                }
            },
            onSuccess = { result ->
                if (result.isEmpty()) {
                    isLastPage = true
                } else {
                    movies.addAll(result)
                    currentPage++
                }
                _uiState.update { 
                    it.copy(
                        movieListResource = Resource.Success(movies.toList()),
                        isPaginating = false
                    )
                }
                isLoading = false
            },
            onError = { error ->
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
                isLoading = false
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
        currentQuery = query
        currentPage = 1
        isLastPage = false
        movies.clear()
    }

    fun hideDialog() {
        if (_uiState.value.movieListResource is Resource.Error || _uiState.value.movieListResource is Resource.Empty) {
            _uiState.update { it.copy(movieListResource = Resource.Success(movies.toList())) }
        }
    }
}