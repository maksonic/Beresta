package ru.maksonic.beresta.screen.settings.security.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.settings.security.core.SettingsSecurityProgram
import ru.maksonic.beresta.screen.settings.security.core.SettingsSecuritySandbox

/**
 * @Author maksonic on 03.08.2023
 */
val settingsSecurityScreenModule = module {
    single { SettingsSecurityProgram(pinSecurePrefs = get()) }
    viewModel { SettingsSecuritySandbox(program = get()) }
}