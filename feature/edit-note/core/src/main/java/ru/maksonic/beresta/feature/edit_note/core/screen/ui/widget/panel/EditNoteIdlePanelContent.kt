package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget.panel

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.feature.edit_note.core.screen.PanelItem
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.feature.edit_note.core.screen.ui.SendMessage
import ru.maksonic.beresta.ui.theme.color.onSurface
import ru.maksonic.beresta.ui.theme.icons.*
import ru.maksonic.beresta.ui.widget.button.IconAction
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 13.03.2023
 */
private val idlePanelItems = arrayOf(
    PanelItem(icon = AppIcon.VoiceEnter, msg = Msg.Ui.OnTopBarBackPressed),
    PanelItem(icon = AppIcon.AddImage, msg = Msg.Ui.OnTopBarBackPressed),
    PanelItem(icon = AppIcon.MakePhoto, msg = Msg.Ui.OnTopBarBackPressed),
    PanelItem(icon = AppIcon.Wallpaper, msg = Msg.Ui.OnChangeNoteWallpaperClicked),
)

@Composable
internal fun EditNoteIdlePanelContent(
    send: SendMessage,
    panelHeight: Dp,
    navBarHeight: Dp,
    modifier: Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(panelHeight)
            .noRippleClickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        idlePanelItems.forEach { panelItem ->
            IconAction(
                icon = { panelItem.icon },
                action = { send(panelItem.msg) },
                tint = onSurface
            )
        }
    }
    Spacer(modifier.height(navBarHeight))
}