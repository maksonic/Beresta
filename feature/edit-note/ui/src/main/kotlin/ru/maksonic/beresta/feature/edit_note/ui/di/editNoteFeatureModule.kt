package ru.maksonic.beresta.feature.edit_note.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.ui.EditNoteProgram
import ru.maksonic.beresta.feature.edit_note.ui.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.ui.ui.EditNoteExpandableScreen

/**
 * @Author maksonic on 26.04.2023
 */
val editNoteUiFeatureModule = module {
    single {
        EditNoteProgram(
            interactor = get(),
            mapper = get(),
            navigator = get(),
        )
    }
    single<EditNoteApi.Ui> { EditNoteExpandableScreen() }
    viewModel { EditNoteSandbox(program = get()) }
}