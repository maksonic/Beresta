package ru.maksonic.beresta.screen.settings.appearance.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.feature.notes.api.LocalNoteCardState
import ru.maksonic.beresta.feature.notes.api.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.noteUiCardState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.SendMessage
import ru.maksonic.beresta.ui.theme.color.DefaultSliderColors
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp32
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.ModalSheetBottomButtonsRow
import kotlin.math.roundToInt

/**
 * @Author maksonic on 08.07.2023
 */
private const val MIN_LINES_COUNT = 1F
private const val MAX_LINES_COUNT = 5F
private const val SLIDER_STEP = 3

@Composable
internal fun NoteCardLinesPickerSheetContent(
    send: SendMessage,
    noteCardState: State<NoteCardUiState>
) {
    Column {
        CompositionLocalProvider(LocalNoteCardState provides noteCardState.value) {

            val titleSliderPosition = rememberUpdatedState(noteUiCardState.maxTitleLines.toFloat())
            val messageSliderPosition =
                rememberUpdatedState(noteUiCardState.maxMessageLines.toFloat())


            BaseLinesPicker(title = text.shared.title, titleSliderPosition) {
                send(Msg.Inner.UpdatedNoteTitleMaxLines(it.roundToInt()))
            }

            BaseLinesPicker(title = text.shared.note, messageSliderPosition) {
                send(Msg.Inner.UpdatedNoteMessageMaxLines(it.roundToInt()))
            }

            ModalSheetBottomButtonsRow(
                onLeftClicked = { send(Msg.Ui.OnModalSheetLinesPickerDefaultClicked) },
                onRightClicked = { send(Msg.Ui.OnModalSheetLinesPickerSaveClicked) }
            )
        }
    }
}

@Composable
private fun BaseLinesPicker(
    title: String,
    position: State<Float>,
    modifier: Modifier = Modifier,
    updatePosition: (Float) -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp32, end = dp32)
    ) {
        Text(title, style = TextDesign.title)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dp8)
        ) {
            Slider(
                value = position.value,
                onValueChange = { updatePosition(it) },
                valueRange = MIN_LINES_COUNT..MAX_LINES_COUNT,
                steps = SLIDER_STEP,
                colors = DefaultSliderColors,
                modifier = modifier.weight(1f)
            )
            Text(position.value.roundToInt().toString(), style = TextDesign.bodyPrimary)
        }
    }
}