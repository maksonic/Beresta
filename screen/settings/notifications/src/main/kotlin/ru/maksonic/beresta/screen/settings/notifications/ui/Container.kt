package ru.maksonic.beresta.screen.settings.notifications.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.navigation.router.router.settings.SettingsNotificationsScreenRouter
import ru.maksonic.beresta.screen.settings.notifications.core.Eff
import ru.maksonic.beresta.screen.settings.notifications.core.Msg
import ru.maksonic.beresta.screen.settings.notifications.core.SettingsNotificationsSandbox

/**
 * @Author maksonic on 07.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
internal fun Container(
    router: SettingsNotificationsScreenRouter,
    sandbox: SettingsNotificationsSandbox = koinViewModel(),
) {
    val model by sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(sandbox.effects, router)

    Content(model, sandbox::send)
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: SettingsNotificationsScreenRouter
) {
    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
        }
    }
}