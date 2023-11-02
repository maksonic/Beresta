package ru.maksonic.beresta.feature.ui.add_folder_dialog.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.common.ui_kit.dialog.DialogBase
import ru.maksonic.beresta.feature.ui.add_folder_dialog.api.FolderDialogUiState
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core.Eff
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core.Msg
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler

/**
 * @Author maksonic on 04.07.2023
 */
internal typealias Send = (Msg) -> Unit

@Composable
internal fun Container(
    isVisible: Boolean,
    state: State<FolderDialogUiState>,
    hideDialog: () -> Unit,
    sandbox: FolderDialogSandbox = koinViewModel()
) {
    DialogBase(
        isVisible = isVisible,
        alignment = Alignment.BottomCenter,
        onCancelClicked = { sandbox.send(Msg.Ui.OnDismissClicked) },
        onAcceptClicked = { sandbox.send(Msg.Ui.OnAcceptClicked) }
    ) {
        val model by sandbox.model.collectAsStateWithLifecycle()
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(isVisible) {
            if (isVisible) {
                delay(100)
                awaitFrame()
                focusRequester.requestFocus()
            }
        }

        HandleUiEffects(effects = sandbox.effects, hideDialog)

        Content(model, sandbox::send, isNewFolder = state.value.isNewFolder, focusRequester)
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
