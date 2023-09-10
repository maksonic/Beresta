package ru.maksonic.beresta.screen.settings.appearance.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.settings.appearance.core.AnimationVelocity
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceProgram
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceSandbox

/**
 * @Author maksonic on 07.07.2023
 */
val settingsAppearanceScreenModule = module {
    single<AnimationVelocity> { AnimationVelocity.Core(datastore = get()) }

    single {
        SettingsAppearanceProgram(
            noteCardUiApi = get(),
            noteCardFeatureState = get(),
            languageEngineApi = get(),
            animationVelocity = get()
        )
    }
    viewModel { SettingsAppearanceSandbox(program = get()) }
}