package ru.maksonic.beresta.feature.onboarding.core.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.onboarding.core.presentation.Msg
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorUiApi

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
    currentSheetContent: ModalSheetContent,
    modalSheetState:  ModalBottomSheetState,
    languageSheet: LanguageSelectorApi.Ui = get(),
    themeSheet: ThemeSelectorUiApi = get(),
    modifier: Modifier
) {

    Box {
        when (currentSheetContent) {
            ModalSheetContent.LANGUAGE_SELECTOR -> {
                languageSheet.BottomSheet(
                    isVisibleSheet = modalSheetState.isVisible,
                    hideSheet = { send(Msg.Ui.OnHideLanguageBtnClicked) }
                )
            }

            ModalSheetContent.THEME_SELECTOR -> {
                themeSheet.BottomSheet(
                    isVisibleSheet = modalSheetState.isVisible,
                    hideSheet = { send(Msg.Ui.OnHideLanguageBtnClicked) }
                )
            }

            ModalSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}