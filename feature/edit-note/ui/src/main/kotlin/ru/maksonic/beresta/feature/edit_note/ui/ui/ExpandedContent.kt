package ru.maksonic.beresta.feature.edit_note.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.core.ui.saver.ColorSaver
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.core.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.CategoryBar
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.ControlBars
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerColorPickerApi
import ru.maksonic.beresta.ui.theme.color.color_palette.Palette
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.surface

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun ExpandedContent(
    model: State<Model>,
    send: SendMessage,
    isHiddenNote: Boolean,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    addChipDialogApi: FoldersApi.AddChipDialog.Ui,
    chipsRowApi: FoldersApi.ChipsRow.Ui,
    markerColorPickerApi: MarkerColorPickerApi.Ui
) {

    LaunchedEffect(chipsRowApi.currentSelectedId) {
        send(Msg.Inner.FetchedCurrentFolderId(chipsRowApi.currentSelectedId.value))
    }

    Box(
        modifier
            .fillMaxSize()
            .background(surface)
            .imePadding()
    ) {
        val scrollState = rememberScrollState()
        val isScrollUp = remember { mutableStateOf(true) }
        val canScrollBackward = rememberUpdatedState(scrollState.canScrollBackward)
        val nestedScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                    val z = available.y
                    val zz = z + available.y
                    isScrollUp.value = zz >= available.y
                    return super.onPreScroll(available, source)
                }
            }
        }
        val defaultMarkerColor = outline
        val color = rememberSaveable(saver = ColorSaver) { mutableStateOf(defaultMarkerColor) }

        LaunchedEffect(model.value.editableNote.style.markerColorId) {
            updateMarkerColor(model, color, defaultMarkerColor)
        }

        Column(
            Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
                .verticalScroll(scrollState)
        ) {
            CategoryBar(isHiddenNote, model, send, markerColor = color.value)

            NoteTitleInputFieldWidget(model.value.editableNote.title, send, focusRequester)

            NoteMessageInputFieldWidget(model.value.editableNote.message, send)
        }

        ControlBars(
            model = model,
            send = send,
            isHiddenNote = isHiddenNote,
            isScrollUp = isScrollUp,
            canScrollBackward = canScrollBackward,
            isVisibleAddNoteDialog = addChipDialogApi.sharedState.value.isVisible,
        )

        markerColorPickerApi.Widget(
            onAcceptClicked = { colorId -> send(Msg.Inner.UpdatedCurrentNoteMarkerColor(colorId)) }
        )

        addChipDialogApi.Widget()
    }
}

private fun updateMarkerColor(model: State<Model>, color: MutableState<Color>, default: Color) {
    color.value = Palette.markerColors[model.value.editableNote.style.markerColorId] ?: default
}
