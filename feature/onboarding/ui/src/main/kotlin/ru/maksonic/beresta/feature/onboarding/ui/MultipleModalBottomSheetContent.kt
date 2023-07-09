package ru.maksonic.beresta.feature.onboarding.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.language_picker.api.LanguagePickerApi
import ru.maksonic.beresta.feature.onboarding.api.OnboardingApi
import ru.maksonic.beresta.feature.onboarding.ui.core.Msg
import ru.maksonic.beresta.feature.theme_picker.api.ThemePickerApi
import ru.maksonic.beresta.ui.theme.color.secondaryContainer

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    modifier: Modifier = Modifier,
    send: SendMessage,
    currentSheetContent: OnboardingApi.Ui.BottomSheetContent,
    languageBottomSheet: LanguagePickerApi.Ui = koinInject(),
    themeBottomSheet: ThemePickerApi.Ui = koinInject()
) {

    Box(modifier.background(secondaryContainer)) {
        when (currentSheetContent) {
            OnboardingApi.Ui.BottomSheetContent.LANGUAGE_PICKER -> {
                languageBottomSheet.SheetContent(
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            OnboardingApi.Ui.BottomSheetContent.THEME_PICKER -> {
                themeBottomSheet.SheetContent(
                    hideSheet = { send(Msg.Ui.OnHideModalBottomSheet) }
                )
            }

            OnboardingApi.Ui.BottomSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}