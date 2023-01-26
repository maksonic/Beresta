package ru.maksonic.beresta.screen.main.ui.core

import androidx.compose.runtime.Stable
import androidx.navigation.NavBackStackEntry
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.feature.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature

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
    val notesListFeature: NotesListFeature,
    val tasksListFeature: TasksListFeature,
    val bottomPanelFeature: BottomPanelFeature,
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
    object NavigateNoEditNote : Eff()
    object NavigateToSettings : Eff()
    object NavigateToTrash : Eff()
}