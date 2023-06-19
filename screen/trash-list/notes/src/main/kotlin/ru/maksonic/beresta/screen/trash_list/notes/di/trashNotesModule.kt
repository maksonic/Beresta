package ru.maksonic.beresta.screen.trash_list.notes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.trash_list.notes.core.NotesTrashProgram
import ru.maksonic.beresta.screen.trash_list.notes.core.NotesTrashSandbox

/**
 * @Author maksonic on 30.05.2023
 */
val trashNotesModule = module {
    single { NotesTrashProgram(
        fetchRemovedNotes = get(),
        notesInteractor = get(),
        notesMapper = get(),
        appLanguageEngineApi = get(),
        dateFormatter = get()
    ) }

    viewModel { NotesTrashSandbox(program = get()) }
}