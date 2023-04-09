package ru.maksonic.beresta.feature.folders_list.core.dialog.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi

/**
 * @Author maksonic on 30.03.2023
 */
@Stable
data class DialogModel(
    val base: BaseModel,
    val currentEditableFolder: FilterChipUi = FilterChipUi.Empty,
    val supportingText: String,
    val isEmptyFieldError: Boolean,
) : ElmModel {

    companion object {
        val Initial = DialogModel(
            base = BaseModel.Initial,
            supportingText = "0/50",
            isEmptyFieldError = false,
        )
    }
}

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object AddNewNotesFolder : Ui()
        object OnCreateNewNotesFolderClicked : Ui()
        object OnDismissFolderCreationDialogClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdateNewFolderNameInput(val value: String) : Inner()
        data class FillTextFieldByFolderName(val folder: FilterChipUi) : Inner()
        data class FetchedEditableFolderId(val id: Long) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class FetchFolderById(val id: Long) : Cmd()
    data class SaveNewFolderToCache(val folder: FilterChipUi) : Cmd()
    data class UpdateFolder(val folder: FilterChipUi) : Cmd()
}

sealed class Eff : ElmEffect {
    object ShowEmptyFieldError : Eff()
    object ShowNewFolderDialog : Eff()
    object HideNewFolderDialog : Eff()
}