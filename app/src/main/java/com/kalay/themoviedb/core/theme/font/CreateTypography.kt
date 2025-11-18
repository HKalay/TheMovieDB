package com.kalay.themoviedb.core.theme.font

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

fun createTypography(fontFamily: FontFamily): FontTypography {
    val displayLargeFontSize = 48.sp
    val displayMediumFontSize = 40.sp
    val displaySmallFontSize = 32.sp
    val headlineLargeFontSize = 24.sp
    val headlineMediumFontSize = 20.sp
    val headlineSmallFontSize = 18.sp
    val bodyXLargeFontSize = 18.sp
    val bodyLargeFontSize = 16.sp
    val bodyMediumFontSize = 14.sp
    val bodySmallFontSize = 12.sp
    val bodyXSmallFontSize = 10.sp

    val base = Typography(
        displayLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = displayLargeFontSize,
            lineHeight = calculateLineHeight(displayLargeFontSize, 160f)
        ),
        displayMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = displayMediumFontSize,
            lineHeight = calculateLineHeight(displayMediumFontSize, 160f)
        ),
        displaySmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = displaySmallFontSize,
            lineHeight = calculateLineHeight(displaySmallFontSize, 160f)
        ),
        headlineLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = headlineLargeFontSize,
            lineHeight = calculateLineHeight(headlineLargeFontSize, 160f)
        ),
        headlineMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = headlineMediumFontSize,
            lineHeight = calculateLineHeight(headlineMediumFontSize, 160f)
        ),
        headlineSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = headlineSmallFontSize,
            lineHeight = calculateLineHeight(headlineSmallFontSize, 160f)
        ),
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = bodyLargeFontSize,
            lineHeight = calculateLineHeight(bodyLargeFontSize, 160f)
        ),
        bodyMedium = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = bodyMediumFontSize,
            lineHeight = calculateLineHeight(bodyMediumFontSize, 140f)
        ),
        bodySmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = bodySmallFontSize,
            lineHeight = calculateLineHeight(bodySmallFontSize, null)
        )
    )

    return FontTypography(
        typography = base,
        bodyXLarge = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = bodyXLargeFontSize,
            lineHeight = calculateLineHeight(bodyXLargeFontSize, 160f)
        ),
        bodyLarge = base.bodyLarge,
        bodyMedium = base.bodyMedium,
        bodySmall = base.bodySmall,
        bodyXSmall = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = bodyXSmallFontSize,
            lineHeight = calculateLineHeight(bodyXSmallFontSize, null)
        )
    )
}

private fun calculateLineHeight(fontSize: TextUnit, lineHeightPercentage: Float?): TextUnit {
    return if (lineHeightPercentage == null) {
        TextUnit.Unspecified
    } else {
        fontSize * (lineHeightPercentage / 100)
    }
}
