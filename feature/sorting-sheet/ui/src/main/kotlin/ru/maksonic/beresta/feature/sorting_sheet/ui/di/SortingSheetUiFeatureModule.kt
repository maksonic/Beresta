package ru.maksonic.beresta.feature.sorting_sheet.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.ListSortUiState
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.SortingSheetProgram
import ru.maksonic.beresta.feature.sorting_sheet.ui.core.SortingSheetSandbox
import ru.maksonic.beresta.feature.sorting_sheet.ui.ui.SortingSheetWidget

/**
 * @Author maksonic on 06.07.2023
 */
val sortingSheetUiFeatureModule = module {
    single<SortingSheetApi.Ui> {
        SortingSheetWidget(object : SharedUiState<ListSortUiState>(ListSortUiState.Initial) {})
    }
    single { SortingSheetProgram(listSortStateUiApi = get(), listSortStateFeatureState = get()) }
    viewModel { SortingSheetSandbox(program = get()) }
}