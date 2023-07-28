package ru.maksonic.beresta.navigation.router.router

/**
 * @Author maksonic on 24.01.2023
 */
data class MainScreenRouter(
    val toSettings: () -> Unit,
    val toTrash: () -> Unit,
    val toNoteEditor: (id: Long?) -> Unit,
    val toFoldersList: (passedListIds: List<Long>) -> Unit,
    val toHiddenNotes: (passedListIds: List<Long>) -> Unit
)