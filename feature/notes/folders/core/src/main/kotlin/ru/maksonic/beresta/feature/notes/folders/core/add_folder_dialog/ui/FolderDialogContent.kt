package ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.job
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.notes.folders.api.ui.hideDialog
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core.DialogModel
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core.Eff
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core.FolderDialogSandbox
import ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.error
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.widget.button.DialogButton
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 29.03.2023
 */

@Composable
internal fun FolderDialogContent(
    modifier: Modifier = Modifier,
    sharedUiState: SharedUiState<FoldersSharedUiState>,
    sandbox: FolderDialogSandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle()
    val sharedState = sharedUiState.state.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val passedFolderId = rememberSaveable {
        mutableStateOf(sharedState.value.passedForEditFolderId)
    }

    HandleUiEffects(effects = sandbox.effects, sharedUiState = sharedUiState)

    BackHandler(true) {
        sandbox.send(Msg.Ui.OnDismissClicked)
    }

    LaunchedEffect(Unit) {
        this.coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(passedFolderId) {
        sharedState.value.let {
            sandbox.send(Msg.Inner.UpdatedFolderStatus(it.isNewFolderStatus))
            sandbox.send(Msg.Inner.FetchedEditableFolderId(it.passedForEditFolderId))
        }
    }

    Surface(color = scrim, modifier = modifier.focusRequester(focusRequester)) {
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
                Column(
                    modifier
                        .fillMaxWidth()
                        .padding(start = dp16, end = dp16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val dialogTitle = if (model.value.isNewFolder) text.folders.titleDialogNewFolder
                    else text.folders.titleDialogEditFolder

                    Text(
                        dialogTitle,
                        style = TextDesign.topBar,
                        modifier = modifier.padding(dp16)
                    )

                    InputFolderName(model, sandbox::send, focusRequester)

                    Row(
                        modifier.padding(top = dp16),
                        horizontalArrangement = Arrangement.spacedBy(dp16)
                    ) {
                        val acceptBtnTitle = if (model.value.isNewFolder) text.shared.btnTitleCreate
                        else text.shared.btnTitleChange

                        DialogButton(
                            action = { sandbox.send(Msg.Ui.OnDismissClicked) },
                            title = text.shared.btnTitleCancel,
                            isDismiss = true,
                            modifier = Modifier.weight(1f)
                        )
                        DialogButton(
                            action = { sandbox.send(Msg.Ui.OnAcceptClicked) },
                            title = acceptBtnTitle,
                            isDismiss = false,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputFolderName(
    model: State<DialogModel>,
    send: (Msg) -> Unit,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = model.value.inputFiled,
        onValueChange = { send(Msg.Inner.UpdateNewFolderNameInput(it)) },
        textStyle = TextDesign.bodyPrimary,
        singleLine = true,
        supportingText = {
            InputCounterHint(
                counterValue = model.value.supportingText,
                isError = model.value.isEmptyFieldError
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { send(Msg.Ui.OnAcceptClicked) }),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = onBackground,
            disabledTextColor = outline,
            focusedBorderColor = tertiaryContainer,
            unfocusedBorderColor = outline,
            cursorColor = primary,
            errorBorderColor = error,
            errorLabelColor = error,
            errorSupportingTextColor = error,
            selectionColors = TextSelectionColors(
                handleColor = primary,
                backgroundColor = onSurfaceVariant
            ),
        ),
        shape = Shape.cornerBig,
        isError = model.value.isEmptyFieldError,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
    )
}

@Composable
private fun InputCounterHint(counterValue: String, isError: Boolean) {
    val caption = if (isError) text.folders.hintErrorEmptyFolderName else counterValue
    val color = if (isError) error else onBackground

    Text(text = caption, style = TextDesign.captionSmall.copy(color = color))
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    sharedUiState: SharedUiState<FoldersSharedUiState>
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.HideDialog -> keyboardController?.hide().let { sharedUiState.hideDialog() }
        }
    }
}

