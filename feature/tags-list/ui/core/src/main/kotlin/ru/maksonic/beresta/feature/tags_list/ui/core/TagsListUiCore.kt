package ru.maksonic.beresta.feature.tags_list.ui.core

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.common.ui_kit.sheet.ModalSheetContainer
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagsListUiApi

/**
 * @Author maksonic on 05.11.2023
 */
class TagsListUiCore : TagsListUiApi {
    @Composable
    override fun SheetContent(
        isVisible: Boolean,
        passedNoteTagsIds: List<Long>?,
        updatedNoteTags: (List<NoteTagUi>) -> Unit,
        hideSheet: () -> Unit
    ) {
        ModalSheetContainer(isVisible = isVisible, hideSheet = hideSheet) {
            Container(passedNoteTagsIds, updatedNoteTags, hideSheet)
        }
    }
}