package com.kalay.themoviedb.feature.detail.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.ui.statusdialog.StatusDialog
import com.kalay.themoviedb.core.ui.statusdialog.StatusVariant
import com.kalay.themoviedb.core.ui.statusdialog.toStatusDialogConfig
import com.kalay.themoviedb.feature.detail.presentation.DetailViewModel

@Composable
fun DetailDialog(navController: NavController, viewModel: DetailViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    val dialog = uiState.detailResource.toStatusDialogConfig(
        loadingMessage = stringResource(id = R.string.loading_detail),
        emptyMessage = stringResource(id = R.string.no_detail_found),
        errorFallbackMessage = stringResource(id = R.string.an_error_occurred)
    )

    StatusDialog(
        isVisible = dialog?.show == true,
        variant = dialog?.variant ?: StatusVariant.LOADING,
        message = dialog?.message ?: "",
        onConfirm = {
            viewModel.hideDialog()
            navController.popBackStack()
        }
    )
}
