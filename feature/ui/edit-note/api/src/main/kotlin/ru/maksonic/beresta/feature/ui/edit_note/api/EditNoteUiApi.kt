package ru.maksonic.beresta.feature.ui.edit_note.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.navigation.router.routes.EditNoteRouter

/**
 * @Author maksonic on 26.04.2023
 */
interface EditNoteUiApi {
    @Composable
    fun ExpandableScreen(
        router: EditNoteRouter?,
        state: EditNoteFabState,
        updateFabState: (EditNoteFabState) -> Unit,
        isEntryPoint: Boolean,
        isHiddenNote: Boolean,
        modifier: Modifier
    )
}