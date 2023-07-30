package ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import ru.maksonic.beresta.ui.widget.dialog.BaseDialog

/**
 * @Author maksonic on 04.07.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
internal fun Container(
    sharedUiState: SharedUiState<SharedNewFolderDialogUiState>,
    sandbox: FolderDialogSandbox = koinViewModel()
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val uiState = sharedUiState.state.collectAsStateWithLifecycle()

    if (uiState.value.isVisible) {
        HandleUiEffects(effects = sandbox.effects, sharedUiState)
    }

    BaseDialog(
        isVisible = uiState.value.isVisible,
        alignment = Alignment.BottomCenter,
        onCancelClicked = { sandbox.send(Msg.Ui.OnDismissClicked) },
        onAcceptClicked = { sandbox.send(Msg.Ui.OnAcceptClicked) }
    ) {
        Content(model, sandbox::send, uiState = uiState)
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
