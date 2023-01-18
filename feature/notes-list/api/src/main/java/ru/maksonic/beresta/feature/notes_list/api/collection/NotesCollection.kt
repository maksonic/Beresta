package ru.maksonic.beresta.feature.notes_list.api.collection

import androidx.compose.runtime.Immutable
import ru.maksonic.beresta.feature.notes_list.api.NoteUi

/**
 * @Author maksonic on 18.01.2023
 */
@Immutable
data class NotesCollection(val notes: List<NoteUi>)