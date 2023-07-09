package ru.maksonic.beresta.screen.settings.appearance.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.navigation.router.router.settings.SettingsAppearanceScreenRouter
import ru.maksonic.beresta.screen.settings.appearance.core.Eff
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceSandbox

/**
 * @Author maksonic on 07.07.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun Container(
    router: SettingsAppearanceScreenRouter,
    sandbox: SettingsAppearanceSandbox = koinViewModel(),
    noteCardApi: NotesApi.Ui.Card = koinInject()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(sandbox.effects, router, model.value.modalSheet.state) {
        sandbox.send(Msg.Inner.HiddenModalBottomSheet)
    }

    Content(model = model, send = sandbox::send, noteCardApi = noteCardApi)
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