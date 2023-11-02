package ru.maksonic.beresta.feature.ui.edit_note.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiApi
import ru.maksonic.beresta.feature.ui.edit_note.core.EditNoteProgram
import ru.maksonic.beresta.feature.ui.edit_note.core.EditNoteSandbox
import ru.maksonic.beresta.feature.ui.edit_note.core.EditNoteUiCore

/**
 * @Author maksonic on 26.04.2023
 */
val editNoteUiFeatureModule = module {
    single {
        EditNoteProgram(
            fetchFoldersUseCase = get(),
            fetchNoteByIdUseCase = get(),
            fetchMarkerColorsUseCase = get(),
            wallpaperRepository = get(),
            foldersChipsRowUiApi = get(),
            notesInteractor = get(),
            mapper = get(),
            foldersMapper = get(),
            navigator = get()
        )
    }
    single<EditNoteUiApi> { EditNoteUiCore() }
    viewModel { EditNoteSandbox(program = get()) }
}