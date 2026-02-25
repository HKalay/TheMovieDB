package com.kalay.themoviedb.core.state

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PaginationStateTest {

    @Test
    fun `Given state with page and items, When reset with empty query called, Then state is cleared and query is empty`() {
        val state = PaginationState(
            currentPage = 3,
            isLoading = true,
            isLastPage = false,
            currentQuery = "old",
            items = listOf(1, 2, 3)
        )

        val result = state.reset("")

        assertEquals(1, result.currentPage)
        assertFalse(result.isLoading)
        assertFalse(result.isLastPage)
        assertEquals("", result.currentQuery)
        assertTrue(result.items.isEmpty())
    }

    @Test
    fun `Given state with page 1 and one item, When appendPage called with new items, Then page increments and items appended`() {
        val state = PaginationState(currentPage = 1, items = listOf("a"))

        val result = state.appendPage(listOf("b", "c"))

        assertEquals(2, result.currentPage)
        assertFalse(result.isLoading)
        assertFalse(result.isLastPage)
        assertEquals(listOf("a", "b", "c"), result.items)
    }

    @Test
    fun `Given state with items, When appendPage called with empty list, Then isLastPage is true`() {
        val state = PaginationState(currentPage = 1, items = listOf("a"))

        val result = state.appendPage(emptyList())

        assertTrue(result.isLastPage)
        assertFalse(result.isLoading)
        assertEquals(listOf("a"), result.items)
    }

    @Test
    fun `Given isLoading false, When startLoading called, Then isLoading is true`() {
        val state = PaginationState<Unit>(isLoading = false)

        val result = state.startLoading()

        assertTrue(result.isLoading)
    }

    @Test
    fun `Given isLoading true, When setLoadingFailed called, Then isLoading is false`() {
        val state = PaginationState<Unit>(isLoading = true)

        val result = state.setLoadingFailed()

        assertFalse(result.isLoading)
    }
}
