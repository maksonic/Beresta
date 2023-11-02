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
fun TextTitleLarge(text: String, modifier: Modifier = Modifier, color: Color = onBackground) {
    Text(text = text, style = TextDesign.titleLarge.copy(color), modifier = modifier)
}

@Composable
fun TextTitleMedium(text: String, modifier: Modifier = Modifier, color: Color = onBackground) {
    Text(text = text, style = TextDesign.titleMedium.copy(color), modifier = modifier)
}

@Composable
fun TextTitleSmall(text: String, modifier: Modifier = Modifier, color: Color = onBackground) {
    Text(text = text, style = TextDesign.titleSmall.copy(color), modifier = modifier)
}

@Preview
@Composable
private fun TextTitlePreview() {
    PreviewContainer {
        Column(verticalArrangement = Arrangement.spacedBy(dp8)) {
            TextTitleLarge("TextTitleLargePreview")
            TextTitleMedium("TextTitleMediumPreview")
            TextTitleSmall("TextTitleSmallPreview")
        }
    }
}