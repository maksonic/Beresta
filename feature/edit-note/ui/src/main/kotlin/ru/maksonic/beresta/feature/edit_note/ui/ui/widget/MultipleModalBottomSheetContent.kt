package ru.maksonic.beresta.feature.edit_note.ui.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject
import ru.maksonic.beresta.feature.edit_note.ui.core.CurrentSheetContent
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.core.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.feature.wallpaper_picker.api.WallpaperPickerApi
import ru.maksonic.beresta.ui.theme.color.secondaryContainer

/**
 * @Author maksonic on 15.09.2023
 */
@Composable
internal fun MultipleModalBottomSheetContent(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier,
    wallpaperPickerApi: WallpaperPickerApi.Ui = koinInject()
) {

    Box(modifier.background(secondaryContainer)) {
        when (model.value.modalSheet.content) {
            CurrentSheetContent.WALLPAPER_PICKER -> {
                wallpaperPickerApi.SheetContent(
                    hideSheet = { /*send(Msg.Ui.OnHideModalBottomSheet)*/ }
                )
            }

            CurrentSheetContent.NOTHING -> Box(modifier.size(1.dp))
        }
    }
}