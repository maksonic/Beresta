package ru.maksonic.beresta.feature.notes_list.ui.core.card.inidcator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes_list.ui.core.card.NoteTagsRow
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi

/**
 * @Author maksonic on 31.10.2023
 */
@Composable
internal fun IndicationBottomPanel(tags: NoteTagUi.Collection, modifier: Modifier = Modifier) {
    NoteTagsRow(tags)
}