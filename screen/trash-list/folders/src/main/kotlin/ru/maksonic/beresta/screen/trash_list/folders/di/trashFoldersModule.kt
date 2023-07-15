package ru.maksonic.beresta.screen.trash_list.folders.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.screen.trash_list.folders.core.FoldersTrashProgram
import ru.maksonic.beresta.screen.trash_list.folders.core.FoldersTrashSandbox

/**
 * @Author maksonic on 30.05.2023
 */
val trashFoldersScreenModule = module {
    single { FoldersTrashProgram(
        fetchFoldersTrashListUseCase = get(),
        fetchNotesByFolderTrashListUseCase = get(),
        notesFoldersInteractor = get(),
        notesInteractor = get(),
        foldersMapper = get(),
        notesMapper = get(),
        appLanguageEngineApi = get(),
        dateFormatter = get(),
        ioDispatcher = get(named(CoroutineDispatchers.IO))
    ) }

    viewModel { FoldersTrashSandbox(program = get()) }
}