package ru.maksonic.beresta.screen.main.presentation.core

import androidx.compose.runtime.Stable
import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.elm.*

/**
 * @Author maksonic on 16.01.2023
 */
@Stable
data class Model(
    val base: BaseModel = BaseModel(),
    val entry: NavBackStackEntry? = null,
    val isVisibleTopBar: Boolean = true,
    val isColoredTopBar: Boolean = false,
    val isVisibleBottomBar: Boolean = true,
) : ElmModel

sealed class Msg : ElmMessage {
    sealed class Ui : Msg() {
        object CreateNewNote : Ui()
        object OnSettingsClicked : Ui()
        object OnTrashClicked : Ui()
        object OnSearchClicked : Ui()
        object OnShareSelectedNotes : Ui()
    }

    sealed class Inner : Msg() {
        data class SetTopBarVisibility(val value: Boolean) : Inner()
        data class SetColoredTopBar(val value: Boolean) : Inner()
        data class SetBottomVisibility(val value: Boolean) : Inner()
    }
}

sealed class Cmd : ElmCommand {
    object ListenBottomPanelActions : Cmd()
}

sealed class Eff : ElmEffect {
    object NavigateToAddNewNote : Eff()
    object NavigateToSettings : Eff()
    object NavigateToTrash : Eff()
}