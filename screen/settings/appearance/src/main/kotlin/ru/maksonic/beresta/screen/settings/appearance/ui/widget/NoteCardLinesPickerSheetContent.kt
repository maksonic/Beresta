package ru.maksonic.beresta.screen.settings.appearance.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onBackground
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp32
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp64
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.notes_list.ui.api.card.LocalNoteCardState
import ru.maksonic.beresta.feature.notes_list.ui.api.card.NoteCardUiState
import ru.maksonic.beresta.feature.notes_list.ui.api.card.noteUiCardState
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.Send

/**
 * @Author maksonic on 08.07.2023
 */
@Composable
internal fun NoteCardLinesPickerSheetContent(
    send: Send,
    noteCardState: State<NoteCardUiState>,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(bottom = dp32)) {
        Text(
            text = text.settingsAppearance.itemNoteCardMaxLines,
            style = TextDesign.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = dp8)
        )

        CompositionLocalProvider(LocalNoteCardState provides noteCardState.value) {
            NoteLinesPicker(
                title = text.shared.title,
                currentValue = noteUiCardState.maxTitleLines,
                onClick = { send(Msg.Inner.UpdatedNoteTitleMaxLines(it)) }
            )

            NoteLinesPicker(
                title = text.shared.note,
                currentValue = noteUiCardState.maxMessageLines,
                onClick = { send(Msg.Inner.UpdatedNoteMessageMaxLines(it)) }
            )
        }
    }
}

@Composable
private fun NoteLinesPicker(
    title: String,
    currentValue: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val values = listOf(1, 2, 3, 4, 5)

    Column(modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = TextDesign.bodyLarge,
            modifier = modifier.padding(start = dp16)
        )

        Row(Modifier.fillMaxWidth()) {
            Spacer(modifier.weight(1f))

            values.forEach { value ->
                Item(value, isSelected = value == currentValue, onClick = { onClick(value) })
            }

            Spacer(modifier.weight(1f))
        }
    }
}

@Composable
private fun Item(
    value: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = animateColorAsState(
        if (isSelected) tertiaryContainer else secondaryContainer,
        tween(Theme.animVelocity.common), label = ""
    )
    val borderColor = animateColorAsState(
        if (isSelected) tertiaryContainer else onSecondaryContainer,
        tween(Theme.animVelocity.common), label = ""
    )

    val titleColor = animateColorAsState(
        if (isSelected) onTertiaryContainer else onBackground,
        tween(Theme.animVelocity.common), label = ""
    )

    Box(modifier.padding(dp4), contentAlignment = Alignment.Center) {
        Box(
            modifier
                .size(Theme.size.minimumTouchTargetSize)
                .aspectRatio(1f)
                .clip(CircleShape)
                .border(1.dp, borderColor.value, CircleShape)
                .drawBehind { drawRect(backgroundColor.value) }
                .rippledClick(primary) { onClick() }, contentAlignment = Alignment.Center
        ) {
            Text(value.toString(), style = TextDesign.titleMedium.copy(titleColor.value))
        }

        Box(
            modifier
                .size(dp64)
                .clip(CircleShape)
                .rippledClick(primary) { onClick() }
        )
    }
}