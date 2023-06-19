package ru.maksonic.beresta.feature.search_bar.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.search_bar.api.SearchBarApi
import ru.maksonic.beresta.feature.search_bar.core.SearchBarSandbox
import ru.maksonic.beresta.feature.search_bar.core.SearchProgram
import ru.maksonic.beresta.feature.search_bar.core.ui.widget.TopSearchBarWidget

/**
 * @Author maksonic on 26.04.2023
 */
val topSearchBarFeatureModule = module {
    single {
        SearchProgram(
            fetchNotesUseCase = get(),
            mapper = get(),
            appLanguageEngineApi = get(),
            dateFormatter = get()
        )
    }
    single<SearchBarApi.Ui> { TopSearchBarWidget() }
    viewModel { SearchBarSandbox(program = get()) }
}