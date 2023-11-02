package ru.maksonic.beresta.navigation.router.routes.trash

/**
 * @Author maksonic on 24.01.2023
 */
data class TrashNotesRouter(
    val onBack: () -> Unit,
    val toTrashedFoldersList : () -> Unit
)