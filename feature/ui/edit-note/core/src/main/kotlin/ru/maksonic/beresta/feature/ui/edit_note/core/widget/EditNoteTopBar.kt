package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemStatusBar
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.icons.Pin
import ru.maksonic.beresta.common.ui_kit.icons.PinFilled
import ru.maksonic.beresta.common.ui_kit.icons.Wallpaper
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun EditNoteTopBar(
    model: Model,
    send: Send,
    backgroundColor: State<Color>,
    modifier: Modifier = Modifier
) {
    Column {
        SystemStatusBar(backgroundColor = backgroundColor)

        Row(
            modifier
                .fillMaxWidth()
                .height(Theme.size.topBarSmallHeight)
                .drawBehind { drawRect(backgroundColor.value) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ButtonIcon(
                icon = AppIcon.ArrowBack,
                onClick = { send(Msg.Ui.OnTopBarBackPressed) },
                tint = editorColors.tint.value,
                modifier = Modifier.padding(start = dp4)
            )

            Spacer(Modifier.weight(1f))


            Row(Modifier.padding(end = dp4)) {

                PinBtn(model.isPinNoteSelected) { send(Msg.Ui.OnPinClicked) }

                ButtonIcon(
                    icon = AppIcon.Wallpaper,
                    tint = editorColors.tint.value,
                    onClick = { send(Msg.Ui.UpdatedWallpaperPickerSheetVisibility(true)) })

                DropdownMoreMenu(model, send)
            }
        }
    }
}

@Composable
private fun PinBtn(isPinSelected: Boolean, onPinClicked: () -> Unit) {
    Crossfade(isPinSelected, label = "") { isPinned ->
        ButtonIcon(
            if (isPinned) AppIcon.PinFilled else AppIcon.Pin,
            tint = editorColors.tint.value,
            onClick = onPinClicked
        )
    }
}