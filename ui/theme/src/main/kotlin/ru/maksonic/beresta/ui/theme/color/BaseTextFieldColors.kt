package ru.maksonic.beresta.ui.theme.color

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 27.01.2023
 */

val NoteInputDefaultColors @Composable get() = TextFieldDefaults.colors(
    focusedTextColor = onBackground,
    disabledTextColor = onBackground,
    focusedContainerColor = transparent,
    cursorColor = primary,
    focusedIndicatorColor = transparent,
    unfocusedIndicatorColor = transparent,
    disabledIndicatorColor = transparent,
    unfocusedContainerColor = transparent,
    selectionColors = TextSelectionColors(handleColor = primary, onSurfaceVariant),
)