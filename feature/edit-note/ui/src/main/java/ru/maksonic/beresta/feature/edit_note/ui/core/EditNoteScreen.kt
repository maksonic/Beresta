package ru.maksonic.beresta.feature.edit_note.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter

/**
 * @Author maksonic on 24.01.2023
 */
@Composable
fun EditNoteScreen(router: EditNoteRouter) {
    Content()
}


@Composable
private fun Content(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.Yellow))
}