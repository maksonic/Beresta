package ru.maksonic.beresta.feature.sorting_sheet.data.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.sorting_sheet.data.FetchFoldersSortUseCaseImpl
import ru.maksonic.beresta.feature.sorting_sheet.data.FetchHiddenNotesSortUseCaseImpl
import ru.maksonic.beresta.feature.sorting_sheet.data.FetchNotesSortUseCaseImpl
import ru.maksonic.beresta.feature.sorting_sheet.data.SortDataStore
import ru.maksonic.beresta.feature.sorting_sheet.data.SortRepositoryImpl
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortInteractor
import ru.maksonic.beresta.feature.sorting_sheet.domain.SortRepository
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchFoldersSortUseCase
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchHiddenNotesSortUseCase
import ru.maksonic.beresta.feature.sorting_sheet.domain.usecase.FetchNotesSortUseCase

/**
 * @Author maksonic on 16.10.2023
 */
val sortingSheetDataFeatureModule = module {
    single { SortDataStore(datastore = get()) }
    single<SortRepository> { SortRepositoryImpl(sortDataStore = get()) }
    factory<SortInteractor> { SortInteractor.Core(repository = get()) }
    factory<FetchNotesSortUseCase> { FetchNotesSortUseCaseImpl(repository = get()) }
    factory<FetchHiddenNotesSortUseCase> { FetchHiddenNotesSortUseCaseImpl(repository = get()) }
    factory<FetchFoldersSortUseCase> { FetchFoldersSortUseCaseImpl(repository = get()) }
}