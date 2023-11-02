package ru.maksonic.beresta.feature.notes_list.ui.core.card

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.common.core.ext.SINGLE_LINE_VALUE
import ru.maksonic.beresta.common.ui_kit.surface.SurfacePro
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSurface
import ru.maksonic.beresta.common.ui_theme.provide.dp2
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.notes_list.ui.api.card.isSquare
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState

/**
 * @Author maksonic on 31.10.2023
 */
@Composable
fun NoteTagsRow(modifier: Modifier = Modifier) {
    val tags = listOf("maksonic", "Work", "Метка", "Работа", "Продукты", "Телефон")

    Row(
        modifier
            .padding(top = dp8, bottom = dp8)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
    ) {
        tags.forEachIndexed { index, title ->
            val pModifier = if (index == tags.lastIndex) Modifier.padding(end = dp8) else Modifier

            NoteTag(title, pModifier)
        }
    }
}

@Composable
private fun NoteTag(title: String, modifier: Modifier = Modifier) {
    val shape = if (noteUiCardState.shape.isSquare) Theme.shape.cornerNormal else CircleShape

    SurfacePro(
        modifier.padding(start = dp8).clip(shape),
        tonalElevation = Theme.tonal.level5
    ) {
        Text(
            text = title.take(100),
            style = TextDesign.labelSmall.copy(onSurface),
            overflow = TextOverflow.Clip,
            maxLines = SINGLE_LINE_VALUE,
            modifier = Modifier.padding(start = dp8, end = dp8, top = dp2, bottom = dp2)
        )
    }
}
