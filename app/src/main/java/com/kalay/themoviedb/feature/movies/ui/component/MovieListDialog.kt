package com.kalay.themoviedb.feature.movies.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.ui.statusdialog.StatusDialog
import com.kalay.themoviedb.core.ui.statusdialog.StatusVariant
import com.kalay.themoviedb.core.ui.statusdialog.toStatusDialogConfig
import com.kalay.themoviedb.feature.movies.presentation.MoviesViewModel

@Composable
fun MovieListDialog(viewModel: MoviesViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val dialog = uiState.movieListResource.toStatusDialogConfig(
        loadingMessage = stringResource(id = R.string.loading_movies),
        emptyMessage = stringResource(id = R.string.no_movie_found),
        errorFallbackMessage = stringResource(id = R.string.an_error_occurred)
    )

    StatusDialog(
        isVisible = dialog?.show == true || uiState.isPaginating,
        variant = if (uiState.isPaginating) StatusVariant.LOADING else dialog?.variant
            ?: StatusVariant.LOADING,
        message = if (uiState.isPaginating) {
            stringResource(id = R.string.loading_movies)
        } else {
            dialog?.message ?: ""
        },
        onConfirm = {
            viewModel.hideDialog()
        }
    )
}