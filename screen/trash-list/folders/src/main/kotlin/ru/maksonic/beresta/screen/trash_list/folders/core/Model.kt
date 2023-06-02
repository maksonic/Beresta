package ru.maksonic.beresta.screen.trash_list.folders.core

import ru.maksonic.beresta.elm.BaseModel
import ru.maksonic.beresta.elm.ElmCommand
import ru.maksonic.beresta.elm.ElmEffect
import ru.maksonic.beresta.elm.ElmMessage
import ru.maksonic.beresta.elm.ElmModel
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi

/**
 * @Author maksonic on 30.05.2023
 */
data class Model(
    val base: BaseModel,
    val folders: NoteFolderUi.Collection,
    val selectedFolders: Set<NoteFolderUi>,
    val isSelectionState: Boolean,
    ) : ElmModel {
    companion object {
        val Initial = Model(
            base = BaseModel.InitialWithLoading,
            folders = NoteFolderUi.Collection.Empty,
            selectedFolders = emptySet(),
            isSelectionState = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnTopBarBackPressed : Ui()
        object OnTrashedFoldersBtnClicked : Ui()
        data class OnNoteClicked(val id: Long) : Ui()
        data class OnNoteLongClicked(val id: Long) : Ui()
        object CancelSelectionState : Ui()
        object OnSelectAllNotesClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedRemovedNotesResult(val folders: List<NoteFolderUi>) : Inner()
        data class FetchedError(val message: String) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object FetchRemovedData : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateBack : Eff()
    object NavigateToTrashedFoldersList : Eff()
}