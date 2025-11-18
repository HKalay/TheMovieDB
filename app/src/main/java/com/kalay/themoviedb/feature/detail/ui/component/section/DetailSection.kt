package com.kalay.themoviedb.feature.detail.ui.component.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.core.theme.Dark3Color
import com.kalay.themoviedb.core.theme.SecondaryColor
import com.kalay.themoviedb.core.theme.font.urbanistTypography

@Composable
fun DetailSection(
    title: String,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 12.dp)
            .background(
                color = Dark3Color,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(modifier = Modifier.offset(y = (-12).dp)) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .background(
                        color = SecondaryColor,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    text = title,
                    style = urbanistTypography().bodyXLarge.copy(color = Color.White)
                )
            }

            content()
        }
    }
}
