package ru.maksonic.beresta.screen.settings.tags.ui

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
import ru.maksonic.beresta.navigation.router.routes.settings.SettingsTagsScreenRouter
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.screen.settings.tags.core.Eff
import ru.maksonic.beresta.screen.settings.tags.core.Msg
import ru.maksonic.beresta.screen.settings.tags.core.SettingsTagsSandbox

/**
 * @Author maksonic on 12.11.2023
 */
internal typealias Send = (Msg) -> Unit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: SettingsTagsScreenRouter,
    sandbox: SettingsTagsSandbox = koinViewModel()
) {
    val model by sandbox.model.collectAsStateWithLifecycle()
    val modalBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = model.modalSheet.skipPartiallyExpanded
    )

    HandleUiEffects(
        effects = sandbox.effects,
        modalBottomSheetState = modalBottomSheetState,
        hideSheet = { sandbox.send(Msg.Inner.UpdatedModalSheetState(false)) },
        router = router
    )

    Content(model, sandbox::send, modalBottomSheetState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    router: SettingsTagsScreenRouter,
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