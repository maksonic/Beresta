package ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.elm.compose.ElmComposableEffectHandler
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.Eff
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.Msg
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 04.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
internal fun Container(
    sharedUiState: SharedUiState<SharedNewFolderDialogUiState>,
    modifier: Modifier = Modifier,
    sandbox: FolderDialogSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val uiState = sharedUiState.state.collectAsStateWithLifecycle()

    AnimateFadeInOut(uiState.value.isVisible) {

        HandleUiEffects(effects = sandbox.effects, sharedUiState)

        BackHandler {
            sandbox.send(Msg.Ui.OnDismissClicked)
        }

        Surface(color = scrim) {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Box(
                    modifier
                        .imePadding()
                        .navigationBarsPadding()
                        .padding(bottom = dp16, start = dp32, end = dp32)
                        .clip(Shape.cornerExtra)
                        .background(secondaryContainer)
                        .padding(bottom = dp16)
                        .noRippleClickable { }
                ) {
                    Content(model, sandbox::send, sharedUiState)
                }
            }
        }
    }
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    sharedUiState: SharedUiState<SharedNewFolderDialogUiState>
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.HideDialog -> {
                scope.launch {
                    focusManager.clearFocus()
                    sharedUiState.update { it.copy(isVisible = false) }
                }
            }
        }
    }
}
