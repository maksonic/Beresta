package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * @Author maksonic on 23.02.2023
 */
@Composable
internal fun EditNoteOverflowContainer(
    send: SendMessage,
    modifier: Modifier = Modifier,
    content: @Composable (boxScope: BoxWithConstraintsScope) -> Unit,
) {
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {

        BoxWithConstraints(
            modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            val boxScope = this
            content(boxScope)
        }
    }
}