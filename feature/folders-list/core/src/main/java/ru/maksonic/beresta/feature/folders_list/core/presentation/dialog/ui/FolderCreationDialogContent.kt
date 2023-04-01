package ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.ui

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
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.core.CreateNewFolderDialogSandbox
import ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.core.DialogModel
import ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.core.Eff
import ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.core.Msg
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
    updateDialogVisibility: (isVisible: Boolean) -> Unit,
    sandbox: CreateNewFolderDialogSandbox = koinViewModel(),
) {
    val model = sandbox.model.collectAsStateWithLifecycle().value
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    HandleUiEffects(effects = sandbox.effects, updateDialogVisibility)

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackHandler(true) {
        focusManager.clearFocus()
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
                        text.shared.titleNewFolder,
                        style = TextDesign.topBar,
                        modifier = modifier.padding(dp16)
                    )

                    InputFolderName(model, sandbox::send, focusManager , focusRequester)

                    Row(
                        modifier.padding(top = dp16),
                        horizontalArrangement = Arrangement.spacedBy(dp16)
                    ) {
                        DialogButton(
                            action = {
                                focusManager.clearFocus()
                                sandbox.send(Msg.Ui.OnDismissFolderCreationDialogClicked)
                            },
                            title = text.shared.btnTitleCancel,
                            isDismiss = true,
                            modifier = Modifier.weight(1f)
                        )
                        DialogButton(
                            action = { sandbox.send(Msg.Ui.OnCreateNewNotesFolderClicked) },
                            title = text.shared.btnTitleCreate,
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
        value = model.folderInputName,
        onValueChange = {
            send(Msg.Inner.UpdateNewFolderNameInput(it))
        },
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
    val caption = if (isError) text.shared.hintErrorEmptyFolderName else counterValue
    val color = if (isError) error else onBackground

    Text(text = caption, style = TextDesign.captionSmall.copy(color = color))
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>, updateDialogVisibility: (isVisible: Boolean) -> Unit,
) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.ShowNewFolderDialog -> updateDialogVisibility(true)
            is Eff.HideNewFolderDialog -> updateDialogVisibility(false)
            is Eff.ShowEmptyFieldError -> {}
        }
    }
}

