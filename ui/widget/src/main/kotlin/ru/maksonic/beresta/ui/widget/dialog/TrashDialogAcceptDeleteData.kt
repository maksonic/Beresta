package ru.maksonic.beresta.ui.widget.dialog

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.TrashedFolder
import ru.maksonic.beresta.ui.theme.images.TrashedFoldersList
import ru.maksonic.beresta.ui.theme.images.TrashedNote
import ru.maksonic.beresta.ui.theme.images.TrashedNotesList
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.DialogButton
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

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

    if (isVisible) {
        BackHandler {
            onCancelClicked()
        }
    }

    AnimateFadeInOut(
        visible = isVisible,
        fadeInDuration = Theme.animSpeed.dialogVisibility,
        fadeOutDuration = Theme.animSpeed.dialogVisibility
    ) {

        SurfacePro(color = scrim) {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Box(
                    modifier
                        .padding(start = dp32, end = dp32)
                        .clip(Shape.cornerExtra)
                        .background(secondaryContainer)
                        .padding(bottom = dp16)
                ) {
                    Column(
                        modifier
                            .fillMaxWidth()
                            .padding(start = dp16, end = dp16),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            imageVector = imageDialog.value,
                            contentDescription = "",
                            modifier = modifier
                                .padding(top = dp16)
                                .size(120.dp)
                        )
                        Text(
                            text = titleDialog.value,
                            style = TextDesign.topBar,
                            textAlign = TextAlign.Center,
                            modifier = modifier.padding(top = dp16)
                        )


                        Text(
                            text = bodyDialog.value,
                            textAlign = TextAlign.Center,
                            style = TextDesign.bodyPrimary,
                            modifier = modifier.padding(dp16)
                        )

                        Row(
                            modifier.padding(top = dp16),
                            horizontalArrangement = Arrangement.spacedBy(dp16)
                        ) {

                            DialogButton(
                                action = onCancelClicked,
                                title = text.shared.btnTitleCancel,
                                isDismiss = true,
                                modifier = Modifier.weight(1f)
                            )
                            DialogButton(
                                action = onAcceptClicked,
                                title = text.shared.btnTitleDelete,
                                isDismiss = false,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}