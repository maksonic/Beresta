package ru.maksonic.beresta.navigation.graph_builder.destination

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.edit_note.api.EditNoteApi
import ru.maksonic.beresta.navigation.router.Destination
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator

/**
 * @Author maksonic on 04.03.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.editNoteScreen(api: EditNoteApi.Ui, navigator: AppNavigator) {
    composable(
        route = Destination.EditNote.routeWithArg,
        arguments = listOf(navArgument(Destination.EditNote.passedKey) { type = NavType.LongType })
    ) { navBackStackEntry ->
        val router = navigator.editNoteRouter(navBackStackEntry)
        api.ExpandableScreen(router, isEntryPoint = true, Modifier)
    }
}