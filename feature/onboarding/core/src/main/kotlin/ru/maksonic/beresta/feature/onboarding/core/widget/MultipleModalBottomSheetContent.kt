package ru.maksonic.beresta.feature.onboarding.core.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi
import ru.maksonic.beresta.feature.onboarding.core.Msg
import ru.maksonic.beresta.feature.onboarding.core.SendMessage
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi

/**
 * @Author maksonic on 24.02.2023
 */
enum class ModalSheetContent {
    NOTHING, LANGUAGE_SELECTOR, THEME_SELECTOR
}

@Composable
internal fun MultipleModalBottomSheetContent(
    send: SendMessage,
    currentSheetContent: State<ModalSheetContent>,
    languageBottomSheet: LanguagePickerApi.Ui = koinInject(),
    themeBottomSheet: ThemePickerApi.Ui = koinInject(),
    modifier: Modifier = Modifier
) {

    Box {
        when (currentSheetContent.value) {
            ModalSheetContent.LANGUAGE_SELECTOR -> {
                languageBottomSheet.BottomSheetContent(
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            ModalSheetContent.THEME_SELECTOR -> {
                themeBottomSheet.BottomSheetContent(
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}