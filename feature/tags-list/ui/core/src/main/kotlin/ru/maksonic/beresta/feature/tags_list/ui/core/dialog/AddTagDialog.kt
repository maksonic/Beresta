package ru.maksonic.beresta.feature.tags_list.ui.core.dialog

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.dialog.DialogBase
import ru.maksonic.beresta.common.ui_kit.text.TextHeadlineSmall
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.errorContainer
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.tags_list.ui.core.Model
import ru.maksonic.beresta.feature.tags_list.ui.core.Msg
import ru.maksonic.beresta.feature.tags_list.ui.core.Send
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 05.11.2023
 */
@Composable
internal fun AddTagDialog(model: Model, send: Send) {
    DialogBase(
        isVisible = model.isVisibleAddTagDialog,
        alignment = Alignment.BottomCenter,
        onCancelClicked = { send(Msg.Inner.UpdatedAddTagDialogVisibility(false)) },
        onAcceptClicked = { send(Msg.Ui.OnAcceptTagCreationClicked) }
    ) {

        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(model.isVisibleAddTagDialog) {
            if (model.isVisibleAddTagDialog) {
                delay(100)
                awaitFrame()
                focusRequester.requestFocus()
            }
        }

        Content(model, send, focusRequester)
    }
}

@Composable
private fun Content(
    model: Model,
    send: Send,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val dialogTitle = "Создать новый тег"

        TextHeadlineSmall(dialogTitle, modifier.padding(bottom = dp16))

        InputFolderName(model, send, focusRequester)
    }
}

@Composable
private fun InputFolderName(
    model: Model,
    send: Send,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = model.newTagInputField,
        onValueChange = { send(Msg.Inner.UpdatedInputField(it)) },
        textStyle = TextDesign.bodyMedium,
        singleLine = true,
        supportingText = {
            InputCounterHint(
                counterValue = model.hintSymbolsCount,
                isError = model.isEmptyFieldError
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { send(Msg.Ui.OnAcceptTagCreationClicked) }),
        colors = TextFieldDefaults.colors(
            focusedTextColor = onBackground,
            disabledTextColor = outline,
            focusedContainerColor = secondaryContainer,
            unfocusedContainerColor = secondaryContainer,
            focusedIndicatorColor = tertiaryContainer,
            errorIndicatorColor = ru.maksonic.beresta.common.ui_theme.colors.error,
            unfocusedIndicatorColor = tertiaryContainer,
            disabledIndicatorColor = tertiaryContainer,
            cursorColor = primary,
            errorContainerColor = errorContainer,
            errorLabelColor = ru.maksonic.beresta.common.ui_theme.colors.error,
            errorSupportingTextColor = ru.maksonic.beresta.common.ui_theme.colors.error,
            selectionColors = TextSelectionColors(
                handleColor = primary,
                backgroundColor = onSurfaceVariant
            ),
        ),
        shape = Theme.shape.cornerLarge,
        isError = model.isEmptyFieldError,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
    )
}

@Composable
private fun InputCounterHint(counterValue: String, isError: Boolean) {
    val caption = if (isError) text.folders.hintErrorEmptyFolderName else counterValue
    val color = if (isError) Theme.color.error else onBackground

    Text(text = caption, style = TextDesign.labelSmall.copy(color = color))
}