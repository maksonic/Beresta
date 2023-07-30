package ru.maksonic.beresta.feature.notes.ui.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalView
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.system.VibrationPerformer
import ru.maksonic.beresta.feature.notes.api.ui.LocalNoteCardState
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.ui.noteUiCardState
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.ui.theme.color.outlineVariant
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.surface.SurfacePro

/**
 * @Author maksonic on 03.07.2023
 */
@Composable
fun Container(
    note: NoteUi,
    cardUiState: State<NoteCardUiState>,
    isSelected: Boolean,
    onNoteClicked: (Long) -> Unit,
    onNoteLongClicked: (Long) -> Unit,
    modifier: Modifier,
    vibrationPerformer: VibrationPerformer = koinInject()
) {
    val view = LocalView.current
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val isSelectedColors = if (isFocusedItem.value) tertiary else secondary
    val colors = if (isFocusedItem.value) outlineVariant else primaryContainer
    val backgroundColor =
        animateColorAsState(if (isSelected) isSelectedColors else colors)

    CompositionLocalProvider(LocalNoteCardState provides cardUiState.value) {

        BoxWithScaleInOutOnClick(
            onClick = { onNoteClicked(note.id) },
            onLongClick = {
                onNoteLongClicked(note.id).run {
                    vibrationPerformer.keyboardTapVibration(view)
                }
            },
            modifier = modifier.onFocusChanged { isFocusedItem.value = it.isFocused }
        ) {
            SurfacePro(
                shadowElevation = noteUiCardState.elevation.dp,
                shape = RoundedCornerShape(noteUiCardState.shape.dp),
                color = backgroundColor.value,
                modifier = modifier.padding(bottom = dp12, start = dp6, end = dp6)

            ) {
                Content(note, modifier)
            }
        }
    }
}