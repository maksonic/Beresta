package ru.maksonic.beresta.feature.ui.add_folder_dialog.core

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import ru.maksonic.beresta.common.ui_kit.text.TextHeadlineSmall
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.error
import ru.maksonic.beresta.common.ui_theme.colors.errorContainer
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core.Model
import ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
internal fun Content(
    model: Model,
    send: Send,
    isNewFolder: Boolean,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        if (!model.isFetchedFolder) {
            send(Msg.Inner.FetchedPassedFolderId)
        }
    }

    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val dialogTitle = if (isNewFolder) text.folders.titleDialogNewFolder
        else text.folders.titleDialogEditFolder

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
        value = model.inputFiled,
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
    val color = if (isError) error else onBackground

    Text(text = caption, style = TextDesign.labelSmall.copy(color = color))
}