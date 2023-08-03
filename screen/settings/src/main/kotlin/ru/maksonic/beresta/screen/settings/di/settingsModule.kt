package ru.maksonic.beresta.screen.settings.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.settings.core.SettingsProgram
import ru.maksonic.beresta.screen.settings.core.SettingsSandbox

/**
 * @Author maksonic on 30.04.2023
 */
val settingsScreenModule = module {
    single { SettingsProgram(themeSelector = get()) }
    viewModel { SettingsSandbox(program = get()) }
}