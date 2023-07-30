package ru.maksonic.beresta.feature.edit_note.ui.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.edit_note.ui.Msg
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter

/**
 * @Author maksonic on 26.04.2023
 */
internal typealias SendMessage = (Msg) -> Unit

class EditNoteExpandableScreen : EditNoteApi.Ui {

    @Composable
    override fun ExpandableScreen(
        router: EditNoteRouter?,
        state: State<EditNoteFabState>,
        updateFabState: (EditNoteFabState) -> Unit,
        isEntryPoint: Boolean,
        isHiddenNotes: Boolean,
        modifier: Modifier
    ) {
        Container(
            router = router,
            state = state,
            updateFabState = updateFabState,
            isEntryPoint = isEntryPoint,
            isCircleFab = isHiddenNotes,
            modifier = modifier
        )
    }
}

