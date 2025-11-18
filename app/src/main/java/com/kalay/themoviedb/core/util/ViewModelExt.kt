package com.kalay.themoviedb.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * ViewModel extension functions for common operations
 */

/**
 * Executes suspend function safely with error handling
 */
inline fun <T> ViewModel.safeLaunch(
    crossinline block: suspend () -> T,
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onSuccess: (T) -> Unit = {}
) {
    viewModelScope.launch {
        try {
            val result = block()
            onSuccess(result)
        } catch (e: Exception) {
            onError(e)
        }
    }
}

/**
 * Collects Flow with error handling
 */
fun <T> ViewModel.collectFlow(
    flow: Flow<T>,
    onEach: (T) -> Unit,
    onError: (Throwable) -> Unit = {}
) {
    flow
        .onEach(onEach)
        .catch { onError(it) }
        .launchIn(viewModelScope)
}