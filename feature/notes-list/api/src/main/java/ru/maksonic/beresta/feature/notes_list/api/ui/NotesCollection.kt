package ru.maksonic.beresta.feature.notes_list.api.ui

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * @Author maksonic on 21.02.2023
 */
@Stable
@Immutable
data class NotesCollection(val data: List<NoteUi>)