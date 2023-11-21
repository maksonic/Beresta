package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.animation.AnimateFadeInOut
import ru.maksonic.beresta.feature.notes_list.ui.api.isBlank
import ru.maksonic.beresta.feature.ui.edit_note.core.ColorByWallpaperCreator
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send

/**
 * @Author maksonic on 08.09.2023
 */
@Composable
internal fun TopWithBottomBars(
    model: Model,
    send: Send,
    colorCreator: State<ColorByWallpaperCreator>,
    isVisibleBars: State<Boolean>,
    isHiddenNote: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.End,
    ) {
        EditNoteTopBar(model, send, colorCreator)

        Spacer(modifier.weight(1f))

        AnimateFadeInOut(!model.isVisibleAddFolderDialog) {
            EditorBottomBar(
                send = send,
                barColorCreatorState = colorCreator,
                isScrollUp = isVisibleBars,
                isBlankNote = model.editableNote.isBlank(),
                isHiddenNote = isHiddenNote,
            )
        }
    }
}