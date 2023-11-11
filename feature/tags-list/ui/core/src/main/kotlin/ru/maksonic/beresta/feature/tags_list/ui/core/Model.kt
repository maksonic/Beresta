package ru.maksonic.beresta.feature.tags_list.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
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
    val isFetchedDefaultTags: Boolean,
    val isVisibleAddTagDialog: Boolean,
    val newTagInputField: TextFieldValue,
    val hintSymbolsCount: String,
    val isEmptyFieldError: Boolean,

    ) : ElmModel {
    companion object {
        val Initial = Model(
            base = ElmBaseModel.Initial,
            tags = NoteTagUi.Collection.Empty,
            isFetchedDefaultTags = false,
            isVisibleAddTagDialog = false,
            newTagInputField = TextFieldValue(),
            hintSymbolsCount = "0/50",
            isEmptyFieldError = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data class OnTagClicked(val id: Long): Ui()
        data object OnAcceptTagCreationClicked: Ui()
    }

    sealed class Inner : Msg() {
        data class FetchedTags(val tagsIds: List<Long>?) : Inner()
        data class FetchedTagsResult(val tags: NoteTagUi.Collection) : Inner()
        //data class FetchedNoteTags(val tagsIds: List<Long>) : Inner()
       // data class FetchedNoteTagsResult(val tags: NoteTagUi.Collection) : Inner()
        data class UpdatedAddTagDialogVisibility(val isVisible: Boolean) : Inner()
        data class UpdatedInputField(val value: TextFieldValue) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchTags(val noteTagsIds: List<Long>?) : Cmd()
    data class SaveNewTag(val tag: NoteTagUi): Cmd()
    //data class UpdateTags(val tags: List<NoteTagUi>, val noteTagsIds: List<Long>): Cmd()
}

sealed class Eff : ElmEffect {
    data class UpdateNoteTags(val tags: List<NoteTagUi>): Eff()
}