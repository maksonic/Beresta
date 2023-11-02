package ru.maksonic.beresta.navigation.router.routes

/**
 * @Author maksonic on 18.07.2023
 */
data class HiddenNotesRouter(
    val onBack: () -> Unit,
    val toNoteEditor: (id: Long?) -> Unit
)