package ru.maksonic.beresta.feature.marker_color_picker.ui.core

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.dialog.DialogBase
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.MarkerPickerUiState

/**
 * @Author maksonic on 29.10.2023
 */
@Composable
internal fun Container(
    state: MarkerPickerUiState,
    onUpdateCurrentColorSelectionClicked: (colorId: Long) -> Unit,
    hideDialog: () -> Unit
) {
    DialogBase(isVisible = state.isVisibleDialog, isVisibleActions = false, contentPadding = 0.dp) {

        BackHandler(state.isVisibleDialog) {
            hideDialog()
        }

        Content(
            state = state,
            onColorClicked = onUpdateCurrentColorSelectionClicked,
            hideDialog = hideDialog
        )
    }
}