package ru.maksonic.beresta.feature.ui.edit_note.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiApi
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Container
import ru.maksonic.beresta.navigation.router.routes.EditNoteRouter

/**
 * @Author maksonic on 15.10.2023
 */
class EditNoteUiCore : EditNoteUiApi {
    @Composable
    override fun ExpandableScreen(
        router: EditNoteRouter?,
        state: EditNoteFabState,
        updateFabState: (EditNoteFabState) -> Unit,
        isEntryPoint: Boolean,
        isHiddenNote: Boolean,
        modifier: Modifier
    ) {
        Container(
            router = router,
            state = state,
            updateFabState = updateFabState,
            isEntryPoint = isEntryPoint,
            isCircleFab = isHiddenNote,
            modifier = modifier
        )
    }
}