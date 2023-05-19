package ru.maksonic.beresta.feature.onboarding.core.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MultipleModalBottomSheetContent(
    send: SendMessage,
    currentSheetContent: State<ModalSheetContent>,
    modalSheetState:  ModalBottomSheetState,
    languageBottomSheet: LanguagePickerApi.Ui = get(),
    themeBottomSheet: ThemePickerApi.Ui = get(),
    modifier: Modifier = Modifier
) {

    Box {
        when (currentSheetContent.value) {
            ModalSheetContent.LANGUAGE_SELECTOR -> {
                languageBottomSheet.BottomSheet(
                    isVisibleSheet = modalSheetState.isVisible,
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            ModalSheetContent.THEME_SELECTOR -> {
                themeBottomSheet.BottomSheet(
                    isVisibleSheet = modalSheetState.isVisible,
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}