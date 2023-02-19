package ru.maksonic.beresta.feature.onboarding.core.presentation.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Language
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 19.02.2023
 */
@Composable
internal fun OnboardingTopBar(showLanguageSelector: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .padding(end = dp4),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconAction(icon = { AppIcon.Language }, action = showLanguageSelector)
    }
}