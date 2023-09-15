package ru.maksonic.beresta.feature.edit_note.ui.ui.widget

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.core.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.feature.notes.api.ui.NoteUi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.surface
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.theme.icons.PinFilled
import ru.maksonic.beresta.ui.theme.icons.Wallpaper
import ru.maksonic.beresta.ui.widget.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.ui.widget.button.ClickableCircleIcon
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp

/**
 * @Author maksonic on 27.04.2023
 */
@Composable
internal fun EditNoteTopBar(
    model: State<Model>,
    send: SendMessage,
    isScrollUp: State<Boolean>,
    canScrollBackward: State<Boolean>,
    modifier: Modifier = Modifier
) {
    val panelHeight = Theme.widgetSize.topBarSmallHeight
    val offsetValue = -panelHeight.plus(SystemStatusBarHeight)
    val panelOffset = animateDp(if (isScrollUp.value) 0.dp else offsetValue)
    val color = animateColorAsState(
        if (canScrollBackward.value) surfaceVariant else surface, tween(Theme.animVelocity.common),
        label = ""
    )

    Row(
        modifier
            .fillMaxWidth()
            .height(panelHeight)
            .padding(start = dp4, end = dp4)
            .graphicsLayer {
                translationY = panelOffset.value.toPx()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ClickableCircleIcon(
            icon = AppIcon.ArrowBack,
            action = { send(Msg.Ui.OnTopBarBackPressed) },
            colorState = color
        )

        Spacer(modifier.weight(1f))


        Row(
            modifier
                .clip(CircleShape)
                .drawBehind { drawRect(color.value) }) {

            PinBtn(model.value.editableNote) { send(Msg.Ui.OnPinClicked) }

            ClickableIcon(icon = AppIcon.Wallpaper, onClick = {})

            DropdownMoreMenu(model, send)
        }
    }
}

@Composable
private fun PinBtn(note: NoteUi, onPinClicked: () -> Unit) {
    Crossfade(note.isPinned, label = "") { isPinned ->
        ClickableIcon(
            icon = if (isPinned) AppIcon.PinFilled else AppIcon.Pin,
            onClick = onPinClicked
        )
    }
}