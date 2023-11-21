package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.graphicsLayer
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemStatusBar
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ArrowBack
import ru.maksonic.beresta.common.ui_kit.icons.Pin
import ru.maksonic.beresta.common.ui_kit.icons.PinFilled
import ru.maksonic.beresta.common.ui_kit.icons.Wallpaper
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.provide.dp0
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.feature.ui.edit_note.core.ColorByWallpaperCreator
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
    barColorCreatorState: State<ColorByWallpaperCreator>,
    modifier: Modifier = Modifier
) {
    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0
    val topBarColor = barColorCreatorState.value.topBarColor(animVelocity)
    val topBarOffset = Theme.size.topBarNormalHeight.plus(SystemStatusBarHeight)
    val topBarTransition = animateDpAsState(
        if (model.isReadOnlyMode) -topBarOffset else dp0, tween(Theme.animVelocity.common), ""
    )

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    Column {
        SystemStatusBar(backgroundColor = topBarColor)

        Row(
            modifier
                .fillMaxWidth()
                .height(Theme.size.topBarSmallHeight)
                .drawBehind { drawRect(topBarColor.value) }
                .graphicsLayer {
                    translationY = topBarTransition.value.toPx()
                },
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