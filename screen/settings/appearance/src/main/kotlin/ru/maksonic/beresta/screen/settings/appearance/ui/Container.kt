package ru.maksonic.beresta.screen.settings.appearance.ui

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
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsAppearanceScreenRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.settings.appearance.core.Eff
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceSandbox

/**
 * @Author maksonic on 07.07.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: SettingsAppearanceScreenRouter,
    sandbox: SettingsAppearanceSandbox = koinViewModel(),
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    HandleUiEffects(sandbox.effects, router, modalBottomSheetState) {
        sandbox.send(Msg.Inner.HiddenModalBottomSheet)
    }

    Content(model, sandbox::send, modalBottomSheetState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: SettingsAppearanceScreenRouter,
    modalBottomSheetState: SheetState,
    hideSheet: () -> Unit

) {
    val scope = rememberCoroutineScope()

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
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