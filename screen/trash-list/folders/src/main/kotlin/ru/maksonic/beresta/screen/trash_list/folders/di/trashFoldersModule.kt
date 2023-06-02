package ru.maksonic.beresta.screen.trash_list.folders.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.trash_list.folders.core.FoldersTrashProgram
import ru.maksonic.beresta.screen.trash_list.folders.core.FoldersTrashSandbox

/**
 * @Author maksonic on 30.05.2023
 */
val trashFoldersModule = module {
    single { FoldersTrashProgram(
        fetchRemovedNotes = get(),
        notesMapper = get()
    ) }

    viewModel { FoldersTrashSandbox(program = get()) }
}