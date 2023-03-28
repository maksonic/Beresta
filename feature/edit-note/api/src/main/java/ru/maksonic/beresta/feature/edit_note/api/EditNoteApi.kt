package ru.maksonic.beresta.feature.edit_note.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @Author maksonic on 23.02.2023
 */
interface EditNoteApi {

    interface Ui {
        @Composable
        fun NewNoteFabWidget(isNotesScrollUp: () -> Boolean, modifier: Modifier)
    }
}