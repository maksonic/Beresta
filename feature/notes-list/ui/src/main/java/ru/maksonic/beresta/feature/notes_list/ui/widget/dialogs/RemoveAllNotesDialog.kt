package ru.maksonic.beresta.feature.notes_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.DialogButton
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 22.01.2023
 */
@Composable
fun RemoveAllNotesDialog(
    cancelAction: () -> Unit,
    removeAllAction: () -> Unit,
    checkedState: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier
                .clip(Theme.shape.cornerBig)
                .background(background)
                .padding(dp16),
            verticalArrangement = Arrangement.spacedBy(dp8)
        ) {
            Text(
                text = stringResource(R.string.title_dialog_remove_all_notes),
                style = TextDesign.topBar
            )
            Text(
                text = stringResource(id = R.string.body_dialog_remove_all_notes),
                style = TextDesign.body,
                modifier = modifier.padding(top = dp8)
            )
            Row(
                modifier
                    .fillMaxWidth()
                    .clip(Shape.cornerNormal)
                    .clickAction(rippleColor = primary) {
                        checkedState.value = !checkedState.value
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = background,
                        checkedColor = primary,
                        uncheckedColor = primary,
                    )
                )
                Text(
                    text = stringResource(R.string.checkbox_caption_remove_all_notes),
                    style = TextDesign.captionSmall
                )
            }

            Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(dp16)) {
                DialogButton(
                    action = {},
                    title = stringResource(R.string.title_dialog_cancel_remove_notes),
                    isDismiss = true,
                    modifier = modifier.weight(1f)
                )
                DialogButton(
                    action = removeAllAction,
                    title = stringResource(R.string.title_dialog_remove_notes),
                    isDismiss = false,
                    modifier = modifier.weight(1f)
                )
            }
        }
    }
}