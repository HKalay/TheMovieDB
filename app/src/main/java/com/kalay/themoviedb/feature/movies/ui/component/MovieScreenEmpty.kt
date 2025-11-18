package com.kalay.themoviedb.feature.movies.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.R
import com.kalay.themoviedb.core.theme.font.urbanistTypography

@Composable
fun MovieScreenEmpty(text: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_movies),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = urbanistTypography()
                .typography
                .bodyLarge
                .copy(color = Color.White)
        )
    }
}