package ru.maksonic.beresta.common.ui_kit.placeholder

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.button.dialog.ButtonDialogPrimary
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Wallpaper
import ru.maksonic.beresta.common.ui_theme.BerestaTheme
import ru.maksonic.beresta.common.ui_theme.colors.background
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 15.10.2023
 */
private const val FAIL_MESSAGE_MAX_LINES = 3

@Composable
fun PlaceholderErrorState(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    painter: Painter? = null,
    contentDescriptionImage: String = "",
    message: String,
    onErrorRetryClicked: () -> Unit,
    backgroundColor: Color = background,
    btnTitle: String = text.shared.btnTitleRetry
) {
    Box(
        modifier
            .fillMaxSize()
            .drawBehind { drawRect(backgroundColor) },
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.weight(0.3f))
            if (imageVector != null) {
                Image(
                    imageVector = imageVector,
                    contentDescription = contentDescriptionImage,
                    modifier = Modifier
                        .weight(0.15f)
                        .aspectRatio(1f)
                )
            }

            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = contentDescriptionImage,
                    modifier = Modifier
                        .weight(0.15f)
                        .aspectRatio(1f)
                )
            }

            Text(
                text = message,
                style = TextDesign.bodyLarge,
                maxLines = FAIL_MESSAGE_MAX_LINES,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(dp16)
            )
            
            ButtonDialogPrimary(onClick = onErrorRetryClicked, title = btnTitle)

            Spacer(Modifier.weight(0.3f))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPlaceholderPreview() {
    BerestaTheme {
        PlaceholderErrorState(
            imageVector = AppIcon.Wallpaper,
            message = "Maksonic",
            onErrorRetryClicked = {}
        )
    }
}