package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.feature.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.navigation.router.AbstractNavigator
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 04.03.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.editNoteScreen(api: EditNoteApi.Ui, navigator: AbstractNavigator) {
    composable(
        route = Destination.EditNote.routeWithListArgs,
        arguments = listOf(
            navArgument(Destination.EditNote.passedKeysList.first()) { type = NavType.BoolType },
            navArgument(Destination.EditNote.passedKeysList.last()) { type = NavType.LongType },
        )
    ) { navBackStackEntry ->
        val router = navigator.editNoteRouter(navBackStackEntry)
        api.ExpandableScreen(
            router = router,
            state = remember { mutableStateOf(EditNoteFabState.COLLAPSED) },
            updateFabState = {},
            isEntryPoint = true,
            isHiddenNotes = false,
            modifier = Modifier
        )
    }
}