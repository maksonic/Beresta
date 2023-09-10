package ru.maksonic.beresta.ui.widget.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.core.SINGLE_LINE_VALUE
import ru.maksonic.beresta.ui.theme.component.TextDesign

/**
 * @Author maksonic on 09.09.2023
 */
@Composable
fun DropdownMenuTitle(title: String) {
    Text(
        text = title,
        style = TextDesign.bodyPrimary,
        maxLines = SINGLE_LINE_VALUE,
        overflow = TextOverflow.Ellipsis
    )
}