package ru.maksonic.beresta.navigation.router.routes

/**
 * @Author maksonic on 03.04.2023
 */
data class FoldersRouter(
    val onBack: () -> Unit,
    val toHiddenNotes: () -> Unit,
)