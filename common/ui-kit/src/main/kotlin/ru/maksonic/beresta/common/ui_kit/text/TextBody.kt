package ru.maksonic.beresta.common.ui_kit.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 15.10.2023
 */
@Composable
fun TextBodyLarge(text: String, modifier: Modifier = Modifier, color: Color = onBackground) {
    Text(text = text, style = TextDesign.bodyLarge.copy(color), modifier = modifier)
}

@Composable
fun TextBodyMedium(text: String, modifier: Modifier = Modifier, color: Color = onBackground) {
    Text(text = text, style = TextDesign.bodyMedium.copy(color), modifier = modifier)
}

@Composable
fun TextBodySmall(text: String, modifier: Modifier = Modifier, color: Color = onBackground) {
    Text(text = text, style = TextDesign.bodySmall.copy(color), modifier = modifier)
}

@Preview
@Composable
private fun TextBodyPreview() {
    PreviewContainer {
        Column(verticalArrangement = Arrangement.spacedBy(dp8)) {
            TextBodyLarge("TextBodyLargePreview")
            TextBodyMedium("TextBodyMediumPreview")
            TextBodySmall("TextBodySmallPreview")
        }
    }
}