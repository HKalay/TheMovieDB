package com.kalay.themoviedb.feature.detail.ui.component.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.theme.Dark5Color
import com.kalay.themoviedb.core.theme.font.urbanistTypography
import com.kalay.themoviedb.domain.model.remote.Detail

@Composable
fun Genres(detail: Detail) {
    DetailSection(title = stringResource(R.string.genres)) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(detail.genres) { genre ->
                Surface(
                    color = Dark5Color,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = genre,
                        style = urbanistTypography().typography.bodyMedium.copy(color = Color.White),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}