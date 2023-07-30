package ru.maksonic.beresta.feature.folders_chips.ui.dialog.core

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.elm.core.ElmCommand
import ru.maksonic.beresta.elm.core.ElmEffect
import ru.maksonic.beresta.elm.core.ElmMessage
import ru.maksonic.beresta.elm.core.ElmModel
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi

/**
 * @Author maksonic on 30.03.2023
 */
@Stable
data class Model(
    val currentEditableFolder: FolderUi,
    val inputFiled: TextFieldValue,
    val supportingText: String,
    val isEmptyFieldError: Boolean,
) : ElmModel {

    companion object {
        val Initial = Model(
            currentEditableFolder = FolderUi.Empty,
            inputFiled = TextFieldValue(),
            supportingText = "",
            isEmptyFieldError = false,
        )
    }

    fun resetInput() = this.inputFiled.copy("", selection = TextRange.Zero)
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnAcceptClicked : Ui()
        object OnDismissClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdateNewFolderNameInput(val value: TextFieldValue) : Inner()
        data class FetchedPassedFolderId(val id: Long) : Inner()
        data class FetchFolderData(val folder: FolderUi) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchFolderById(val id: Long) : Cmd()
    data class SaveOrUpdateCurrentFolder(val folder: FolderUi) : Cmd()
}

sealed class Eff : ElmEffect {
    object HideDialog : Eff()
}