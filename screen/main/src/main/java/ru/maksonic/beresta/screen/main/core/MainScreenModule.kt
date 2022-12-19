package ru.maksonic.beresta.screen.main.core

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

/**
 * @Author maksonic on 15.12.2022
 */
val mainScreenModule = module {
    viewModelOf(::MainSandbox)
}