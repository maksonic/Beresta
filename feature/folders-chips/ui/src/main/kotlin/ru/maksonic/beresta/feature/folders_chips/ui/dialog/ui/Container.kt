package ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
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
    uiState: State<SharedNewFolderDialogUiState>,
    sandbox: FolderDialogSandbox = koinViewModel(),
    hideDialog: () -> Unit
) {

    if (uiState.value.isVisible) {
        HandleUiEffects(effects = sandbox.effects, hideDialog)
    }

    BaseDialog(
        isVisible = uiState.value.isVisible,
        alignment = Alignment.BottomCenter,
        onCancelClicked = { sandbox.send(Msg.Ui.OnDismissClicked) },
        onAcceptClicked = { sandbox.send(Msg.Ui.OnAcceptClicked) }
    ) {
        val model = sandbox.model.collectAsStateWithLifecycle()

        Content(model, sandbox::send, uiState = uiState)
    }
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, hideDialog: () -> Unit) {
    val focusManager = LocalFocusManager.current

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.HideDialog -> focusManager.clearFocus().run { hideDialog() }
        }
    }
}
