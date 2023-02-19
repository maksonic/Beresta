package ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.language_selector.api.LanguageSelectorApi
import ru.maksonic.beresta.feature.onboarding.core.presentation.Msg
import ru.maksonic.beresta.feature.onboarding.core.presentation.ui.SendMessage

/**
 * @Author maksonic on 19.02.2023
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun SelectLanguageBottomSheetDialogContent(
    send: SendMessage,
    languageSheet: LanguageSelectorApi.Ui,
    languageSheetState: ModalBottomSheetState,
    modifier: Modifier
) {
    Box(
        modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 1.dp)
    ) {
        languageSheet.BottomSheet(
            isVisibleSheet = languageSheetState.isVisible,
            hideSheet = { send(Msg.Ui.OnHideLanguageBtnClicked) },
            modifier = modifier
        )
    }
}