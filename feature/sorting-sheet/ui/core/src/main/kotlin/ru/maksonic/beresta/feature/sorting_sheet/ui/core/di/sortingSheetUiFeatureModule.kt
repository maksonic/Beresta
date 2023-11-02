package ru.maksonic.beresta.feature.sorting_sheet.ui.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.sorting_sheet.ui.api.SortingSheetUiApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.SortingSheetProgram
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.SortingSheetSandbox
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.SortingSheetUiCore

/**
 * @Author maksonic on 06.07.2023
 */
val sortingSheetUiFeatureModule = module {
    single<SortingSheetUiApi> { SortingSheetUiCore() }
    single { SortingSheetProgram(sortInteractor = get()) }
    viewModel { SortingSheetSandbox(program = get()) }
}