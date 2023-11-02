package ru.maksonic.beresta.common.ui_kit.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.text.TextBodyLarge
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
fun DialogTopImage(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    painter: Painter? = null,
    imageVector: ImageVector? = null,
    titleDialog: String,
    messageDialog: String,
    onCancelClicked: () -> Unit,
    onAcceptClicked: () -> Unit,
    contentDescriptionDialogImage: String = ""
) {
    DialogBase(
        isVisible = isVisible,
        titleBtnCancel = text.shared.btnTitleCancel,
        titleBtnAccept = text.shared.btnTitleDelete,
        onCancelClicked = onCancelClicked,
        onAcceptClicked = onAcceptClicked
    ) {
        Column(
            modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = contentDescriptionDialogImage,
                    modifier = modifier.size(100.dp)
                )
            }

            if (imageVector != null) {
                Image(
                    imageVector = imageVector,
                    contentDescription = contentDescriptionDialogImage,
                    modifier = modifier.size(100.dp)
                )
            }

            Text(
                text = titleDialog,
                style = TextDesign.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(top = dp16)
            )

            TextBodyLarge(text = messageDialog, modifier = modifier.padding(top = dp16))

        }
    }
}