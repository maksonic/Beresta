package ru.maksonic.beresta.common.ui_kit.button.radio

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.button.radio.base.RadioButtonBase
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.text.TextBodyLarge
import ru.maksonic.beresta.common.ui_kit.text.TextLabelSmall
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.inverseSurface
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.provide.dp16

/**
 * @Author maksonic on 15.10.2023
 */
@Composable
fun RadioButtonEndHint(
    modifier: Modifier = Modifier,
    title: String = "",
    hint: String = "",
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

        RadioButtonBase(selected, onClick)

        TextBodyLarge(title)

        Spacer(modifier.weight(1f))

        TextLabelSmall(hint, color = inverseSurface, modifier = modifier.padding(end = dp16))
    }
}