package ru.maksonic.beresta.navigation.router.routes

/**
 * @Author maksonic on 24.01.2023
 */
data class MainRouter(
    val toSettings: () -> Unit,
    val toTrash: () -> Unit,
    val toNoteEditor: (id: Long?) -> Unit,
    val toFoldersList: (passedListIds: List<Long>) -> Unit,
    val toHiddenNotes: () -> Unit)