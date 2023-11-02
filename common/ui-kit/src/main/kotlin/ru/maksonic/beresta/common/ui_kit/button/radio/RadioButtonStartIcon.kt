package ru.maksonic.beresta.common.ui_kit.button.radio

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.common.ui_kit.button.radio.base.RadioButtonBase
import ru.maksonic.beresta.common.ui_kit.helpers.PreviewContainer
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Done
import ru.maksonic.beresta.common.ui_kit.text.TextBodyLarge
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8

/**
 * @Author maksonic on 15.10.2023
 */
@Composable
fun RadioButtonStartIcon(
    modifier: Modifier = Modifier,
    title: String = "",
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.size.listItemContainerHeight)
            .rippledClick(primary, onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconBase(icon = icon, modifier = modifier.padding(start = dp16))

        TextBodyLarge(text = title, modifier = modifier.padding(start = dp8))

        Spacer(modifier.weight(1f))

        RadioButtonBase(selected, onClick)
    }
}

@Preview
@Composable
private fun RadioButtonStartIconPreview() {
    PreviewContainer {
        var selected by remember { mutableStateOf(false) }

        RadioButtonStartIcon(
            title = "Radio button start icon",
            icon = AppIcon.Done,
            selected = selected,
            onClick = { selected = !selected }
        )
    }
}
