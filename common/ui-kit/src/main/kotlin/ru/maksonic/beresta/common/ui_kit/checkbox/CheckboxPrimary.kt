package ru.maksonic.beresta.common.ui_kit.checkbox

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer

/**
 * @Author maksonic on 16.10.2023
 */
@Composable
fun CheckboxPrimary(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Checkbox(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = tertiaryContainer,
            checkmarkColor = onTertiaryContainer,
            uncheckedColor = outline,
            disabledCheckedColor = outline,
            disabledUncheckedColor = outline,
            disabledIndeterminateColor = outline
        )
    )
}