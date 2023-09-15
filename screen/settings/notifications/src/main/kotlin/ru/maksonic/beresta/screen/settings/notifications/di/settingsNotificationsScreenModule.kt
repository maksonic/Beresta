package ru.maksonic.beresta.screen.settings.notifications.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.screen.settings.notifications.core.SettingsNotificationsSandbox
import ru.maksonic.beresta.screen.settings.notifications.core.programs.SettingsVibrationProgram

/**
 * @Author maksonic on 07.07.2023
 */
val settingsNotificationsScreenModule = module {
    single { SettingsVibrationProgram(vibrationPerformer = get()) }
    viewModel { SettingsNotificationsSandbox(vibrationProgram = get()) }
}