package ru.maksonic.beresta.navigation.router.router.settings

/**
 * @Author maksonic on 24.01.2023
 */
data class SettingsScreenRouter(
    val onBack: () -> Unit,
    val toAppearance: () -> Unit,
    val toSecurity: () -> Unit,
)