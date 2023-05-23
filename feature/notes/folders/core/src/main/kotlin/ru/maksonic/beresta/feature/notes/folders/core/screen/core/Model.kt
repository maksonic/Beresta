package ru.maksonic.beresta.feature.notes.folders.core.screen.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi

/**
 * @Author maksonic on 03.04.2023
 */
@Stable
data class Model(
    val base: BaseModel,
    val folders: NoteFolderUi.Collection,
    val selectedFolders: Set<NoteFolderUi>,
    val removedFolders: Set<NoteFolderUi> = emptySet(),
    val currentSelectedFolderId: Long,
    val isSelectionState: Boolean,
    val isShowUnpinBottomBarIcon: Boolean,
    val isVisibleRemovedSnackBar: Boolean,
    val isMoveNotesToFolder: Boolean,
    val moveNotesList: List<NoteUi>
    ) : ElmModel {

    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            folders = NoteFolderUi.Collection.Empty,
            selectedFolders = emptySet(),
            currentSelectedFolderId = 1L,
            isSelectionState = false,
            isShowUnpinBottomBarIcon = false,
            isVisibleRemovedSnackBar = false,
            isMoveNotesToFolder = false,
            moveNotesList = emptyList()
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        data class OnFolderClicked(val id: Long) : Ui()
        data class OnFolderLongPressed(val id: Long) : Ui()
        object CancelSelectionState: Ui()
        object OnSelectAllFoldersClicked : Ui()
        object OnAddNewFolderClicked : Ui()
        object OnBarPinClicked: Ui()
        object OnBarRemoveClicked : Ui()
        object OnBarEditClicked: Ui()
        object OnSnackUndoRemoveFoldersClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedFoldersResult(val folders: List<NoteFolderUi>) : Inner()
        object ShowRemovedNotesSnackBar : Inner()
        object HideRemovedNotesSnackBar : Inner()
        data class FetchedPassedArgsFromMain(val isMove: Boolean, val id: Long) : Inner()
        data class FetchedPassedReplaceNotesState(val notes: List<NoteUi>) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchPassedFromMainScreenArgs : Cmd()
    object FetchFolders : Cmd()
    data class RemoveSelected(val folders: List<NoteFolderUi>) : Cmd()
    data class UndoRemoveNotes(val folders: List<NoteFolderUi>) : Cmd()
    data class UpdatePinnedFoldersInCache(val pinned: Set<NoteFolderUi>) : Cmd()
    data class ChangeNoteFolderId(val folderId: Long, val notes: List<NoteUi>) : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    data class UpdateCurrentSelectedFolderInSharedState(val id: Long): Eff()
    data class ShowFolderDialog(val isNewFolder: Boolean, val id: Long = 0L) : Eff()
    object ClearPassedForReplaceNotes : Eff()
}