package com.kalay.themoviedb.feature.detail.ui.component.section

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.theme.font.urbanistTypography
import com.kalay.themoviedb.domain.model.remote.DetailDTO

@Composable
fun ReleaseDate(detail: DetailDTO) {
    DetailSection(title = stringResource(R.string.release_date)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = detail.releaseDate.toString(),
                style = urbanistTypography().typography.bodyMedium.copy(color = Color.White)
            )
        }
    }
}