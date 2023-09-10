package ru.maksonic.beresta.screen.folders.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.coroutine_dispatchers.CoroutineDispatchers
import ru.maksonic.beresta.screen.folders.core.FoldersListProgram
import ru.maksonic.beresta.screen.folders.core.FoldersScreenSandbox

/**
 * @Author maksonic on 04.07.2023
 */
val foldersScreenModule = module {
    single {
        FoldersListProgram(
            foldersMapper = get(),
            foldersInteractor = get(),
            notesInteractor = get(),
            notesMapper = get(),
            navigator = get(),
            appLanguageEngineApi = get(),
            stickyFoldersTitleFormatter = get(),
            chipsRowApi = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))

        )
    }
    viewModel { FoldersScreenSandbox(program = get()) }
}