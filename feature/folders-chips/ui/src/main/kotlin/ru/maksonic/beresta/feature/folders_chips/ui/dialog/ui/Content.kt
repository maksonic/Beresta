package ru.maksonic.beresta.feature.folders_chips.ui.dialog.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import kotlinx.coroutines.android.awaitFrame
import ru.maksonic.beresta.feature.folders_chips.api.ui.SharedNewFolderDialogUiState
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.Model
import ru.maksonic.beresta.feature.folders_chips.ui.dialog.core.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.error
import ru.maksonic.beresta.ui.theme.color.errorContainer
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSurfaceVariant
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16

/**
 * @Author maksonic on 03.07.2023
 */

@Composable
internal fun Content(
    model: State<Model>,
    send: SendMessage,
    uiState: State<SharedNewFolderDialogUiState>,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester.requestFocus()
    }

    LaunchedEffect(uiState.value.editableFolderId) {
        Log.e("AAA", "UI ${uiState.value.editableFolderId}")
        send(Msg.Inner.FetchedPassedFolderId(uiState.value.editableFolderId))
    }

    val dialogTitle = if (uiState.value.isNewFolder) text.folders.titleDialogNewFolder
    else text.folders.titleDialogEditFolder

    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            dialogTitle,
            style = TextDesign.topBar,
            modifier = modifier.padding(bottom = dp16)
        )

        InputFolderName(model, send, focusRequester)
    }
}

@Composable
private fun InputFolderName(
    model: State<Model>,
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
        colors = TextFieldDefaults.colors(
            focusedTextColor = onBackground,
            disabledTextColor = outline,
            focusedContainerColor = secondaryContainer,
            unfocusedContainerColor = secondaryContainer,
            focusedIndicatorColor = tertiaryContainer,
            errorIndicatorColor = error,
            unfocusedIndicatorColor = tertiaryContainer,
            disabledIndicatorColor = tertiaryContainer,
            cursorColor = primary,
            errorContainerColor = errorContainer,
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




