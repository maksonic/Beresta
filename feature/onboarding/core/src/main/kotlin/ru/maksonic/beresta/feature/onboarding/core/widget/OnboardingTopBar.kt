package ru.maksonic.beresta.feature.onboarding.core.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Language
import ru.maksonic.beresta.ui.theme.icons.ThemeSystem
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
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconAction(icon = { AppIcon.Language }, action = showLanguageSelector)
        IconAction(icon = { AppIcon.ThemeSystem }, action = showThemeSelector)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    BerestaTheme {
        OnboardingTopBar(showLanguageSelector = {}, showThemeSelector = {})
    }
}