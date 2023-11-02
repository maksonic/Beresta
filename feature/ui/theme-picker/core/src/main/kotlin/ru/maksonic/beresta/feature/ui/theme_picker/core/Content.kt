package ru.maksonic.beresta.feature.ui.theme_picker.core

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.text.TextHeadlineSmall
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.Model
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 19.06.2023
 */
@Composable
internal fun Content(model: Model, send: Send, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextHeadlineSmall(text.settings.dialogThemePickerTitle)

        PaletteColorPicker(model) { send(Msg.Ui.OnPaletteColorClicked(it)) }

        HorizontalDivider(modifier.padding(dp16), color = onSecondaryContainer)

        ThemeSelector(
            currentSelectedTheme = model.currentTheme,
            onSelectThemeClicked = { theme -> send(Msg.Ui.OnThemeClicked(theme)) }
        )
    }
}