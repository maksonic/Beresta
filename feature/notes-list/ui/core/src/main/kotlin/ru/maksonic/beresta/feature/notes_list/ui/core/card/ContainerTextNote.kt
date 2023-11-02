package ru.maksonic.beresta.feature.notes_list.ui.core.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onTertiary
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.notes_list.ui.api.card.isSquare
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState

/**
 * @Author maksonic on 31.10.2023
 */
@Composable
internal fun ContainerTextNote(modifier: Modifier = Modifier, text: @Composable () -> Unit) {
    val shape = with(Theme.shape) {
        if (noteUiCardState.shape.isSquare) cornerSmall else cornerNormal
    }

    Box(
        modifier
            .padding(start = dp8, end = dp8)
            .clip(shape)
            .background(onTertiary.copy(0.35f)),
        contentAlignment = Alignment.CenterStart
    ) {
        text()
    }
}