package ru.maksonic.beresta.screen.main.ui.core

import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import ru.maksonic.beresta.elm.*
import ru.maksonic.beresta.feature.botom_panel.api.BottomPanelFeature
import ru.maksonic.beresta.feature.notes_list.api.NotesListFeature
import ru.maksonic.beresta.feature.tasks_list.api.TasksListFeature

/**
 * @Author maksonic on 16.01.2023
 */
object Screen {
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
            object OnSettingsClicked : Ui()
            object OnTrashClicked : Ui()
            object OnSearchClicked : Ui()
            object OnShareSelectedNotes : Ui()
        }

        sealed class Inner : Msg() {
            data class FetchNavEntry(val from: NavBackStackEntry?): Inner()
            data class SetTopBarVisibility(val value: Boolean) : Inner()
            data class SetColoredTopBar(val value: Boolean) : Inner()
            data class SetBottomVisibility(val value: Boolean) : Inner()
        }
    }

    sealed class Cmd : ElmCommand {
        object ListenBottomPanelActions : Cmd()
    }

    sealed class Eff : ElmEffect {
        object NavigateToSettings : Eff()
        object NavigateToTrash : Eff()
    }
}