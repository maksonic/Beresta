package ru.maksonic.beresta.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.core.MainActivityProgram
import ru.maksonic.beresta.core.MainActivitySandbox

/**
 * @Author maksonic on 18.06.2023
 */
internal val appModule = module {
    single {
        MainActivityProgram(
            theme = get(), palette = get(), language = get(), languageProvider = get()
        )
    }
    viewModel { MainActivitySandbox(mainActivityProgram = get()) }
}