package ru.maksonic.beresta.feature.ui.theme_picker.core

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.button.radio.RadioButtonStartIcon
import ru.maksonic.beresta.common.ui_theme.AppThemeUi
import ru.maksonic.beresta.common.ui_theme.BerestaTheme
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.ThemeUi
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.ThemeUiStore
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 25.02.2023
 */
@Composable
internal fun ThemeSelector(
    currentSelectedTheme: AppThemeUi,
    onSelectThemeClicked: (AppThemeUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(bottom = dp32)) {
        ThemeUiStore.themes.data.forEach { item ->
            val title = when (item.theme) {
                AppThemeUi.SYSTEM -> text.settings.titleThemeSystem
                AppThemeUi.DAY -> text.settings.titleThemeLight
                AppThemeUi.NIGHT -> text.settings.themeTitleNight
                AppThemeUi.DARK -> text.settings.themeTitleDark
            }

            RadioButtonStartIcon(
                title = item.copy(title = title).title,
                icon = item.icon,
                selected = currentSelectedTheme == item.theme,
                onClick = { onSelectThemeClicked(item.theme) },
                modifier = modifier
            )
        }
    }
}

@Preview
@Composable
private fun ThemeItemPreview() {
    BerestaTheme {
        val themeItem = ThemeUi.Preview
        var selected by remember { mutableStateOf(false) }

        RadioButtonStartIcon(
            title = themeItem.title,
            icon = themeItem.icon,
            selected = selected,
            onClick = { selected = !selected }
        )
    }
}
