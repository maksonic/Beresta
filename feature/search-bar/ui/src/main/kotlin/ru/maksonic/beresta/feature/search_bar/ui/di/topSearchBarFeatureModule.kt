package ru.maksonic.beresta.feature.search_bar.ui.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.ui.SearchBarViewModel
import ru.maksonic.beresta.feature.search_bar.ui.TopSearchBarWidget

/**
 * @Author maksonic on 26.04.2023
 */
val topSearchBarUiFeatureModule = module {
    single<SearchBarApi.Ui> { TopSearchBarWidget() }
    viewModel { SearchBarViewModel() }
}