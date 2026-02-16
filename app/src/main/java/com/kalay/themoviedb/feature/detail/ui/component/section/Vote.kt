package com.kalay.themoviedb.feature.detail.ui.component.section

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.theme.font.urbanistTypography
import com.kalay.themoviedb.domain.model.remote.Detail

@Composable
fun Vote(detail: Detail) {
    DetailSection(title = stringResource(R.string.vote)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_vote),
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier
                    .size(16.dp)
                    .padding(end = 4.dp)
            )

            Text(
                text = detail.voteAverage.toString(),
                style = urbanistTypography().typography.bodyMedium.copy(color = Color.White)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "-",
                style = urbanistTypography().typography.bodySmall.copy(color = Color.Gray)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = detail.voteCount.toString(),
                style = urbanistTypography().typography.bodyMedium.copy(color = Color.White)
            )
        }
    }
}