package ru.maksonic.beresta.screen.settings.appearance.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceProgram
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceSandbox

/**
 * @Author maksonic on 07.07.2023
 */
val settingsAppearanceScreenModule = module {
    single { SettingsAppearanceProgram(noteCardUiApi = get(), noteCardFeatureState = get()) }
    viewModel { SettingsAppearanceSandbox(program = get()) }
}