package ru.maksonic.beresta.feature.tags_list.ui.core

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
 * @Author maksonic on 05.11.2023
 */
@Stable
@Immutable
data class Model(
    val base: ElmBaseModel,
    val tags: NoteTagUi.Collection,
    val addTagDialogState: AddTagDialogState,
    val isFetchedDefaultTags: Boolean
    ) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            tags = NoteTagUi.Collection.Empty,
            addTagDialogState = AddTagDialogState.Initial,
            isFetchedDefaultTags = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnTagClicked(val id: Long): Ui()
        data object OnCreateNewTagClicked: Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedTags(val tagsIds: List<Long>?) : Inner()
        data class FetchedTagsResult(val tags: NoteTagUi.Collection) : Inner()
        data object HiddenAddTagDialog : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchTags(val noteTagsIds: List<Long>?) : Cmd()
}

sealed class Eff : ElmEffect {
    data class UpdateNoteTags(val tags: List<NoteTagUi>): Eff()
}