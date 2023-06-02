package ru.maksonic.beresta.screen.trash_list.notes.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash_list.notes.core.Msg
import ru.maksonic.beresta.ui.theme.color.scrim
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.TrashedFolder
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.button.DialogButton

/**
 * @Author maksonic on 01.06.2023
 */
@Composable
fun TrashDialogAcceptDeleteData(send: SendMessage, modifier: Modifier = Modifier) {

    BackHandler {
        send(Msg.Ui.OnCancelDeleteDialogClicked)
    }

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
                    /*Text(
                        "\uD83D\uDDD1\uFE0F \uD83D\uDDC2\uFE0F \uD83D\uDDD1\uFE0F",
                        style = TextDesign.topBar,
                        modifier = modifier.padding(dp16)
                    )*/
                    Image(
                        imageVector = AppImage.TrashedFolder,
                        contentDescription = "",
                        modifier = modifier
                            .padding(top = dp16)
                            .size(120.dp)
                    )
                    Text(
                        text = text.trash.titleDialogDeleteNote,
                        style = TextDesign.topBar,
                        textAlign = TextAlign.Center,
                        modifier = modifier.padding(top = dp16)
                    )


                    Text(
                        text = text.trash.dialogBodyDeleteNote,
                        textAlign = TextAlign.Center,
                        style = TextDesign.bodyPrimary,
                        modifier = modifier.padding(dp16)
                    )

                    Row(
                        modifier.padding(top = dp16),
                        horizontalArrangement = Arrangement.spacedBy(dp16)
                    ) {

                        DialogButton(
                            action = { send(Msg.Ui.OnCancelDeleteDialogClicked) },
                            title = text.shared.btnTitleCancel,
                            isDismiss = true,
                            modifier = Modifier.weight(1f)
                        )
                        DialogButton(
                            action = { },
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