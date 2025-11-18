package com.kalay.themoviedb.feature.favorites.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.ui.statusdialog.StatusDialog
import com.kalay.themoviedb.core.ui.statusdialog.StatusVariant
import com.kalay.themoviedb.core.ui.statusdialog.toStatusDialogConfig
import com.kalay.themoviedb.core.util.Resource
import com.kalay.themoviedb.feature.favorites.presentation.FavoriteViewModel

@Composable
fun FavoritesDialog(favoriteViewModel: FavoriteViewModel) {
    val uiState by favoriteViewModel.uiState.collectAsState()

    val dialog = uiState.favoriteResource.toStatusDialogConfig(
        loadingMessage = stringResource(id = R.string.loading_favorites),
        errorFallbackMessage = stringResource(id = R.string.an_error_occurred),
        emptyMessage = ""
    )

    val shouldShow = when (uiState.favoriteResource) {
        is Resource.Loading,
        is Resource.Error -> true

        else -> false
    }

    StatusDialog(
        isVisible = shouldShow && dialog?.show == true,
        variant = dialog?.variant ?: StatusVariant.LOADING,
        message = dialog?.message ?: "",
        onConfirm = {
            favoriteViewModel.hideDialog()
        }
    )
}
