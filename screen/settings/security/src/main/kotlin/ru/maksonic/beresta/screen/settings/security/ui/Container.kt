package ru.maksonic.beresta.screen.settings.security.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.navigation.router.router.settings.SettingsSecurityScreenRouter
import ru.maksonic.beresta.screen.settings.security.core.Eff
import ru.maksonic.beresta.screen.settings.security.core.Msg
import ru.maksonic.beresta.screen.settings.security.core.SettingsSecuritySandbox

/**
 * @Author maksonic on 03.08.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
internal fun Container(
    router: SettingsSecurityScreenRouter,
    sandbox: SettingsSecuritySandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(sandbox.effects, router)

    Content(model = model, send = sandbox::send)
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, router: SettingsSecurityScreenRouter) {
    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
        }
    }
}