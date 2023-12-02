package ru.maksonic.beresta.feature.ui.edit_note.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiApi
import ru.maksonic.beresta.feature.ui.edit_note.core.EditNoteProgram
import ru.maksonic.beresta.feature.ui.edit_note.core.EditNoteSandbox
import ru.maksonic.beresta.feature.ui.edit_note.core.EditNoteUiCore
import ru.maksonic.beresta.feature.ui.edit_note.core.MapperStore
import ru.maksonic.beresta.feature.ui.edit_note.core.ProgramProxy

/**
 * @Author maksonic on 26.04.2023
 */
val editNoteUiFeatureModule = module {

    single {
        ProgramProxy(
            fetchNoteByIdUseCase = get(),
            fetchNoteTagsUseCase = get(),
            fetchFoldersUseCase = get(),
            fetchMarkerColorsUseCase = get(),
            findWallpaperByParamsUseCase = get(),
            foldersChipsRowUiApi = get(),
            notesInteractor = get(),
        )
    }

    single { MapperStore(note = get(), tag = get(), folder = get()) }
    single { EditNoteProgram(proxy = get(), mapper = get(), navigator = get()) }
    single<EditNoteUiApi> { EditNoteUiCore() }
    viewModel { EditNoteSandbox(program = get()) }
}