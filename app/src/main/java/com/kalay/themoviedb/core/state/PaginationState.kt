package com.kalay.themoviedb.core.state

data class PaginationState<T>(
    val currentPage: Int = 1,
    val isLoading: Boolean = false,
    val isLastPage: Boolean = false,
    val currentQuery: String = "",
    val items: List<T> = emptyList()
) {
    fun reset(query: String = "") = copy(
        currentPage = 1,
        isLoading = false,
        isLastPage = false,
        currentQuery = query,
        items = emptyList()
    )

    fun appendPage(newItems: List<T>) = copy(
        currentPage = currentPage + 1,
        isLoading = false,
        isLastPage = newItems.isEmpty(),
        items = items + newItems
    )

    fun startLoading() = copy(isLoading = true)

    fun setLoadingFailed() = copy(isLoading = false)
}
