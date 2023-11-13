package ru.maksonic.beresta.screen.settings.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsScreenRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.settings.core.Eff
import ru.maksonic.beresta.screen.settings.core.Msg
import ru.maksonic.beresta.screen.settings.core.SettingsSandbox

/**
 * @Author maksonic on 26.07.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(router: SettingsScreenRouter, sandbox: SettingsSandbox = koinViewModel()) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    HandleUiEffects(
        effects = sandbox.effects,
        hideSheet = { sandbox.send(Msg.Inner.HiddenModalBottomSheet) },
        router = router,
        modalBottomSheetState = modalBottomSheetState,
    )

    Content(model, sandbox::send, modalBottomSheetState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    hideSheet: () -> Unit,
    router: SettingsScreenRouter,
    modalBottomSheetState: SheetState,
) {
    val scope = rememberCoroutineScope()

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.NavigateToAppearance -> router.toAppearance()
            is Eff.NavigateToNotifications -> router.toNotifications()
            is Eff.NavigateToSecurity -> router.toSecurity()
            is Eff.NavigateToTags -> router.toTags()
            is Eff.HideModalSheet -> {
                scope.launch { modalBottomSheetState.hide() }.invokeOnCompletion {
                    if (!modalBottomSheetState.isVisible) {
                        hideSheet()
                    }
                }
            }
        }
    }
}