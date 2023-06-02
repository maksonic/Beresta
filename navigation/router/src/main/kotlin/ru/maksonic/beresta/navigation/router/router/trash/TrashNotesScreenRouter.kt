package ru.maksonic.beresta.navigation.router.router.trash

/**
 * @Author maksonic on 24.01.2023
 */
data class TrashNotesScreenRouter(
    val onBack: () -> Unit,
    val toTrashedFoldersList : () -> Unit
)