package ru.maksonic.beresta.feature.sorting_sheet.core.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.sorting_sheet.api.SortingSheetApi
import ru.maksonic.beresta.feature.sorting_sheet.core.SortNotesFeatureCore

/**
 * @Author maksonic on 09.07.2023
 */
val listSortCoreFeatureModule = module {
    single<SortingSheetApi.Storage> { SortNotesFeatureCore(datastore = get()) }
}