package ru.maksonic.beresta.feature.notes_list.ui.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.notes_list.ui.api.NoteUiMapper
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NotesCardUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.list.NotesListUiApi
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardStateMapper
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesCardUiCore
import ru.maksonic.beresta.feature.notes_list.ui.core.NotesListUiCore
import ru.maksonic.beresta.feature.notes_list.ui.core.data.NoteCardStateMapperImpl
import ru.maksonic.beresta.feature.notes_list.ui.core.data.NoteCardStateUiCore
import ru.maksonic.beresta.feature.notes_list.ui.core.data.NoteUiMapperImpl
import ru.maksonic.beresta.feature.notes_list.ui.core.data.NotesSortUiMapperImpl
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.NotesSortUiMapper

/**
 * @Author maksonic on 13.10.2023
 */
val notesListUiFeatureModule = module {
    factory<NoteUiMapper> { NoteUiMapperImpl() }
    factory<NotesSortUiMapper> { NotesSortUiMapperImpl() }
    factory<NoteCardStateMapper> { NoteCardStateMapperImpl() }
    single<NotesCardUiApi.CardState> { NoteCardStateUiCore() }
    factory<NotesCardUiApi> { NotesCardUiCore() }
    factory<NotesListUiApi> { NotesListUiCore() }
}