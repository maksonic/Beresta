package ru.maksonic.beresta.feature.notes.list.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 24.04.2023
 */
@Stable
@Immutable
data class Model(
    val base: BaseModel,
    val notes: NoteUi.Collection,
    val errorMsg: String,
    val isSelectionState: Boolean,
    val isShowUnpinMainBarIcon: Boolean,
    val selectedNotes: Set<NoteUi>,
    val removedNotes: Set<NoteUi> = emptySet(),
    val gridCount: Int,
    val isVisibleRemovedSnackBar: Boolean,
    val currentAppLanguage: AppLanguage,
    val currentSelectedFolderId: Long = NoteFolderUi.StartListFolder.id,

    ): ElmModel {

    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            notes = NoteUi.Collection.Empty,
            errorMsg = "Error",
            isSelectionState = false,
            isShowUnpinMainBarIcon = false,
            selectedNotes = emptySet(),
            gridCount = 1,
            isVisibleRemovedSnackBar = false,
            currentAppLanguage = AppLanguage.RUSSIAN
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        object OnBarMoveToTrashClicked : Ui()
        object OnBarMoveToSecureFolderClicked : Ui()
        object OnBarPinToTopOfListClicked : Ui()
        object OnBarReplaceToFolderClicked : Ui()
        object OnSelectAllNotesClicked : Ui()
        object OnShareSelectedClicked : Ui()
        object CancelSelectionState : Ui()
        object OnChangeGridCountClicked : Ui()
        object OnSnackUndoRemoveNotesClicked : Ui()

    }
    sealed class Inner : Msg() {
        data class FetchedResultData(val notes: NoteUi.Collection): Inner()
        data class FetchedResultError(val errorMsg: String = ""): Inner()
        object ShowRemovedNotesSnackBar : Inner()
        object HideRemovedNotesSnackBar : Inner()
        data class FetchedCurrentAppLang(val language: AppLanguage): Inner()
        data class FilteredNotesByFolder(val id: Long): Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNotesList : Cmd()
    data class RemoveSelected(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: Set<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    object FetchCurrentLangForNotesDatestamp : Cmd()
}

sealed class Eff : ElmEffect {
    data class NavigateToEditNote(val id: Long) : Eff()
    object NavigateToFoldersWithMovingState : Eff()
    data class UpdateSharedUiIsSelectedState(val isSelectionState: Boolean) : Eff()
}