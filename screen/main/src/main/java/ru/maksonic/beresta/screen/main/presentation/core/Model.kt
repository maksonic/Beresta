package ru.maksonic.beresta.screen.main.presentation.core

import androidx.compose.runtime.Stable
import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 16.01.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel.InitialWithLoading,
    val entry: NavBackStackEntry? = null,
    val notes: NoteUi.Collection = NoteUi.Collection.Empty,
    val allNotes: List<NoteUi> = emptyList(),
    val selectedNotes: Set<NoteUi> = emptySet(),
    val removedNotes: Set<NoteUi> = emptySet(),
    val filters: FilterChipUi.Collection = FilterChipUi.Collection.Empty,
    val errorMsg: String = "",
    val isVisibleBottomBar: Boolean = true,
    val bottomBarState: MainBottomBarState = MainBottomBarState.IDLE,
    val isSelectionState: Boolean = false,
    val isShowBottomBarUnpinBtn: Boolean = false,
    val selectedNotesCount: Int = 0,
    val notesGridCount: Int = 1,
    val isVisibleNewFolderDialog: Boolean = false,
    val isVisibleUndoRemoveNotesSnack: Boolean = false,
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCreateNewNoteClicked : Ui()

        // Idle bottom panel messages
        object OnBottomBarSettingsClicked : Ui()
        object OnBottomBarTrashClicked : Ui()
        object OnBottomBarOpenFoldersClicked : Ui()
        object OnBottomBarSortNotesByClicked : Ui()
        object OnSwitchViewClicked : Ui()

        // Selected bottom panel messages
        object OnHideSelectedNotesBottomBarClicked : Ui()
        object OnPinSelectedNotesBottomBarClicked : Ui()
        object OnReplaceNoteToFolderClicked : Ui()
        object OnRemoveSelectedNotesClicked : Ui()
        object OnCancelSelectionClicked : Ui()
        object OnSelectAllNotesClicked : Ui()

        object OnShareSelectedNotes : Ui()
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongPressed(val id: Long) : Ui()
        data class OnChipFilterClicked(val id: Long) : Ui()
        object OnRemoveNotesUndoClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedDataResult(
            val notes: NoteUi.Collection,
            val folders: FilterChipUi.Collection
        ) : Inner()

        data class FetchedError(val message: String) : Inner()
        data class UpdatedNewFolderDialogVisibility(val isVisible: Boolean) : Inner()
        object HideRemovedNotesSnack : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object RunFetchingNotesCollection : Cmd()
    data class RemoveSelected(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoved(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: List<NoteUi>) : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateToAddNewNote : Eff()
    object NavigateToSettings : Eff()
    object NavigateToTrash : Eff()
    data class ShowNoteForEdit(val id: Long) : Eff()
}