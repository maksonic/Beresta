package ru.maksonic.beresta.feature.notes.list.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
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
    val isVisibleUnpinMainBarIcon: Boolean,
    val selectedNotes: Set<NoteUi>,
    val removedNotes: Set<NoteUi> = emptySet(),
    val gridCount: Int,
    val isVisibleRemovedSnackBar: Boolean,
    val currentAppLanguage: AppLanguage,
    val currentSelectedFolderId: Long = 1L,

    ) : ElmModel {

    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            notes = NoteUi.Collection.Empty,
            errorMsg = "Error",
            isSelectionState = false,
            isVisibleUnpinMainBarIcon = false,
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
        data class FetchedResultData(val notes: NoteUi.Collection) : Inner()
        data class FetchedResultError(val errorMsg: String = "") : Inner()
        object HideRemovedNotesSnackBar : Inner()
        data class FetchedCurrentAppLang(val language: AppLanguage) : Inner()
        data class FetchedCurrentFolderIdByPassedState(val id: Long) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchNotesList : Cmd()
    data class RemoveSelected(val notes: List<NoteUi>) : Cmd()
    data class UndoRemoveNotes(val notes: List<NoteUi>) : Cmd()
    data class UpdatePinnedNotesInCache(val pinned: Set<NoteUi>) : Cmd()
    object FetchCurrentLangForNotesDatestamp : Cmd()
}

sealed class Eff : ElmEffect {
    data class NavigateToEditNote(val id: Long) : Eff()
    data class NavigateToFoldersWithMovingState(val currentSelectedFolderId: Long) : Eff()
    data class UpdateSharedUiIsSelectedState(val isSelectionState: Boolean) : Eff()
    data class UpdateSharedUiIsEnabledBottomBarState(val isEnabled: Boolean) : Eff()
    data class UpdatePassedNotesSharedState(val notes: List<NoteUi>) : Eff()
}