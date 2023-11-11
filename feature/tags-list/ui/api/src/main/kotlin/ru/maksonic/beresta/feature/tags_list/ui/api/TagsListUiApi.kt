package ru.maksonic.beresta.feature.tags_list.ui.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 05.11.2023
 */
interface TagsListUiApi {
    @Composable
    fun SheetContent(
        isVisible: Boolean,
        passedNoteTagsIds: List<Long>?,
        updatedNoteTags: (List<NoteTagUi>) -> Unit,
        hideSheet: () -> Unit
    )
}