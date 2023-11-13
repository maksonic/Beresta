package ru.maksonic.beresta.screen.settings.tags.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.settings.tags.core.SettingsTagsProgram
import ru.maksonic.beresta.screen.settings.tags.core.SettingsTagsSandbox

/**
 * @Author maksonic on 12.11.2023
 */
val settingsTagsScreenModule = module {
    factory { SettingsTagsProgram(repository = get(), tagUiMapper = get()) }

    viewModel { SettingsTagsSandbox(program = get()) }
}