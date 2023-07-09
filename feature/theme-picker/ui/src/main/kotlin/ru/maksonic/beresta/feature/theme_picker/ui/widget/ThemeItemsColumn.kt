package ru.maksonic.beresta.feature.theme_picker.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.theme_picker.api.ThemeUiModel
import ru.maksonic.beresta.feature.theme_picker.ui.core.ThemeStore
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.AppTheme
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onSecondary
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 25.02.2023
 */
@Composable
internal fun ThemeItemsColumn(
    currentSelectedTheme: AppTheme,
    onChangeTheme: (AppTheme) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(bottom = dp16)) {
        ThemeStore.themes.data.forEach { item ->
            val title = when (item.theme) {
                AppTheme.SYSTEM -> text.settings.titleThemeSystem
                AppTheme.LIGHT -> text.settings.titleThemeLight
                AppTheme.DARK -> text.settings.themeTitleNight
                AppTheme.HIGH_CONTRAST -> text.settings.themeTitleHighContrast
            }
            val updated = item.copy(title = title)

            ThemeItem(
                isCurrent = currentSelectedTheme == item.theme,
                item = updated,
                onChangeTheme = { onChangeTheme(item.theme) }
            )
        }
    }
}

@Composable
private fun ThemeItem(
    isCurrent: Boolean,
    item: ThemeUiModel,
    onChangeTheme: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isCurrent) onSecondaryContainer else transparent

    Row(
        modifier
            .padding(bottom = dp8)
            .fillMaxWidth()
            .height(Theme.widgetSize.modalSheetItemHeight)
            .clip(Theme.shape.cornerNormal)
            .clickAction(rippleColor = primary) { onChangeTheme() }
            .drawBehind { drawRect(backgroundColor) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = item.icon,
            tint = onSecondary,
            contentDescription = "",
            modifier = modifier.padding(start = dp8)
        )

        Text(
            text = item.title,
            style = TextDesign.title.copy(color = onSecondary),
            modifier = modifier.padding(start = dp8)
        )
    }
}

@Preview
@Composable
private fun ThemeItemPreview() {
    BerestaTheme {
        ThemeItem(isCurrent = true, item = ThemeUiModel.Preview, onChangeTheme = {})
    }
}
