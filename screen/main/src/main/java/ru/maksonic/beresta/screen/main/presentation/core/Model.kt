package ru.maksonic.beresta.screen.main.presentation.core

import androidx.compose.runtime.Stable
import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.notes_list.api.ui.MainBottomBarState
import ru.maksonic.beresta.feature.notes_list.api.ui.NoteUi

/**
 * @Author maksonic on 16.01.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(isLoading = true),
    val entry: NavBackStackEntry? = null,
    val notes: NoteUi.Collection = NoteUi.Collection.Empty,
    val filters: FilterChipUi.Collection = FilterChipUi.Collection.Preview,
    val errorMsg: String = "Неизвестная ошибка",
    val isVisibleBottomBar: Boolean = true,
    val bottomBarState: MainBottomBarState = MainBottomBarState.IDLE,
    val isSelectionState: Boolean = false,
    val isShowBottomBarUnpinBtn: Boolean = false,
    val selectedNotesCount: Int = 0,
    val notesGridCount: Int = 1,
    val isVisibleNewFolderDialog: Boolean = false,
    val newFolderInputName: String = "",
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnCreateNewNoteClicked : Ui()
        object OnAddNewFilterFolderClicked : Ui()
        object OnCreateNewNotesFolderClicked: Ui()
        object OnDismissFolderCreationDialogClicked: Ui()

        // Idle bottom panel messages
        object OnBottomBarSettingsClicked : Ui()
        object OnBottomBarTrashClicked : Ui()
        object OnBottomBarOpenFoldersClicked : Ui()
        object OnBottomBarSortNotesByClicked : Ui()
        object OnSwitchViewClicked : Ui()
        // Selected bottom panel messages
        object OnHideSelectedNotesBottomBarClicked: Ui()
        object OnPinSelectedNotesBottomBarClicked: Ui()
        object OnReplaceFolderSelectedNotesBottomBarClicked: Ui()
        object OnRemoveSelectedNotesBottomBarClicked: Ui()
        object OnCancelSelectionClicked : Ui()
        object OnSelectAllNotesClicked : Ui()

        object OnShareSelectedNotes : Ui()
        data class OnNoteClicked(val id: Long): Ui()
        data class OnNoteLongPressed(val id: Long): Ui()
        data class OnChipFilterClicked(val id: Int): Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedNotesCollection(val notes: NoteUi.Collection): Inner()
        data class FetchedError(val message: String): Inner()
        data class UpdateNewFolderNameInput(val value: String): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object RunFetchingNotesCollection : Cmd()
    data class RemoveSelected(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: List<NoteUi>): Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateToAddNewNote : Eff()
    object NavigateToSettings : Eff()
    object NavigateToTrash : Eff()
    data class ShowNoteForEdit(val id: Long) : Eff()
}