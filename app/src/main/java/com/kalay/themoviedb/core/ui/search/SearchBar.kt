package com.kalay.themoviedb.core.ui.search

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.kalay.themoviedb.core.theme.Dark3Color
import com.kalay.themoviedb.core.theme.SecondaryColor
import com.kalay.themoviedb.core.theme.font.urbanistTypography
import kotlinx.coroutines.delay


@Composable
fun SearchBar(
    text: String,
    hintText: String,
    onTextChange: (String) -> Unit,
    isEditing: Boolean,
    onEditingChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }
    var hasFocus by remember { mutableStateOf(false) }
    val view = LocalView.current

    var textFieldValue by remember(text) {
        mutableStateOf(
            TextFieldValue(
                text = text,
                selection = TextRange(text.length)
            )
        )
    }

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight < screenHeight * 0.15) {
                if (hasFocus) {
                    focusManager.clearFocus()
                }
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    Box(
        modifier = modifier
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Dark3Color)
            .clickable {
                if (!isEditing) {
                    onEditingChange(true)
                }
            }
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue.copy(
                    selection = TextRange(newValue.text.length)
                )
                onTextChange(newValue.text)
            },
            singleLine = true,
            readOnly = !isEditing,
            cursorBrush = if (text.isNotEmpty() || hasFocus) {
                SolidColor(SecondaryColor)
            } else {
                SolidColor(Color.Unspecified)
            },
            textStyle = urbanistTypography().typography.bodyLarge.copy(color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { state -> hasFocus = state.isFocused },
        ) { innerTextField ->
            if (text.isEmpty() && !hasFocus) {
                Text(
                    text = hintText,
                    style = urbanistTypography().bodyLarge.copy(color = Color.Gray)
                )
            }
            innerTextField()
        }
    }

    LaunchedEffect(isEditing) {
        if (isEditing) {
            delay(100)
            focusRequester.requestFocus()
        } else {
            focusManager.clearFocus()
        }
    }
}
