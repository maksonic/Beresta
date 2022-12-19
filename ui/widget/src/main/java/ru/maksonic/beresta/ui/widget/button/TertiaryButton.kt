package ru.maksonic.beresta.ui.widget.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 15.11.2022
 */
@Composable
fun TertiaryButton(
    modifier: Modifier = Modifier,
    title: String,
    color: Color = primary,
    action: () -> Unit
) {
    Text(
        text = title,
        style = TextDesign.title.copy(color),
        modifier = modifier
            .noRippleClickable { action.invoke() }
            .padding(dp8)
    )
}