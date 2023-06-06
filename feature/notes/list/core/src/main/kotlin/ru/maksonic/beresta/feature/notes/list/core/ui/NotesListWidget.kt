package ru.maksonic.beresta.feature.notes.list.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListSharedUiState
import ru.maksonic.beresta.feature.notes.list.api.ui.SortedNotes
import ru.maksonic.beresta.feature.notes.list.core.sort.ui.SortNotesModalSheetContent
import ru.maksonic.beresta.feature.notes.list.core.ui.widget.NotesLoaderWidgetContent
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter
import ru.maksonic.beresta.ui.theme.images.AddNotePlaceholder
import ru.maksonic.beresta.ui.theme.images.AppImage
import ru.maksonic.beresta.ui.widget.placeholder.ScreenPlaceholder

/**
 * @Author maksonic on 24.04.2023
 */
class NotesListWidget : NotesListApi.Ui {
    override val sharedUiState = NotesListSharedUiState.Initial

    @Composable
    override fun ListWidget(router: MainScreenRouter) {
        NotesListContainer(sharedUiState = sharedUiState, router = router)
    }

    @Composable
    override fun NoteListItem(
        isSelected: Boolean,
        note: NoteUi,
        onNoteClicked: (id: Long) -> Unit,
        onNoteLongClicked: (id: Long) -> Unit,
        isTrashPlacement: Boolean,
        currentAppLang: AppLanguage,
        formatter: DateFormatter,
        modifier: Modifier
    ) {
        NoteListItemContent(
            note = note,
            isSelected = isSelected,
            onNoteClicked = onNoteClicked,
            onNoteLongClicked = onNoteLongClicked,
            isTrashPlacement = isTrashPlacement,
            currentAppLang = currentAppLang,
            formatter = formatter,
            modifier = modifier
        )
    }

    @Composable
    override fun EmptyListWidget() {
        ScreenPlaceholder(
            imageVector = AppImage.AddNotePlaceholder,
            message = text.shared.hintNoNotes
        )
    }

    @Composable
    override fun NotesLoaderWidget(modifier: Modifier) {
        NotesLoaderWidgetContent(modifier)
    }

    @Composable
    override fun SortNotesModalSheet(
        currentSortItemSelected: MutableState<SortedNotes>,
        checkboxSortPinned: MutableState<Boolean>,
        onBtnSaveClicked: () -> Unit
    ) {
        SortNotesModalSheetContent(
            currentSortItemSelected = currentSortItemSelected,
            checkboxSortPinned = checkboxSortPinned,
            onBtnSaveClicked = onBtnSaveClicked
        )
    }
}