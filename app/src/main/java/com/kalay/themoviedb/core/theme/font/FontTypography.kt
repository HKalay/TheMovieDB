package com.kalay.themoviedb.core.theme.font

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle

data class FontTypography(
    val typography: Typography,
    val bodyXLarge: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val bodyXSmall: TextStyle
)