package ru.maksonic.beresta.feature.edit_note.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.core.SharedUiState

/**
 * @Author maksonic on 23.02.2023
 */
interface EditNoteApi {

    interface Ui {
        @Composable
        fun NewNoteFabWidget(
            isVisible: Boolean, isNotesScrollUp: State<Boolean>, modifier: Modifier
        )

        val sharedUiState: SharedUiState<EditNoteFabUiSharedState>

    }
}