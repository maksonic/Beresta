package ru.maksonic.beresta.screen.main.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.main.MainProgram
import ru.maksonic.beresta.screen.main.MainSandbox

/**
 * @Author maksonic on 25.04.2023
 */
val mainScreenModule = module {
    single { MainProgram(foldersListUseCase = get(), foldersMapper = get()) }
    viewModel { MainSandbox(mainProgram = get()) }
}