package ru.maksonic.beresta.feature.sorting_sheet.ui.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * @Author maksonic on 16.10.2023
 */
val LocalListNotesSortState = staticCompositionLocalOf<NotesSortUi> {
    error("No notes list sort state provided")
}
val LocalListHiddenNotesSortState = staticCompositionLocalOf<NotesSortUi> {
    error("No hidden notes list sort state provided")
}
val LocalListFoldersSortState = staticCompositionLocalOf<FoldersSortUi> {
    error("No folders list sort state provided")
}

val listNotesSortState: NotesSortUi
    @Composable get() = LocalListNotesSortState.current
val listHiddenNotesSortState: NotesSortUi
    @Composable get() = LocalListHiddenNotesSortState.current
val listFoldersSortState: FoldersSortUi
    @Composable get() = LocalListFoldersSortState.current
