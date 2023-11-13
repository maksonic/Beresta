package ru.maksonic.beresta.screen.settings.tags.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogState
import ru.maksonic.beresta.platform.elm.core.ElmBaseModel
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 12.11.2023
 */
@Stable
@Immutable
data class ModalSheet(
    val isVisible: Boolean,
    val skipPartiallyExpanded: Boolean,
) {
    companion object {
        val Initial = ModalSheet(isVisible = false, skipPartiallyExpanded = true)
    }
}

@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val tags: NoteTagUi.Collection,
    val modalSheet: ModalSheet,
    val addTagDialogState: AddTagDialogState,
    val isSelection: Boolean,
    val currentClickedTag: NoteTagUi?

) : ElmModel {

    companion object {
        val Initial = Model(
            base = ElmBaseModel.Loading,
            tags = NoteTagUi.Collection.Empty,
            modalSheet = ModalSheet.Initial,
            addTagDialogState = AddTagDialogState.Initial.copy(isNewTag = false),
            isSelection = false,
            currentClickedTag = null
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnTopBarBackPressed : Ui()
        data class OnTagClicked(val tag: NoteTagUi) : Ui()
        data object OnSelectAllTagsClicked : Ui()
        data object CancelSelectionState : Ui()
        data object HideModalBottomSheet : Ui()
        data object OnModalSheetRenameTagClicked : Ui()
        data object OnModalSheetDeleteTagClicked : Ui()
        data object OnCreateNewTagClicked: Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedDataResult(val tags: NoteTagUi.Collection) : Inner()
        data class UpdatedModalSheetState(val isVisible: Boolean) : Inner()
        data object HiddenAddTagDialog : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchData : Cmd()
    data class DeleteTag(val tag: NoteTagUi) : Cmd()
}

sealed class Eff : ElmEffect {
    data object NavigateBack : Eff()
    data object HideModalSheet : Eff()
}