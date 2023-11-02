package ru.maksonic.beresta.feature.hidden_notes_dialog.ui.core

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.feature.hidden_notes_dialog.ui.api.HiddenNotesDialogUiApi

/**
 * @Author maksonic on 15.07.2023
 */
class HiddenNotesPinInputDialog : HiddenNotesDialogUiApi.PinInputDialog {
    @Composable
    override fun Widget(
        isVisible: Boolean,
        hideDialog: () -> Unit,
        onSuccessPin: () -> Unit,
        isBlocked: Boolean,
        onBlockedBackPressed: () -> Unit,
    ) {
        AnimateFadeInOut(
            visible = isVisible,
            fadeInDuration = Theme.animVelocity.dialogVisibility,
            fadeOutDuration = Theme.animVelocity.dialogVisibility
        ) {
            Container(
                hideDialog = hideDialog,
                onSuccessPin = onSuccessPin,
                isBlocked = isBlocked,
                onBlockedBackPressed = onBlockedBackPressed
            )
        }
    }
}