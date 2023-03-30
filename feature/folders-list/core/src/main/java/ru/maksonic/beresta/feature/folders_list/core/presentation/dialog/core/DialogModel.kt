package ru.maksonic.beresta.feature.folders_list.core.presentation.dialog.core

import androidx.compose.runtime.Stable
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi

/**
 * @Author maksonic on 30.03.2023
 */
@Stable
data class DialogModel(
    val base: BaseModel = BaseModel.Initial,
    val folderInputName: String = "",
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object AddNewNotesFolder : Ui()
        object OnCreateNewNotesFolderClicked : Ui()
        object OnDismissFolderCreationDialogClicked : Ui()
    }

    sealed class Inner : Msg() {
        data class UpdateNewFolderNameInput(val value: String) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    data class AddNewFolderToCache(val folder: FilterChipUi) : Cmd()
}

sealed class Eff : ElmEffect {
    object ShowNewFolderDialog : Eff()
    object HideNewFolderDialog : Eff()
}