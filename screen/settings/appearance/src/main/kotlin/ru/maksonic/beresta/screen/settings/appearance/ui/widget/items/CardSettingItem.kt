package ru.maksonic.beresta.screen.settings.appearance.ui.widget.items

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.koin.compose.koinInject
import ru.maksonic.beresta.core.DateFormatter
import ru.maksonic.beresta.feature.notes.api.ui.LocalNoteCardState
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardShape
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.ui.isEnabled
import ru.maksonic.beresta.feature.notes.api.ui.isSquare
import ru.maksonic.beresta.feature.notes.api.ui.noteUiCardState
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.settings.appearance.core.Msg
import ru.maksonic.beresta.screen.settings.appearance.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.inverseSurface
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.surfaceVariant
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.CornerRadius
import ru.maksonic.beresta.ui.theme.icons.Grading
import ru.maksonic.beresta.ui.theme.icons.Shadow
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.button.settings.DropdownMenuItem
import ru.maksonic.beresta.ui.widget.button.settings.RightPart
import ru.maksonic.beresta.ui.widget.button.settings.SettingClickableItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingItem
import ru.maksonic.beresta.ui.widget.button.settings.SettingDropdownClickableItem
import ru.maksonic.beresta.ui.widget.functional.animation.animateDp
import ru.maksonic.beresta.ui.widget.surface.SettingContainer
import ru.maksonic.beresta.ui.widget.surface.SurfacePro
import ru.maksonic.beresta.ui.widget.text.SettingTitle
import java.time.LocalDateTime

/**
 * @Author maksonic on 07.07.2023
 */
@Composable
internal fun CardSettingItem(
    send: SendMessage,
    noteCardState: State<NoteCardUiState>,
    currentLang: AppLanguage,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalNoteCardState provides noteCardState.value) {

        SettingContainer {

            SettingTitle(title = text.settingsAppearance.titleNoteCard)

            Card(modifier, currentLang)

            SettingDropdownClickableItem(shapeSettingItem(), dropdownMenuItems(send))

            items(send).forEach { item ->
                SettingClickableItem(item)
            }
        }
    }
}

@Composable
private fun Card(
    modifier: Modifier,
    currentLang: AppLanguage,
    dateFormatter: DateFormatter = koinInject()
) {
    val corner = animateDp(with(noteUiCardState.shape) { if (isSquare) dp else dp })
    val elevation = animateDp(with(noteUiCardState.elevation) { if (isEnabled) dp else dp })

    BoxWithScaleInOutOnClick {
        SurfacePro(
            shadowElevation = elevation.value,
            shape = RoundedCornerShape(corner.value),
            modifier = modifier.padding(dp16)
        ) {
            Column(
                modifier
                    .fillMaxWidth()
                    .background(surfaceVariant)
                    .padding(dp16),
                verticalArrangement = Arrangement.spacedBy(dp8)
            ) {

                Text(
                    text = text.shared.noteTitlePlaceholder,
                    style = TextDesign.title.copy(color = onPrimaryContainer),
                    maxLines = noteUiCardState.maxTitleLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.animateContentSize(tween(Theme.animVelocity.common))
                )

                Text(
                    text = text.shared.noteMessagePlaceholder,
                    style = TextDesign.bodyPrimary.copy(color = onPrimaryContainer),
                    maxLines = noteUiCardState.maxMessageLines,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.animateContentSize(tween(Theme.animVelocity.common))
                )
                Text(
                    text = dateFormatter.fetchFormattedUiDate(LocalDateTime.now(), currentLang),
                    style = TextDesign.captionSmall.copy(color = inverseSurface),
                    maxLines = 1,
                    modifier = Modifier.padding(top = dp8)
                )
            }
        }
    }
}

@Composable
private fun shapeSettingItem(): SettingItem {
    val notesShapeHint = with(text.settingsAppearance) {
        if (noteUiCardState.shape.isSquare) hintNoteCardSquaredShape else hintNoteCardRoundedShape
    }

    return SettingItem(
        text.settingsAppearance.itemNoteCardShape,
        prefixIcon = AppIcon.CornerRadius,
        valueHint = notesShapeHint,
        rightPart = RightPart.CURRENT_VALUE,
    )
}

@Composable
private fun items(send: SendMessage): List<SettingItem> {
    val isEnabledElevation = rememberUpdatedState(noteUiCardState.elevation.isEnabled)

    return listOf(
        SettingItem(
            title = text.settingsAppearance.itemNoteCardElevation,
            prefixIcon = AppIcon.Shadow,
            rightPart = RightPart.TOGGLE,
            isEnabledToggle = noteUiCardState.elevation.isEnabled,
            onClick = { send(Msg.Ui.OnNoteCardElevationClicked(isEnabledElevation.value)) },
        ),
        SettingItem(
            title = text.settingsAppearance.itemNoteCardMaxLines,
            prefixIcon = AppIcon.Grading,
            rightPart = RightPart.NOTHING,
            onClick = { send(Msg.Ui.OnNoteLinesCountClicked) },
        )
    )
}

@Composable
private fun dropdownMenuItems(send: SendMessage) = listOf(
    DropdownMenuItem(
        title = text.settingsAppearance.hintNoteCardRoundedShape,
        onClick = { send(Msg.Ui.OnNoteCardShapeClicked(NoteCardShape.ROUNDED)) }
    ),
    DropdownMenuItem(
        title = text.settingsAppearance.hintNoteCardSquaredShape,
        onClick = { send(Msg.Ui.OnNoteCardShapeClicked(NoteCardShape.SQUARED)) }
    )
)