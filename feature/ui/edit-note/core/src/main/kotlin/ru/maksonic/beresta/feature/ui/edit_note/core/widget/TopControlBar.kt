package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.maksonic.beresta.common.ui_kit.bar.system.SystemStatusBarHeight
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.button.ButtonTertiary
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icons.Add
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.label.LabelOutlined
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.primary
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.provide.NoRipple
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp4
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.marker_color_picker.ui.api.findColor
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.ui.edit_note.core.ColorByWallpaperCreator
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.platform.core.ui.ColorSaver

/**
 * @Author maksonic on 08.09.2023
 */
@Composable
internal fun TopControlBar(
    model: Model,
    send: Send,
    isHiddenNote: Boolean,
    colorCreator: State<ColorByWallpaperCreator>,
    modifier: Modifier = Modifier
) {
    val markerColorState = rememberUpdatedState(model.markerState.currentSelectedColorId)
    val initialMarkerColor = transparent
    val markerColor = rememberSaveable(saver = ColorSaver) { mutableStateOf(initialMarkerColor) }
    val animDelay = Theme.animVelocity.common
    var isAnimated by remember { mutableStateOf(false) }
    val animVelocity = if (isAnimated) animDelay else 0
    val backgroundColor = colorCreator.value.controlBarColor(animVelocity)

    LaunchedEffect(Unit) {
        if (!isAnimated) {
            delay(animDelay.toLong())
            isAnimated = true
        }
    }

    LaunchedEffect(markerColorState.value) {
        updateMarkerColor(model, markerColor, initialMarkerColor, markerColorState.value)
    }

    LaunchedEffect(model.currentWallpaper) {
        updateMarkerColor(model, markerColor, initialMarkerColor, markerColorState.value)
    }

    Column(Modifier.drawBehind { drawRect(backgroundColor.value) }) {
        var isExpanded by remember { mutableStateOf(false) }
        Row(
            modifier
                .fillMaxWidth()
                .padding(
                    top = Theme.size.topBarNormalHeight
                        .plus(SystemStatusBarHeight)
                        .plus(dp8),
                    bottom = dp8
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier
                    .padding(start = dp4)
                    .clip(CircleShape)
                    .rippledClick(rippleColor = primary) { send(Msg.Ui.OnSelectColorMarkerClicked) }
                    .size(Theme.size.minimumTouchTargetSize)
                    .padding(14.dp)
                    .border(2.dp, editorColors.tint.value, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier
                        .fillMaxSize()
                        .padding(dp4)
                        .clip(CircleShape)
                        .background(markerColor.value)
                )
            }
            ButtonIcon(
                icon = AppIcon.LabelOutlined,
                tint = editorColors.tint.value,
                onClick = { isExpanded = !isExpanded },
            )

            Spacer(modifier.weight(1f))

            if (!isHiddenNote) {
                DropdownFolderPicker(model, send)
            }
        }
        ExpandableTagsContainer(
            tags = model.editableNote.tags,
            colorCreator = colorCreator,
            isExpanded = isExpanded,
            onAddNoteTagClicked = { send(Msg.Inner.UpdatedTagPickerSheetState(true)) }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ExpandableTagsContainer(
    tags: NoteTagUi.Collection,
    colorCreator: State<ColorByWallpaperCreator>,
    isExpanded: Boolean,
    onAddNoteTagClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val height = if (isExpanded) Modifier.wrapContentHeight() else Modifier.height(0.dp)

    Box(
        modifier
            .animateContentSize()
            .fillMaxWidth()
            .then(height)
    ) {
        if (tags.data.isEmpty()) {
            ButtonTertiary(
                onClick = onAddNoteTagClicked,
                title = text.tags.btnTitleAddTags,
                icon = AppIcon.Add,
                modifier = Modifier.padding(start = dp16)
            )
        } else {
            FlowRow(
                modifier = modifier.padding(start = dp8, end = dp8),
                horizontalArrangement = Arrangement.spacedBy(dp8),
                verticalArrangement = Arrangement.Center
            ) {
                CompositionLocalProvider(LocalRippleTheme provides NoRipple) {
                    val backgroundColor = colorCreator.value.tagChipBarColor()

                    ButtonAddTagCircle(
                        icon = AppIcon.Add,
                        onClick = onAddNoteTagClicked,
                        backgroundColor = backgroundColor,
                        modifier = Modifier.padding(top = dp8, start = dp4)
                    )

                    tags.data.forEach {
                        FilterChip(
                            selected = true,
                            enabled = false,
                            onClick = {},
                            label = { Text(it.title) },
                            colors = FilterChipDefaults.filterChipColors(
                                disabledSelectedContainerColor = backgroundColor.value,
                                disabledLabelColor = editorColors.textTint.value
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ButtonAddTagCircle(
    icon: ImageVector,
    backgroundColor: State<Color>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier
            .size(Theme.size.chipHeight)
            .clip(CircleShape)
            .drawBehind { drawRect(backgroundColor.value) }
            .rippledClick(primary, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, tint = editorColors.tint.value, contentDescription = "")
    }
}

private fun updateMarkerColor(
    model: Model,
    mutableColor: MutableState<Color>,
    initialMarkerColor: Color,
    currentMarkerId: Long
) {
    val colorContainer = model.markerState.findColor(currentMarkerId)

    if (colorContainer == null || colorContainer.colorId == 0L) {
        mutableColor.value = initialMarkerColor
    } else {
        mutableColor.value = colorContainer.value
    }
}

