package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteFabState
import ru.maksonic.beresta.feature.ui.edit_note.api.EditNoteUiApi
import ru.maksonic.beresta.navigation.router.core.AbstractNavigator
import ru.maksonic.beresta.navigation.router.core.Destination

/**
 * @Author maksonic on 04.03.2023
 */
internal fun NavGraphBuilder.editNoteScreen(api: EditNoteUiApi, navigator: AbstractNavigator) {
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
            state = EditNoteFabState.COLLAPSED,
            updateFabState = {},
            isEntryPoint = true,
            isHiddenNote = false,
            modifier = Modifier
        )
    }
}