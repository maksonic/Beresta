package ru.maksonic.beresta.screen.settings.security.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.screen.settings.security.core.SettingsSecurityProgram
import ru.maksonic.beresta.screen.settings.security.core.SettingsSecuritySandbox

/**
 * @Author maksonic on 03.08.2023
 */
val settingsSecurityScreenModule = module {
    factory {
        SettingsSecurityProgram(
            pinPrivacySettings = get(),
            fetchHiddenNotesPinStatusUseCase = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    viewModel { SettingsSecuritySandbox(program = get()) }
}