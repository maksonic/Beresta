package ru.maksonic.beresta.feature.ui.add_tag_dialog.core

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
import ru.maksonic.beresta.common.ui_theme.colors.errorContainer
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSurfaceVariant
import ru.maksonic.beresta.common.ui_theme.colors.outline
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogState
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core.Model
import ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core.Msg
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 13.11.2023
 */
@Composable
internal fun Content(
    state: AddTagDialogState,
    model: Model,
    send: Send,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(state.isVisible) {
        if (!model.isFetchedTag && state.isVisible) {
            send(Msg.Inner.FetchedPassedTagId(state.passedTagId))
        }
    }

    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val dialogTitle = with(text.tags) {
            if (state.isNewTag) btnTitleCreateNewTag else btnTitleEditTag
        }

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
    val caption = if (isError) text.tags.hintErrorEmptyTagName else counterValue
    val color = if (isError) ru.maksonic.beresta.common.ui_theme.colors.error else onBackground

    Text(text = caption, style = TextDesign.labelSmall.copy(color = color))
}