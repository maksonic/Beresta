package ru.maksonic.beresta.feature.notes_list.ui.widget

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.component.dp8

/**
 * @Author maksonic on 19.01.2023
 */
@Composable
internal fun SelectedNotesCounter(
    countNotes: () -> Int,
    isSelectionState: () -> Boolean,
    modifier: Modifier = Modifier
) {

    AnimatedVisibility(
        visible = isSelectionState(),
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {

        Surface(
            modifier.padding(top = Theme.widgetSize.topBarNormalHeight.plus(dp6)),
            shape = Theme.shape.cornerNormal,
        ) {
            Box(modifier.background(tertiaryContainer)) {
                Text(
                    text = "Выбрано заметок: ${countNotes()}",
                    style = TextDesign.caption,
                    modifier = modifier
                        .padding(start = dp8, end = dp8, top = dp4, bottom = dp4),
                )
            }
        }
    }
}