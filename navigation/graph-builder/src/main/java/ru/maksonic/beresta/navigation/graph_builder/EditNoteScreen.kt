package ru.maksonic.beresta.navigation.graph_builder

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import ru.maksonic.beresta.feature.edit_note.ui.core.EditNoteScreen
import ru.maksonic.beresta.navigation.router.navigator.AppNavigator
import ru.maksonic.beresta.navigation.router.Destination

/**
 * @Author maksonic on 26.01.2023
 */
@OptIn(ExperimentalAnimationApi::class)
internal fun NavGraphBuilder.editNoteScreen(navigator: AppNavigator) {
    composable(
        route = Destination.EditNote.route.plus("/{noteId}"),
        arguments = listOf(navArgument("noteId") { type = NavType.LongType })) { navBackStackEntry ->
        val router = navigator.editNoteRouter(navBackStackEntry)
        EditNoteScreen(router = router)
    }
}