package ru.maksonic.beresta.screen.folders.ui.widget

import androidx.compose.runtime.Composable
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.hidden_notes_dialog.api.HiddenNotesApi
import ru.maksonic.beresta.screen.folders.core.Msg
import ru.maksonic.beresta.screen.folders.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 01.08.2023
 */
@Composable
internal fun HiddenNotesDialog(
    isVisible: Boolean,
    send: SendMessage,
    hiddenNotesEnterPasswordDialog: HiddenNotesApi.Ui.EnterPasswordDialog = koinInject(),
) {
    AnimateFadeInOut(
        visible = isVisible,
        fadeInDuration = Theme.animVelocity.dialogVisibility,
        fadeOutDuration = Theme.animVelocity.dialogVisibility
    ) {
        hiddenNotesEnterPasswordDialog.Widget(
            isBlocked = false,
            onSuccessPin = { send(Msg.Inner.NavigatedToHiddenNotes) },
            onBlockedBackPressed = {},
            hideDialog = { send(Msg.Ui.OnHideHiddenNotesDialogClicked) }
        )
    }
}