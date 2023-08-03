package ru.maksonic.beresta.screen.settings.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.screen.settings.core.Msg
import ru.maksonic.beresta.screen.settings.ui.SendMessage

/**
 * @Author maksonic on 20.02.2023
 */
enum class ModalSheetContent {
    NOTHING, LANGUAGE_SELECTOR, THEME_SELECTOR
}

@Composable
internal fun MultipleModalBottomSheetContent(
    send: SendMessage,
    currentSheetContent: State<ModalSheetContent>,
    modifier: Modifier = Modifier,
    languageSheet: LanguagePickerApi.Ui = koinInject(),
    themeSheet: ThemePickerApi.Ui = koinInject(),
) {
    when (currentSheetContent.value) {
        ModalSheetContent.LANGUAGE_SELECTOR -> {
            languageSheet.SheetContent(
                hideSheet = { send(Msg.Ui.OnHideModalSheetClicked) }
            )
        }

        ModalSheetContent.THEME_SELECTOR -> {
            themeSheet.SheetContent(
                hideSheet = { send(Msg.Ui.OnHideModalSheetClicked) },
            )
        }

        ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
    }
}