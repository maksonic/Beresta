package ru.maksonic.beresta.feature.notes.ui.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.notes.api.NotesApi
import ru.maksonic.beresta.feature.notes.api.ui.NoteUiMapper
import ru.maksonic.beresta.feature.notes.ui.card.NoteCard
import ru.maksonic.beresta.feature.notes.ui.list.NotesList
import ru.maksonic.beresta.feature.notes.ui.placeholder.NotesListPlaceholder

/**
 * @Author maksonic on 22.06.2023
 */
val notesUiFeatureModule = module {
    single<NotesApi.Card.Ui> { NoteCard() }
    single<NotesApi.List.Ui> { NotesList() }
    single<NotesApi.ListPlaceholder.Ui> { NotesListPlaceholder() }
    single { NoteUiMapper() }
}