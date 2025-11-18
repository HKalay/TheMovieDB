package com.kalay.themoviedb.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kalay.themoviedb.core.theme.Dark3Color
import com.kalay.themoviedb.core.theme.SecondaryColor
import com.kalay.themoviedb.core.theme.font.urbanistTypography

@Composable
fun LoadingDialog(
    isVisible: Boolean,
    message: String
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = {  },
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .wrapContentHeight()
                    .background(
                        color = Dark3Color,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = SecondaryColor)
                Text(
                    text = message,
                    style = urbanistTypography().typography.headlineMedium.copy(color = Color.White),
                    modifier = Modifier.padding(top = 24.dp)
                )
            }
        }
    }
}