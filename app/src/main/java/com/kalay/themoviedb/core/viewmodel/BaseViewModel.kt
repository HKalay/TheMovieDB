package com.kalay.themoviedb.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected inline fun <T> safeLaunch(
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

    protected fun <T> collectFlow(
        flow: Flow<T>,
        onEach: (T) -> Unit,
        onError: (Throwable) -> Unit = {}
    ) {
        flow
            .onEach(onEach)
            .catch { onError(it) }
            .launchIn(viewModelScope)
    }
}
