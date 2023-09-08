package ru.maksonic.beresta.feature.theme_picker.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.theme_picker.ui.core.Model
import ru.maksonic.beresta.feature.theme_picker.ui.core.Msg
import ru.maksonic.beresta.feature.theme_picker.ui.widget.ThemeItemsColumn
import ru.maksonic.beresta.feature.theme_picker.ui.widget.palette_picker.PaletteColorPicker
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.button.PrimaryButton

/**
 * @Author maksonic on 19.06.2023
 */
@Composable
internal fun Content(
    model: State<Model>,
    send: SendMessage,
    hideSheet: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp16, end = dp16)
            .verticalScroll(ScrollState(0))
    ) {

        PaletteColorPicker(model) { send(Msg.Ui.OnPaletteColorClicked(it)) }

        HorizontalDivider(modifier.padding(top = dp16, bottom = dp16), color = onSecondaryContainer)

        ThemeItemsColumn(
            currentSelectedTheme = model.value.currentTheme.first,
            onChangeTheme = { theme -> send(Msg.Ui.OnThemeClicked(theme)) }
        )

        PrimaryButton(
            action = { hideSheet() },
            title = text.shared.btnTitleSave,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = dp16)
        )
    }
}