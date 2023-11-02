package ru.maksonic.beresta.screen.trash.folders.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.common.ui_kit.dialog.DialogTopImage
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash.folders.core.Model
import ru.maksonic.beresta.screen.trash.folders.core.Msg
import ru.maksonic.beresta.screen.trash.folders.ui.Send

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
fun DialogAcceptDeleteItems(model: Model, send: Send) {
    val title = with(text.trash) {
        if (model.isSingleSelection) titleDialogDeleteFolder else titleDialogDeleteFoldersList
    }
    val message = with(text.trash) {
        if (model.isSingleSelection) dialogBodyDeleteFolder else dialogBodyDeleteFoldersList
    }
    val image = with(Theme.image) {
        if (model.isSingleSelection) imageDeleteFolder else imageDeleteFoldersList
    }

    DialogTopImage(
        isVisible = model.isVisibleAcceptDeleteFoldersDialog,
        painter = painterResource(image),
        titleDialog = title,
        messageDialog = message,
        onCancelClicked = { send(Msg.Ui.OnCancelDeleteWarningDialogClicked) },
        onAcceptClicked = { send(Msg.Ui.OnAcceptDeleteWarningDialogClicked) },
    )
}