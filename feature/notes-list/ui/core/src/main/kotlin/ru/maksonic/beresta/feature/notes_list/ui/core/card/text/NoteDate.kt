package ru.maksonic.beresta.feature.notes_list.ui.core.card.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_theme.colors.inverseSurface
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 31.10.2023
 */
@Composable
internal fun NoteDate(
    date: String,
    modifier: Modifier = Modifier,
    tint: Color = inverseSurface
) {
    Text(
        text = date,
        style = TextDesign.labelSmall.copy(color = tint),
        modifier = modifier.padding(start = dp16, top = dp16)
    )
}