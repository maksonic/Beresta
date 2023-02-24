package ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 19.02.2023
 */
@Composable
internal fun OnboardingTopBar(
    showLanguageSelector: () -> Unit,
    showThemeSelector: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .padding(start = dp4, end = dp4),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconAction(icon = { AppIcon.Language }, action = showLanguageSelector)
        IconAction(icon = { AppIcon.ThemeSystem }, action = showThemeSelector)
    }
}