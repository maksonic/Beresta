package ru.maksonic.beresta.screen.settings.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguagePickerUiApi
import ru.maksonic.beresta.feature.ui.theme_picker.api.ThemePickerUiApi
import ru.maksonic.beresta.screen.settings.core.ModalSheetContent
import ru.maksonic.beresta.screen.settings.core.Model

/**
 * @Author maksonic on 20.02.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    model: Model,
    modifier: Modifier = Modifier,
    languageSheet: LanguagePickerUiApi = koinInject(),
    themeSheet: ThemePickerUiApi = koinInject(),
) {
    when (model.modalSheet.content) {
        ModalSheetContent.LANGUAGE_PICKER -> languageSheet.SheetContent()
        ModalSheetContent.THEME_PICKER -> themeSheet.SheetContent()
        ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
    }
}