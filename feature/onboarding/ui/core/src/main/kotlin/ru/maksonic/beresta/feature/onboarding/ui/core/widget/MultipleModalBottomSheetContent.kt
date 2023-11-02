package ru.maksonic.beresta.feature.onboarding.ui.core.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.onboarding.ui.core.ModalSheetContent
import ru.maksonic.beresta.feature.ui.language_picker.api.LanguagePickerUiApi
import ru.maksonic.beresta.feature.ui.theme_picker.api.ThemePickerUiApi

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    modalSheetContent: ModalSheetContent,
    modifier: Modifier = Modifier,
    languageBottomSheetApi: LanguagePickerUiApi = koinInject(),
    themePickerUiApi: ThemePickerUiApi = koinInject()
) {
    when (modalSheetContent) {
        ModalSheetContent.LANGUAGE_PICKER -> languageBottomSheetApi.SheetContent()
        ModalSheetContent.THEME_PICKER -> themePickerUiApi.SheetContent()
        ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
    }
}