package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.SendMessage
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi

/**
 * @Author maksonic on 09.03.2023
 */
enum class SheetContent {
    NOTHING, WALLPAPER_SELECTOR
}

@Composable
internal fun MultipleBottomSheetContent(
    send: SendMessage,
    sheetState: BottomSheetEditorState,
    currentSheetContent: SheetContent,
    wallpaperSelector: NoteWallpaperSelectorApi,
    modifier: Modifier
) {

    BackHandler(sheetState == BottomSheetEditorState.EXPANDED) {
        if (sheetState == BottomSheetEditorState.EXPANDED) {
            send(Msg.Ui.OnHideModalSheetClicked)
        }
    }

    when (currentSheetContent) {
        SheetContent.WALLPAPER_SELECTOR -> {
            wallpaperSelector.BottomSheetContent(
                hideSheet = { send(Msg.Ui.OnHideModalSheetClicked) }
            )
        }
        SheetContent.NOTHING -> Box(modifier.defaultMinSize(minHeight = 1.dp))
    }
}
