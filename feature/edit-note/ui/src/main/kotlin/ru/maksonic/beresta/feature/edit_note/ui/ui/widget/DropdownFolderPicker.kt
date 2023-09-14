package ru.maksonic.beresta.feature.edit_note.ui.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import ru.maksonic.beresta.core.SINGLE_LINE_VALUE
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.core.Msg
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.onBackground
import ru.maksonic.beresta.ui.theme.color.onSecondaryContainer
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.outline
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.color.transparent
import ru.maksonic.beresta.ui.theme.component.PrimaryRipple
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.Add
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ExpandMore
import ru.maksonic.beresta.ui.widget.text.DropdownMenuTitle

/**
 * @Author maksonic on 08.09.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DropdownFolderPicker(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {
        BoxWithConstraints(
            modifier = modifier.fillMaxWidth(0.6f),
            contentAlignment = Alignment.TopEnd
        ) {
            val chipLabel = model.value.selectedFolder?.title ?: text.folders.titlePinnedEndFolder
            val dropdownState = rememberSaveable { mutableStateOf(false) }
            var dropdownWidth by remember { mutableStateOf(0.dp) }
            var dropdownMinHeight by remember { mutableStateOf(0.dp) }
            val dropdownOffset = rememberUpdatedState(this.maxWidth - dropdownWidth.plus(dp16))
            val density = LocalDensity.current
            val iconRotation = animateFloatAsState(
                if (dropdownState.value) 180f else 0f, tween(Theme.animVelocity.common), label = ""
            )
            val containerColor =
                animateColorAsState(if (dropdownState.value) secondaryContainer else transparent,
                    label = ""
                )
            FilterChip(
                selected = false,
                onClick = { send(Msg.Ui.OnChipFolderClicked).run { dropdownState.value = true } },
                label = {
                    Text(
                        text = chipLabel,
                        maxLines = SINGLE_LINE_VALUE,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = AppIcon.ExpandMore,
                        contentDescription = "",
                        tint = onBackground,
                        modifier = Modifier.rotate(iconRotation.value)
                    )
                },
                shape = Shape.cornerNormal,
                border = FilterChipDefaults.filterChipBorder(borderColor = outline),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = containerColor.value,
                    selectedContainerColor = tertiaryContainer,
                    labelColor = onBackground,
                    selectedLabelColor = onTertiaryContainer
                ),
                modifier = Modifier.padding(end = dp16)
            )

            MaterialTheme(if (Theme.darkMode.value) darkColorScheme() else lightColorScheme()) {
                DropdownMenu(
                    expanded = dropdownState.value,
                    onDismissRequest = { dropdownState.value = false },
                    offset = DpOffset(x = dropdownOffset.value, y = 0.dp),
                    scrollState = rememberScrollState(),
                    properties = PopupProperties(true),
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            dropdownWidth = with(density) { coordinates.size.width.toDp() }
                            dropdownMinHeight = with(density) { coordinates.size.height.toDp() }
                        }
                        .heightIn(min = dropdownMinHeight, max = 350.dp)
                        .background(secondaryContainer)
                ) {
                    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text.folders.btnTitleCreateNewFolder,
                                    style = TextDesign.bodyPrimary
                                )
                            },
                            onClick = {
                                send(Msg.Ui.OnAddNewChipClicked).run {
                                    dropdownState.value = false
                                }
                            },
                            leadingIcon = { Icon(AppIcon.Add, "", tint = onBackground) })

                        HorizontalDivider(
                            Modifier.padding(start = dp8, end = dp8),
                            color = onSecondaryContainer
                        )

                        model.value.folders.data.forEach { item ->
                            DropdownMenuItem(
                                text = { DropdownMenuTitle(item.title) },
                                onClick = {
                                    send(Msg.Ui.OnSelectNoteFolderClicked(item)).run {
                                        dropdownState.value = false
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}