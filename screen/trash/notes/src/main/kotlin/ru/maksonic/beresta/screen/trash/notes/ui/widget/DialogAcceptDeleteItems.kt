package ru.maksonic.beresta.screen.trash.notes.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.common.ui_kit.dialog.DialogTopImage
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash.notes.core.Model
import ru.maksonic.beresta.screen.trash.notes.core.Msg
import ru.maksonic.beresta.screen.trash.notes.ui.Send

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
fun DialogAcceptDeleteItems(model: Model, send: Send) {
    val title = with(text.trash) {
        if (model.isSingleSelection) titleDialogDeleteNote else titleDialogDeleteNotesList
    }
    val message = with(text.trash) {
        if (model.isSingleSelection) dialogBodyDeleteNote else dialogBodyDeleteNotesList
    }
    val image = with(Theme.image) {
        if (model.isSingleSelection) imageDeleteNote else imageDeleteNotesList
    }

    DialogTopImage(
        isVisible = model.isVisibleAcceptDeleteNotesDialog,
        painter = painterResource(image),
        titleDialog = title,
        messageDialog = message,
        onCancelClicked = { send(Msg.Ui.OnCancelDeleteWarningDialogClicked) },
        onAcceptClicked = { send(Msg.Ui.OnAcceptDeleteWarningDialogClicked) },
    )
}