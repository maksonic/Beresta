package ru.maksonic.beresta.feature.notes_list.core.filter.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.dialog
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import ru.maksonic.beresta.feature.language_selector.api.provider.text
import ru.maksonic.beresta.feature.notes_list.api.NewFolderDialogSharedState
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.widget.button.DialogButton
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 29.03.2023
 */
private const val MAX_FOLDER_LENGTH = 50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FolderCreationDialogContent(
    state: NewFolderDialogSharedState,
    modifier: Modifier = Modifier
) {

    AnimateFadeInOut(state.isVisible) {

        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }

        BackHandler(state.isVisible) {
            focusManager.clearFocus()
            state.onDismissClicked()
        }

        Box(
            modifier
                .fillMaxSize()
                .background(scrim)
                .noRippleClickable { }
                .semantics { dialog() },
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
                    OutlinedTextField(
                        value = state.folderInputName,
                        onValueChange = {
                            state.updateInputField(it)
                        },
                        textStyle = TextDesign.bodyPrimary,
                        singleLine = true,
                        supportingText = {
                            Text(
                                text = "${state.folderInputName.count()}/${MAX_FOLDER_LENGTH}",
                                style = TextDesign.captionSmall
                            )
                        },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedTextColor = onBackground,
                            unfocusedTextColor = outline,
                            focusedBorderColor = tertiaryContainer,
                            unfocusedBorderColor = outline,
                            cursorColor = primary,
                            selectionColors = TextSelectionColors(
                                handleColor = primary,
                                backgroundColor = onSurfaceVariant
                            ),
                        ),
                        shape = Shape.cornerBig,
                        modifier = modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
                    )
                    Row(
                        modifier.padding(top = dp16),
                        horizontalArrangement = Arrangement.spacedBy(dp16)
                    ) {
                        DialogButton(
                            action = {
                                focusManager.clearFocus()
                                state.onDismissClicked()
                            },
                            title = text.shared.btnTitleCancel,
                            isDismiss = true,
                            modifier = Modifier.weight(1f)
                        )
                        DialogButton(
                            action = state.onCreateNewFolderClicked,
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