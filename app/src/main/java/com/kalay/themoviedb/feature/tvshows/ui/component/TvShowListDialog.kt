package com.kalay.themoviedb.feature.tvshows.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.ui.statusdialog.StatusDialog
import com.kalay.themoviedb.core.ui.statusdialog.StatusVariant
import com.kalay.themoviedb.core.ui.statusdialog.toStatusDialogConfig
import com.kalay.themoviedb.feature.tvshows.presentation.TvShowsViewModel

@Composable
fun TvShowListDialog(viewModel: TvShowsViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val dialog = uiState.tvShowListResource.toStatusDialogConfig(
        loadingMessage = stringResource(id = R.string.loading_tv_shows),
        emptyMessage = stringResource(id = R.string.no_tv_shows_found),
        errorFallbackMessage = stringResource(id = R.string.an_error_occurred)
    )

    StatusDialog(
        isVisible = dialog?.show == true || uiState.isPaginating,
        variant = if (uiState.isPaginating) StatusVariant.LOADING else dialog?.variant
            ?: StatusVariant.LOADING,
        message = if (uiState.isPaginating) {
            stringResource(id = R.string.loading_tv_shows)
        } else {
            dialog?.message ?: ""
        },
        onConfirm = {
            viewModel.hideDialog()
        }
    )
}