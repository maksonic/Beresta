package ru.maksonic.beresta.feature.marker_color_picker.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.core.ui.ext.update
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerColorPickerApi
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerPickerState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.format.FormatResetColor
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.button.DialogButton
import ru.maksonic.beresta.ui.widget.dialog.BaseDialog

/**
 * @Author maksonic on 09.09.2023
 */
class MarkerColorPickerDialog : MarkerColorPickerApi.Ui {
    override val sharedState = mutableStateOf(MarkerPickerState.Initial)

    override fun showWithColor(colorId: Long) =
        sharedState.update(sharedState.value.copy(true, colorId))

    override fun updateVisibility(value: Boolean) =
        sharedState.update(sharedState.value.copy(value))

    override fun updateColorId(id: Long) = sharedState.update(sharedState.value.copy(colorId = id))


    @Composable
    override fun Widget(onAcceptClicked: (colorId: Long) -> Unit) {

        BaseDialog(isVisible = sharedState.value.isVisible, isVisibleActions = false) {
            val isDisabledColor = rememberSaveable { mutableStateOf(true) }
            val state = remember { sharedState }

            BackHandler(state.value.isVisible) {
                sharedState.update(sharedState.value.copy(false))
            }

            LaunchedEffect(state.value.colorId) {
                isDisabledColor.value = state.value.colorId == 0L
            }

            Column {
                Content(
                    state = state,
                    onSelectMarkerColorClicked = {
                        updateColorId(it).let { isDisabledColor.value = false }
                    }
                )
                Row(
                    Modifier.padding(top = dp16, start = dp16, end = dp16),
                    horizontalArrangement = Arrangement.spacedBy(dp16),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val resetColorBtnBackground = rememberUpdatedState(
                        if (isDisabledColor.value) tertiaryContainer else surfaceVariant
                    )

                    val resetColorBtnIconTint = rememberUpdatedState(
                        if (isDisabledColor.value) secondaryContainer else onBackground
                    )

                    ClickableIcon(
                        icon = AppIcon.FormatResetColor,
                        action = {
                            if (isDisabledColor.value) {
                                isDisabledColor.value = false
                                updateColorId(201L)
                            } else {
                                isDisabledColor.value = true
                                updateColorId(0L)
                            }
                        },
                        tint = resetColorBtnIconTint.value,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(resetColorBtnBackground.value)
                            .size(40.dp)
                    )

                    DialogButton(
                        action = { updateVisibility(false) },
                        title = text.shared.btnTitleCancel,
                        isDismiss = true,
                        modifier = Modifier.weight(1f)
                    )
                    DialogButton(
                        action = {
                            onAcceptClicked(state.value.colorId).run {
                                updateVisibility(false)
                            }
                        },
                        title = text.shared.btnTitleAccept,
                        isDismiss = false,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}