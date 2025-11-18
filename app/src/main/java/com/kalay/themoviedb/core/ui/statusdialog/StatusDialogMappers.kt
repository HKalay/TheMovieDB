package com.kalay.themoviedb.core.ui.statusdialog

import com.kalay.themoviedb.core.util.Resource

fun <T> Resource<T>.toStatusDialogConfig(
    loadingMessage: String,
    emptyMessage: String,
    errorFallbackMessage: String
): StatusDialogDTO? {
    return when (this) {
        is Resource.Loading -> StatusDialogDTO(true, StatusVariant.LOADING, loadingMessage)
        is Resource.Error -> {
            val msg = error.message?.takeIf { it.isNotBlank() } ?: errorFallbackMessage
            StatusDialogDTO(true, StatusVariant.ERROR, msg)
        }

        is Resource.Empty -> StatusDialogDTO(true, StatusVariant.EMPTY, emptyMessage)
        is Resource.Success -> null
    }
}