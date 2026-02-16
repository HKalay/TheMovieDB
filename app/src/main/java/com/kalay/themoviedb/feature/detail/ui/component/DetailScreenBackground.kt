package com.kalay.themoviedb.feature.detail.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kalay.themoviedb.core.theme.PrimaryColor
import com.kalay.themoviedb.core.ui.NetworkImage
import com.kalay.themoviedb.domain.model.remote.Detail

@Composable
fun DetailScreenBackground(detail: Detail){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PrimaryColor)
    ) {
        NetworkImage(
            imageUrl = detail.posterPath,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryColor.copy(alpha = 0.9f))
        )
    }
}