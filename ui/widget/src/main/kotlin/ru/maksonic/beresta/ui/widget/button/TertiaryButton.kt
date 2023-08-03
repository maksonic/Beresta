package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.noRippleClick

/**
 * @Author maksonic on 15.11.2022
 */
@Composable
fun TertiaryButton(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = onBackground,
    action: () -> Unit
) {
    Box(
        modifier
            .defaultMinSize(minHeight = Theme.widgetSize.minimumTouchTargetSize)
            .noRippleClick { action.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = TextDesign.title.copy(color),
            modifier = modifier.padding(start = dp16, end = dp16)

        )
    }
}