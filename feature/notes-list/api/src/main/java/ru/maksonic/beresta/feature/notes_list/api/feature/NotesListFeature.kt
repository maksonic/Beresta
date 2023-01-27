package ru.maksonic.beresta.feature.notes_list.api.feature

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow
import ru.maksonic.beresta.navigation.router.router.MainScreenRouter

/**
 * @Author maksonic on 22.12.2022
 */
interface NotesListFeature {

    @Composable
    fun Screen(router: MainScreenRouter)

    val state: StateFlow<NotesSharedState>
}