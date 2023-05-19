package ru.maksonic.beresta.feature.edit_note.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.core.EditNoteProgram
import ru.maksonic.beresta.feature.edit_note.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.ui.EditNoteExpandableScreen

/**
 * @Author maksonic on 26.04.2023
 */
val editNoteFeatureModule = module {
    single {
        EditNoteProgram(
            interactor = get(),
            fetchNoteByIdUseCase = get(),
            mapper = get(),
            navigator = get()
        )
    }
    single<EditNoteApi.Ui> { EditNoteExpandableScreen() }
    viewModel { EditNoteSandbox(program = get()) }
}