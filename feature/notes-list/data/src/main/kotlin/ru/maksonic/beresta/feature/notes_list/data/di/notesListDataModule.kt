package ru.maksonic.beresta.feature.notes_list.data.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.notes_list.data.card.FetchNoteCardStateUseCaseImpl
import ru.maksonic.beresta.feature.notes_list.data.list.FetchNoteByIdUseCaseImpl
import ru.maksonic.beresta.feature.notes_list.data.list.FetchNotesByFolderTrashListUseCaseImpl
import ru.maksonic.beresta.feature.notes_list.data.list.FetchNotesUseCaseImpl
import ru.maksonic.beresta.feature.notes_list.data.list.FetchNotesWithoutFolderTrashListUseCaseImpl
import ru.maksonic.beresta.feature.notes_list.data.card.NoteCardInteractorImpl
import ru.maksonic.beresta.feature.notes_list.data.card.NoteCardStateRepositoryImpl
import ru.maksonic.beresta.feature.notes_list.data.list.DeleteHiddenNotesUseCaseImpl
import ru.maksonic.beresta.feature.notes_list.data.list.FetchHiddenNotesUseCaseImpl
import ru.maksonic.beresta.feature.notes_list.data.list.NotesRepositoryImpl
import ru.maksonic.beresta.feature.notes_list.domain.card.FetchNoteCardStateUseCase
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardInteractor
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardStateRepository
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesInteractor
import ru.maksonic.beresta.feature.notes_list.domain.list.NotesRepository
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.DeleteHiddenNotesUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchHiddenNotesUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNoteByIdUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesByFolderTrashListUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesUseCase
import ru.maksonic.beresta.feature.notes_list.domain.list.usecase.FetchNotesWithoutFolderTrashListUseCase

/**
 * @Author maksonic on 13.10.2023
 */
val notesListDataModule = module {
    single<NotesRepository> {
        NotesRepositoryImpl(
            notesCacheDataSource = get(),
            mapper = get(),
            appLangRepository = get(),
            dateFormatter = get()
        )
    }
    single<NoteCardStateRepository> { NoteCardStateRepositoryImpl(datastore = get()) }
    single<NoteCardInteractor> { NoteCardInteractorImpl(repository = get()) }
    single { NotesInteractor(repository = get()) }
    factory<FetchNotesUseCase> { FetchNotesUseCaseImpl(repository = get()) }
    factory<FetchHiddenNotesUseCase> { FetchHiddenNotesUseCaseImpl(repository = get()) }
    factory<FetchNoteByIdUseCase> { FetchNoteByIdUseCaseImpl(repository = get()) }
    factory<FetchNotesWithoutFolderTrashListUseCase> {
        FetchNotesWithoutFolderTrashListUseCaseImpl(repository = get())
    }
    factory<FetchNotesByFolderTrashListUseCase> {
        FetchNotesByFolderTrashListUseCaseImpl(repository = get())
    }
    factory<FetchNoteCardStateUseCase> { FetchNoteCardStateUseCaseImpl(repository = get()) }
    factory<DeleteHiddenNotesUseCase> { DeleteHiddenNotesUseCaseImpl(repository = get()) }
}