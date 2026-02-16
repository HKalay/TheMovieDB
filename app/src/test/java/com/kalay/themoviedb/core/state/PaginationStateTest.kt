package com.kalay.themoviedb.core.state

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PaginationStateTest {

    @Test
    fun `reset with query clears state and sets query`() {
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
    fun `appendPage increments page and appends items`() {
        val state = PaginationState(currentPage = 1, items = listOf("a"))

        val result = state.appendPage(listOf("b", "c"))

        assertEquals(2, result.currentPage)
        assertFalse(result.isLoading)
        assertFalse(result.isLastPage)
        assertEquals(listOf("a", "b", "c"), result.items)
    }

    @Test
    fun `appendPage empty list sets isLastPage`() {
        val state = PaginationState(currentPage = 1, items = listOf("a"))

        val result = state.appendPage(emptyList())

        assertTrue(result.isLastPage)
        assertFalse(result.isLoading)
        assertEquals(listOf("a"), result.items)
    }

    @Test
    fun `startLoading sets isLoading true`() {
        val state = PaginationState<Unit>(isLoading = false)

        val result = state.startLoading()

        assertTrue(result.isLoading)
    }

    @Test
    fun `setLoadingFailed sets isLoading false`() {
        val state = PaginationState<Unit>(isLoading = true)

        val result = state.setLoadingFailed()

        assertFalse(result.isLoading)
    }
}
