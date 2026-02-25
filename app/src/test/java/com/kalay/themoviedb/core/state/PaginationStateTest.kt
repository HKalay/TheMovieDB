package com.kalay.themoviedb.core.state

import com.google.common.truth.Truth
import org.junit.Test

class PaginationStateTest {

    @Test
    fun `Given state with page and items, When reset with empty query called, Then state is cleared and query is empty`() {
        // Given
        val state = PaginationState(
            currentPage = 3,
            isLoading = true,
            isLastPage = false,
            currentQuery = "old",
            items = listOf(1, 2, 3)
        )

        // When
        val result = state.reset("")

        // Then
        result.apply {
            Truth.assertThat(currentPage).isEqualTo(1)
            Truth.assertThat(isLoading).isFalse()
            Truth.assertThat(isLastPage).isFalse()
            Truth.assertThat(currentQuery).isEqualTo("")
            Truth.assertThat(items).isEmpty()
        }
    }

    @Test
    fun `Given state with page 1 and one item, When appendPage called with new items, Then page increments and items appended`() {
        // Given
        val state = PaginationState(currentPage = 1, items = listOf("a"))
        val newItems = listOf("b", "c")

        // When
        val result = state.appendPage(newItems)

        // Then
        result.apply {
            Truth.assertThat(currentPage).isEqualTo(2)
            Truth.assertThat(isLoading).isFalse()
            Truth.assertThat(isLastPage).isFalse()
            Truth.assertThat(items).containsExactly("a", "b", "c").inOrder()
        }
    }

    @Test
    fun `Given state with items, When appendPage called with empty list, Then isLastPage is true`() {
        // Given
        val state = PaginationState(currentPage = 1, items = listOf("a"))

        // When
        val result = state.appendPage(emptyList())

        // Then
        result.apply {
            Truth.assertThat(isLastPage).isTrue()
            Truth.assertThat(isLoading).isFalse()
            Truth.assertThat(items).containsExactly("a")
        }
    }

    @Test
    fun `Given isLoading false, When startLoading called, Then isLoading is true`() {
        // Given
        val state = PaginationState<Unit>(isLoading = false)

        // When
        val result = state.startLoading()

        // Then
        result.apply {
            Truth.assertThat(isLoading).isTrue()
        }
    }

    @Test
    fun `Given isLoading true, When setLoadingFailed called, Then isLoading is false`() {
        // Given
        val state = PaginationState<Unit>(isLoading = true)

        // When
        val result = state.setLoadingFailed()

        // Then
        result.apply {
            Truth.assertThat(isLoading).isFalse()
        }
    }
}
