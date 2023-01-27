package ru.maksonic.beresta.ui.theme.color

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 27.01.2023
 */
val BaseTextFieldColors
    @Composable get() = TextFieldDefaults.textFieldColors(
        textColor = onPrimaryContainer,
        backgroundColor = background,
        cursorColor = primary,
        focusedIndicatorColor = background,
        unfocusedIndicatorColor = background,
        placeholderColor = secondary
    )