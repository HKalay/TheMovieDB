package com.kalay.themoviedb.core.ui.search

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun SearchHeader(
    screenTitle: String,
    hintText: String,
    currentText: String,
    isSearchMode: Boolean,
    onSearchModeChange: (Boolean) -> Unit,
    onSearchQueryChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )

        AnimatedContent(
            targetState = isSearchMode,
            transitionSpec = {
                if (targetState) {
                    slideInHorizontally { fullWidth -> fullWidth } + fadeIn() togetherWith
                            slideOutHorizontally { fullWidth -> -fullWidth / 2 } + fadeOut()
                } else {
                    slideInHorizontally { fullWidth -> -fullWidth } + fadeIn() togetherWith
                            slideOutHorizontally { fullWidth -> fullWidth / 2 } + fadeOut()
                }.using(SizeTransform(clip = false))
            },
            modifier = Modifier.weight(1f)
        ) { searchMode ->
            if (searchMode) {
                SearchBar(
                    text = currentText,
                    hintText = hintText,
                    onTextChange = { onSearchQueryChange(it) },
                    isEditing = isSearchMode,
                    onEditingChange = { onSearchModeChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
            } else {
                Text(
                    text = screenTitle,
                    style = urbanistTypography().typography.headlineLarge.copy(color = Color.White),
                    textAlign = TextAlign.Center
                )
            }
        }

        Image(
            painter = painterResource(id = if (isSearchMode) R.drawable.ic_close else R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    if (isSearchMode) {
                        onSearchQueryChange("")
                        onSearchModeChange(false)
                    } else {
                        onSearchModeChange(true)
                    }
                }
        )
    }
}