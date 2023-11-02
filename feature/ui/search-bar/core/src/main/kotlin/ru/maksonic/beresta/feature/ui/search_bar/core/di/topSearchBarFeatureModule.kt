package ru.maksonic.beresta.feature.ui.search_bar.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.ui.search_bar.api.SearchBarUiApi
import ru.maksonic.beresta.feature.ui.search_bar.core.SearchBarViewModel
import ru.maksonic.beresta.feature.ui.search_bar.core.SearchBarUiCore

/**
 * @Author maksonic on 26.04.2023
 */
val searchBarUiFeatureModule = module {
    single<SearchBarUiApi> { SearchBarUiCore() }
    viewModel { SearchBarViewModel() }
}