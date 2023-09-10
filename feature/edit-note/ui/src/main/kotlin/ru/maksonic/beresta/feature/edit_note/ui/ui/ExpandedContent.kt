package ru.maksonic.beresta.feature.edit_note.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.core.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.CategoryBar
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.ControlBars
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs.NoteMessageInputFieldWidget
import ru.maksonic.beresta.feature.edit_note.ui.ui.widget.inputs.NoteTitleInputFieldWidget
import ru.maksonic.beresta.feature.folders_chips.api.FoldersApi
import ru.maksonic.beresta.feature.marker_color_picker.api.MarkerColorPickerApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.surface

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun ExpandedContent(
    model: State<Model>,
    send: SendMessage,
    focusRequester: FocusRequester,
    isHiddenNote: Boolean,
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

        Column(
            Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
                .verticalScroll(scrollState)
        ) {
            if (isHiddenNote) {
                Box(Modifier.height(Theme.widgetSize.topBarSmallHeight))
            } else {
                CategoryBar(model, send)
            }

            NoteTitleInputFieldWidget(model.value.currentNote.title, send, focusRequester)
            NoteMessageInputFieldWidget(model.value.currentNote.message, send)
        }

        ControlBars(
            model = model,
            send = send,
            isHiddenNote = isHiddenNote,
            isScrollUp = isScrollUp,
            canScrollBackward = canScrollBackward,
            isVisibleAddNoteDialog = addChipDialogApi.sharedState.value.isVisible,
        )

        markerColorPickerApi.Widget()
    }
}
