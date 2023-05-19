package ru.maksonic.beresta.feature.notes.folders.core.add_folder_dialog.core

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.notes.folders.api.ui.NoteFolderUi

/**
 * @Author maksonic on 30.03.2023
 */
@Stable
data class DialogModel(
    val base: BaseModel,
    val isNewFolder: Boolean,
    val inputFiled: TextFieldValue,
    val currentEditableFolder: NoteFolderUi,
    val supportingText: String,
    val isEmptyFieldError: Boolean,
) : ElmModel {

    companion object {
        val Initial = DialogModel(
            base = BaseModel.Initial,
            isNewFolder = true,
            inputFiled = TextFieldValue(),
            currentEditableFolder = NoteFolderUi.Empty,
            supportingText = "",
            isEmptyFieldError = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object OnAcceptClicked : Ui()
        object OnDismissClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdateNewFolderNameInput(val value: TextFieldValue) : Inner()
        data class FetchFolderResult(val folder: NoteFolderUi) : Inner()
        data class FetchedEditableFolderId(val id: Long) : Inner()
        data class UpdatedFolderStatus(val isNew: Boolean): Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchFolderById(val id: Long) : Cmd()
    data class SaveOrUpdateCurrentFolder(val folder: NoteFolderUi) : Cmd()
}

sealed class Eff : ElmEffect {
    object HideDialog : Eff()
}