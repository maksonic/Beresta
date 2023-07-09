package ru.maksonic.beresta.screen.settings.appearance.ui

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.navigation.router.router.settings.SettingsAppearanceScreenRouter
import ru.maksonic.beresta.screen.settings.appearance.core.Msg

/**
 * @Author maksonic on 06.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun SettingsAppearanceScreen(router: SettingsAppearanceScreenRouter) {
    Container(router)
}