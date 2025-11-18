package com.kalay.themoviedb.core.theme.font

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.kalay.themoviedb.R

val UrbanistFamily = FontFamily(
    Font(R.font.urbanist_regular,   FontWeight.Normal),
    Font(R.font.urbanist_medium,    FontWeight.Medium),
    Font(R.font.urbanist_semi_bold, FontWeight.SemiBold),
    Font(R.font.urbanist_bold,      FontWeight.Bold),
)

val PlayFairDisplayFamily = FontFamily(
    Font(R.font.play_fair_display_regular,   FontWeight.Normal),
    Font(R.font.play_fair_display_medium,    FontWeight.Medium),
    Font(R.font.play_fair_display_semi_bold, FontWeight.SemiBold),
    Font(R.font.play_fair_display_bold,      FontWeight.Bold),
)

val RobotoFlexFamily = FontFamily(
    Font(R.font.roboto_flex, FontWeight.W400), // Normal
    Font(R.font.roboto_flex, FontWeight.W500), // Medium
    Font(R.font.roboto_flex, FontWeight.W600), // SemiBold
    Font(R.font.roboto_flex, FontWeight.W700), // Bold
)
