package ru.maksonic.beresta.feature.ui.add_folder_dialog.core.core

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.TextFieldValue
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.platform.elm.core.ElmCommand
import ru.maksonic.beresta.platform.elm.core.ElmEffect
import ru.maksonic.beresta.platform.elm.core.ElmMessage
import ru.maksonic.beresta.platform.elm.core.ElmModel

/**
 * @Author maksonic on 30.03.2023
 */
@Stable
data class Model(
    val currentEditableFolder: FolderUi,
    val inputFiled: TextFieldValue,
    val hintSymbolsCount: String,
    val isEmptyFieldError: Boolean,
    val isFetchedFolder: Boolean
) : ElmModel {

    companion object {
        val Initial = Model(
            currentEditableFolder = FolderUi.Empty,
            inputFiled = TextFieldValue(),
            hintSymbolsCount = "",
            isEmptyFieldError = false,
            isFetchedFolder = false
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
        data object FetchedPassedFolderId : Inner()
        data class FetchedFolderResult(val folder: FolderUi) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data object FetchFolderById : Cmd()
    data class SaveOrUpdateCurrentFolder(val folder: FolderUi) : Cmd()
}

sealed class Eff : ElmEffect {
    data object HideDialog : Eff()
}