package ru.maksonic.beresta.feature.notes.ui.di

import org.koin.dsl.module
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.notes.api.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.ui.card.NoteCard
import ru.maksonic.beresta.feature.notes.ui.list.NotesList

/**
 * @Author maksonic on 22.06.2023
 */
val notesUiFeatureModule = module {
    single<NotesApi.Ui.Card> {
        NoteCard(object : SharedUiState<NoteCardUiState>(NoteCardUiState.Initial) {})
    }
    single<NotesApi.Ui.List> { NotesList() }
    single { NoteUiMapper() }
}