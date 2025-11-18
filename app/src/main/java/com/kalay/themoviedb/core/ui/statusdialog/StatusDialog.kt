package com.kalay.themoviedb.core.ui.statusdialog

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.theme.Dark3Color
import com.kalay.themoviedb.core.theme.SecondaryColor
import com.kalay.themoviedb.core.theme.font.urbanistTypography
import com.kalay.themoviedb.core.ui.ButtonBrandText

@Composable
fun StatusDialog(
    isVisible: Boolean,
    variant: StatusVariant,
    message: String,
    onConfirm: (() -> Unit)? = null
) {
    if (!isVisible) return

    Dialog(
        onDismissRequest = { if (variant != StatusVariant.LOADING) onConfirm?.invoke() },
        properties = DialogProperties(
            dismissOnBackPress = variant != StatusVariant.LOADING,
            dismissOnClickOutside = variant != StatusVariant.LOADING
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
            when (variant) {
                StatusVariant.LOADING -> {
                    CircularProgressIndicator(color = SecondaryColor)
                    Text(
                        text = message,
                        style = urbanistTypography().typography.headlineMedium.copy(color = Color.White),
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }

                StatusVariant.ERROR,
                StatusVariant.EMPTY -> {
                    Text(
                        text = message,
                        style = urbanistTypography().typography.headlineMedium.copy(
                            color = Color.White,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                    ButtonBrandText(
                        text = stringResource(R.string.ok),
                        onClick = { onConfirm?.invoke() }
                    )
                }
            }
        }
    }
}