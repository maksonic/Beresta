package ru.maksonic.beresta.screen.settings.appearance.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.core.SharedUiState
import ru.maksonic.beresta.screen.settings.appearance.core.AnimationVelocity
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceProgram
import ru.maksonic.beresta.screen.settings.appearance.core.SettingsAppearanceSandbox
import ru.maksonic.beresta.ui.theme.component.AppAnimationVelocity

/**
 * @Author maksonic on 07.07.2023
 */
val settingsAppearanceScreenModule = module {
    single<AnimationVelocity> {
        val currentAnimationVelocity =
            object : SharedUiState<AppAnimationVelocity.Key>(AppAnimationVelocity.Key.NORMAL) {}

        AnimationVelocity.Core(
            datastore = get(),
            current = currentAnimationVelocity
        )
    }

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