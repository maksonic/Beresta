package ru.maksonic.beresta.navigation.router.routes.settings

/**
 * @Author maksonic on 24.01.2023
 */
data class SettingsScreenRouter(
    val onBack: () -> Unit,
    val toAppearance: () -> Unit,
    val toNotifications: () -> Unit,
    val toSecurity: () -> Unit,
)