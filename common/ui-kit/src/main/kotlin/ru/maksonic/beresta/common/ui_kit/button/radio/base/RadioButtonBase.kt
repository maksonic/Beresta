package ru.maksonic.beresta.common.ui_kit.button.radio.base

import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer

/**
 * @Author maksonic on 15.10.2023
 */
@Composable
fun RadioButtonBase(selected: Boolean, onClick: () -> Unit) {
    RadioButton(
        selected = selected,
        onClick = onClick,
        colors = RadioButtonDefaults.colors(
            selectedColor = tertiaryContainer,
            unselectedColor = outline
        ),
    )
}