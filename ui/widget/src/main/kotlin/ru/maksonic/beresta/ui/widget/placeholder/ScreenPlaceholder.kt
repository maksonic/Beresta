package ru.maksonic.beresta.ui.widget.placeholder

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.theme.images.MaksonicDef

/**
 * @Author maksonic on 26.05.2023
 */
@Composable
fun ScreenPlaceholder(
    imageVector: ImageVector,
    message: String,
    backgroundColor: Color = background,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .fillMaxSize()
            .drawBehind { drawRect(backgroundColor) },
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.weight(0.3f))
            Image(
                imageVector = imageVector,
                contentDescription = "",
                modifier = Modifier
                    .weight(0.15f)
                    .aspectRatio(1f)
            )
            Text(
                text = message,
                style = TextDesign.title,
                modifier = Modifier.padding(top = dp16)
            )
            Spacer(Modifier.weight(0.3f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPlaceholderPreview() {
    BerestaTheme {
        ScreenPlaceholder(
            imageVector = AppImage.MaksonicDef,
            message = "Maksonic"
        )
    }
}