package ru.maksonic.beresta.ui.theme.color

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 27.01.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
val NoteInputDefaultColors @Composable get() = TextFieldDefaults.textFieldColors(
    textColor = onBackground,
    disabledTextColor = onBackground,
    containerColor = transparent,
    cursorColor = primary,
    focusedIndicatorColor = transparent,
    unfocusedIndicatorColor = transparent,
    disabledIndicatorColor = transparent,
    selectionColors = TextSelectionColors(handleColor = primary, onSurfaceVariant),
)