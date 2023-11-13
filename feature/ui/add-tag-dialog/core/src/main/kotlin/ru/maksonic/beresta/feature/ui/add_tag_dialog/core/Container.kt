package ru.maksonic.beresta.feature.ui.add_tag_dialog.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogState
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core.AddTagDialogSandbox
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core.Eff
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core.Msg
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler

/**
 * @Author maksonic on 13.11.2023
 */
internal typealias Send = (Msg) -> Unit

@Composable
internal fun Container(
    state: AddTagDialogState,
    hideDialog: () -> Unit,
    sandbox: AddTagDialogSandbox = koinViewModel()
) {
    DialogBase(
        isVisible = state.isVisible,
        alignment = Alignment.BottomCenter,
        onCancelClicked = { sandbox.send(Msg.Ui.OnDismissClicked) },
        onAcceptClicked = { sandbox.send(Msg.Ui.OnAcceptClicked) }
    ) {
        val model by sandbox.model.collectAsStateWithLifecycle()
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(state.isVisible) {
            if (state.isVisible) {
                delay(100)
                awaitFrame()
                focusRequester.requestFocus()
            }
        }

        HandleUiEffects(effects = sandbox.effects, hideDialog)

        Content(
            state = state,
            model = model,
            send = sandbox::send,
            focusRequester = focusRequester
        )
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
