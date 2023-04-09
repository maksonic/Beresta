package ru.maksonic.beresta.feature.folders_list.core.dialog.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.job
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.folders_list.api.ui.FoldersSharedUiState
import ru.maksonic.beresta.feature.folders_list.api.ui.updateDialogVisibility
import ru.maksonic.beresta.feature.folders_list.core.dialog.core.CreateNewFolderDialogSandbox
import ru.maksonic.beresta.feature.folders_list.core.dialog.core.DialogModel
import ru.maksonic.beresta.feature.folders_list.core.dialog.core.Eff
import ru.maksonic.beresta.feature.folders_list.core.dialog.core.Msg
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.ui.theme.color.*
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
internal fun FolderCreationDialogContent(
    modifier: Modifier = Modifier,
    sharedUiState: SharedUiState<FoldersSharedUiState>,
    sandbox: CreateNewFolderDialogSandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle().value
    val sharedState = sharedUiState.state.collectAsState().value
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    HandleUiEffects(
        effects = sandbox.effects,
        focusManager = focusManager,
        sharedUiState = sharedUiState
    )

    LaunchedEffect(Unit) {
        this.coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }

    LaunchedEffect(sharedState.passedForEditFolderId) {
        sharedState.passedForEditFolderId?.let {
            sandbox.send(Msg.Inner.FetchedEditableFolderId(it))
        }
    }

    BackHandler(true) {
        sandbox.send(Msg.Ui.OnDismissFolderCreationDialogClicked)
    }

    Surface(color = scrim, modifier = modifier.focusRequester(focusRequester)) {

        Box(
            modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

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
                    Text(
                        text.folders.titleNewFolder,
                        style = TextDesign.topBar,
                        modifier = modifier.padding(dp16)
                    )

                    InputFolderName(model, sandbox::send, focusManager, focusRequester)

                    Row(
                        modifier.padding(top = dp16),
                        horizontalArrangement = Arrangement.spacedBy(dp16)
                    ) {
                        val acceptBtnTitle = if (model.currentEditableFolder.id != 0L)
                            text.shared.btnTitleChange
                        else text.shared.btnTitleCreate

                        DialogButton(
                            action = { sandbox.send(Msg.Ui.OnDismissFolderCreationDialogClicked) },
                            title = text.shared.btnTitleCancel,
                            isDismiss = true,
                            modifier = Modifier.weight(1f)
                        )
                        DialogButton(
                            action = { sandbox.send(Msg.Ui.OnCreateNewNotesFolderClicked) },
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
    model: DialogModel,
    send: (Msg) -> Unit,
    focusManager: FocusManager,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = model.currentEditableFolder.title,
        onValueChange = { send(Msg.Inner.UpdateNewFolderNameInput(it)) },
        textStyle = TextDesign.bodyPrimary,
        singleLine = true,
        supportingText = {
            InputCounterHint(
                counterValue = model.supportingText,
                isError = model.isEmptyFieldError
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default),
        keyboardActions = KeyboardActions(onDone = {
            send(Msg.Ui.OnCreateNewNotesFolderClicked)
            if (!model.isEmptyFieldError) {
                focusManager.clearFocus()
            }
        }),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = onBackground,
            unfocusedTextColor = outline,
            focusedBorderColor = tertiaryContainer,
            unfocusedBorderColor = outline,
            cursorColor = primary,
            errorBorderColor = error,
            errorTextColor = error,
            errorSupportingTextColor = error,
            selectionColors = TextSelectionColors(
                handleColor = primary,
                backgroundColor = onSurfaceVariant
            ),
        ),
        shape = Shape.cornerBig,
        isError = model.isEmptyFieldError,
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

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    focusManager: FocusManager,
    sharedUiState: SharedUiState<FoldersSharedUiState>
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.ShowNewFolderDialog -> sharedUiState.updateDialogVisibility(true)
            is Eff.HideNewFolderDialog -> {
                focusManager.clearFocus()
                sharedUiState.updateState { state ->
                    state.copy(isVisibleDialog = false, passedForEditFolderId = null)
                }
            }
            is Eff.ShowEmptyFieldError -> {}
        }
    }
}

