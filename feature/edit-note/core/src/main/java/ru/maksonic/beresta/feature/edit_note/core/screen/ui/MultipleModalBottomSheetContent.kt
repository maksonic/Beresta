package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.note_wallpaper_selector.api.NoteWallpaperSelectorApi

/**
 * @Author maksonic on 09.03.2023
 */
enum class ModalSheetContent {
    NOTHING, WALLPAPER_SELECTOR
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MultipleModalBottomSheetContent(
    send: SendMessage,
    currentSheetContent: ModalSheetContent,
    wallpaperSelector: NoteWallpaperSelectorApi,
    modalSheetState: () -> ModalBottomSheetState,
    isFullExpandedFab: () -> Boolean,
    modifier: Modifier
) {
    if (isFullExpandedFab()) {
        when (currentSheetContent) {
            ModalSheetContent.WALLPAPER_SELECTOR -> {
                wallpaperSelector.BottomSheetContent(
                    isVisibleSheet = modalSheetState().isVisible,
                    hideSheet = { send(Msg.Ui.OnHideModalSheetClicked) }
                )
            }

            else -> Box(modifier.defaultMinSize(minHeight = 1.dp))
        }
    } else {
        Box(modifier.defaultMinSize(minHeight = 1.dp))
    }
}