package ru.maksonic.beresta.common.ui_kit.widget.settings_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onPrimary
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 21.10.2023
 */
@Composable
fun SettingTextTitle(title: String, modifier: Modifier = Modifier) {
    Row(
        modifier.height(Theme.size.minimumTouchTargetSize),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextDesign.titleMedium.copy(color = onPrimary),
            modifier = modifier.padding(start = dp16, end = dp16)
        )
    }
}