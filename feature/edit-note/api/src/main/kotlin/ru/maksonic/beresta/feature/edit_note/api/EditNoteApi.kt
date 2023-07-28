package ru.maksonic.beresta.feature.edit_note.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter

/**
 * @Author maksonic on 26.04.2023
 */
interface EditNoteApi {
    interface Ui {
        @Composable
        fun ExpandableScreen(
            router: EditNoteRouter?,
            isEntryPoint: Boolean,
            isHiddenNotes: Boolean,
            modifier: Modifier
        )
    }
}