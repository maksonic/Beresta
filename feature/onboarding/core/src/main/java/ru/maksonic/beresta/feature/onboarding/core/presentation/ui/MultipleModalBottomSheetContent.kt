package ru.maksonic.beresta.feature.onboarding.core.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.get
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.onboarding.core.presentation.Msg
import ru.maksonic.beresta.feature.theme_selector.api.ThemeSelectorApi

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
    languageSheet: LanguageSelectorApi.Ui = get(),
    themeSheet: ThemeSelectorApi.Ui = get(),
    modalSheetState: () -> ModalBottomSheetState,
    modifier: Modifier
) {
    when (currentSheetContent) {
        ModalSheetContent.LANGUAGE_SELECTOR -> {
            languageSheet.BottomSheet(
                isVisibleSheet = modalSheetState().isVisible,
                hideSheet = { send(Msg.Ui.OnHideLanguageBtnClicked) }
            )
        }

        ModalSheetContent.THEME_SELECTOR -> {
            themeSheet.BottomSheet(
                isVisibleSheet = modalSheetState().isVisible,
                hideSheet = { send(Msg.Ui.OnHideLanguageBtnClicked) }
            )
        }

        else -> Box(modifier.defaultMinSize(minHeight = 1.dp))
    }
}