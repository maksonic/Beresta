package ru.maksonic.beresta.feature.ui.theme_picker.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.feature.ui.theme_picker.api.ThemePickerUiApi
import ru.maksonic.beresta.feature.ui.theme_picker.core.main.Model

/**
 * @Author maksonic on 13.10.2023
 */
class ThemePickerUiCore : ThemePickerUiApi {
    @Composable
    override fun SheetContent() {
        Container()
    }
}

@Preview
@Composable
private fun ThemePickerSheetContentPreview() {
    PreviewContainer {
        Content(
            model = Model.Initial,
            send = {}
        )
    }
}

@Preview
@Composable
private fun ThemePickerSheetContentPreviewDark() {
    PreviewContainer(isDarkMode = true) {
        Content(
            model = Model.Initial,
            send = {}
        )
    }
}