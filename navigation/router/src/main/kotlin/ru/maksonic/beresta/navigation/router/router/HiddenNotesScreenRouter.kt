package ru.maksonic.beresta.navigation.router.router

/**
 * @Author maksonic on 18.07.2023
 */
data class HiddenNotesScreenRouter(
    val onBack: () -> Unit,
    val toNoteEditor: (id: Long?) -> Unit
)