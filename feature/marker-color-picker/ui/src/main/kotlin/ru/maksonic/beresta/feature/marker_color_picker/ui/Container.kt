package ru.maksonic.beresta.feature.marker_color_picker.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import ru.maksonic.beresta.ui.widget.dialog.BaseDialog

/**
 * @Author maksonic on 09.09.2023
 */
@Composable
internal fun Container(
    isVisible: State<Boolean>,
    hideDialog: () -> Unit
) {

    BaseDialog(
        isVisible = isVisible.value,
        alignment = Alignment.BottomCenter,
        onCancelClicked = hideDialog,
        onAcceptClicked = { }
    ) {
        Content()
    }
}
