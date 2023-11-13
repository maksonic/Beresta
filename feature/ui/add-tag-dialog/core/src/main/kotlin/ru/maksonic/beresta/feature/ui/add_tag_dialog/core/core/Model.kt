package ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 13.11.2023
 */
@Stable
data class Model(
    val currentEditableTag: NoteTagUi,
    val inputFiled: TextFieldValue,
    val hintSymbolsCount: String,
    val isEmptyFieldError: Boolean,
    val isFetchedTag: Boolean
) : ElmModel {

    companion object {
        val Initial = Model(
            currentEditableTag = NoteTagUi.Default,
            inputFiled = TextFieldValue(),
            hintSymbolsCount = "",
            isEmptyFieldError = false,
            isFetchedTag = false
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        data object OnAcceptClicked : Ui()
        data object OnDismissClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdatedInputField(val value: TextFieldValue) : Inner()
        data class FetchedPassedTagId(val id: Long) : Inner()
        data class FetchedTagResult(val tag: NoteTagUi) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchTagById(val id: Long) : Cmd()
    data class SaveOrUpdateCurrentTag(val tag: NoteTagUi) : Cmd()
}

sealed class Eff : ElmEffect {
    data object HideDialog : Eff()
}