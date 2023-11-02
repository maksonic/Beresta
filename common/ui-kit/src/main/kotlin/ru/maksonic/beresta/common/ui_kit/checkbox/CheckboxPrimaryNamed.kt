package ru.maksonic.beresta.common.ui_kit.checkbox

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.text.TextLabelSmall
import ru.maksonic.beresta.common.ui_theme.colors.primary

/**
 * @Author maksonic on 16.10.2023
 */
@Composable
fun CheckboxPrimaryNamed(
    checked: Boolean,
    title: String,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .rippledClick(primary) { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CheckboxPrimary(checked, onCheckedChange)
        TextLabelSmall(title)
    }
}

@Preview
@Composable
fun CheckboxPrimaryNamedPreview() {
    PreviewContainer {
        var checked by remember { mutableStateOf(false) }

        CheckboxPrimaryNamed(checked, "CheckboxPrimaryNamedPreview") { checked = !checked }
    }
}