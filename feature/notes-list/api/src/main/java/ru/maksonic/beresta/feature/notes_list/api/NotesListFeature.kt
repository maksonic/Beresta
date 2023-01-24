package ru.maksonic.beresta.feature.notes_list.api

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow

/**
 * @Author maksonic on 22.12.2022
 */
interface NotesListFeature {

    @Composable
    fun Screen()

    val state: StateFlow<NotesSharedState>
}