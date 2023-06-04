package ru.maksonic.beresta.screen.settings.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.settings.SettingsProgram
import ru.maksonic.beresta.screen.settings.SettingsSandbox

/**
 * @Author maksonic on 30.04.2023
 */
val settingsModule = module {
    single { SettingsProgram(themeSelector = get(), darkModeChecker = get()) }
    viewModel { SettingsSandbox(program = get()) }
}