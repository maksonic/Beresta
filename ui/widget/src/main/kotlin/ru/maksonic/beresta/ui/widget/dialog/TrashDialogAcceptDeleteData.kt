package ru.maksonic.beresta.ui.widget.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.TrashedFolder
import ru.maksonic.beresta.ui.theme.images.TrashedFoldersList
import ru.maksonic.beresta.ui.theme.images.TrashedNote
import ru.maksonic.beresta.ui.theme.images.TrashedNotesList

/**
 * @Author maksonic on 01.06.2023
 */
@Composable
fun TrashDialogAcceptDeleteData(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onCancelClicked: () -> Unit,
    onAcceptClicked: () -> Unit,
    isSingleItemAction: Boolean,
    isNotesDialog: Boolean = true
) {
    val singleActionTitle = if (isNotesDialog) text.trash.titleDialogDeleteNote
    else text.trash.titleDialogDeleteFolder
    val listActionTitle = if (isNotesDialog) text.trash.titleDialogDeleteNotesList
    else text.trash.titleDialogDeleteFoldersList
    val singleActionBody = if (isNotesDialog) text.trash.dialogBodyDeleteNote
    else text.trash.dialogBodyDeleteFolder
    val listActionBody = if (isNotesDialog) text.trash.dialogBodyDeleteNotesList
    else text.trash.dialogBodyDeleteFoldersList

    val singleActionImage = if (isNotesDialog) AppImage.TrashedNote else AppImage.TrashedFolder
    val listActionImage =
        if (isNotesDialog) AppImage.TrashedNotesList else AppImage.TrashedFoldersList
    val titleDialog =
        rememberUpdatedState(if (isSingleItemAction) singleActionTitle else listActionTitle)
    val bodyDialog =
        rememberUpdatedState(if (isSingleItemAction) singleActionBody else listActionBody)
    val imageDialog =
        rememberUpdatedState(if (isSingleItemAction) singleActionImage else listActionImage)

    BaseDialog(
        isVisible = isVisible,
        btnTitleCancel = text.shared.btnTitleCancel,
        btnTitleAccept = text.shared.btnTitleDelete,
        onCancelClicked = onCancelClicked,
        onAcceptClicked = onAcceptClicked
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                imageVector = imageDialog.value,
                contentDescription = "",
                modifier = modifier.size(120.dp)
            )

            Text(
                text = titleDialog.value,
                style = TextDesign.topBar,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(top = dp16)
            )

            Text(
                text = bodyDialog.value,
                style = TextDesign.bodyPrimary,
                modifier = modifier.padding(top = dp16)
            )
        }
    }
}