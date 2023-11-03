package ru.maksonic.beresta.feature.ui.edit_note.core.widget

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import ru.maksonic.beresta.common.core.ext.SINGLE_LINE_VALUE
import ru.maksonic.beresta.common.ui_kit.dialog.dropdown.DropdownMenuTitle
import ru.maksonic.beresta.common.ui_kit.icon.IconBase
import ru.maksonic.beresta.common.ui_kit.icons.Add
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.ExpandMore
import ru.maksonic.beresta.common.ui_kit.text.TextBodyLarge
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.transparent
import ru.maksonic.beresta.common.ui_theme.provide.AppRipple
import ru.maksonic.beresta.common.ui_theme.provide.PrimaryRipple
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.Msg
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 08.09.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DropdownFolderPicker(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {
        BoxWithConstraints(
            modifier = modifier.fillMaxWidth(0.6f),
            contentAlignment = Alignment.TopEnd
        ) {
            val chipLabel = model.selectedFolder?.title ?: text.folders.titlePinnedEndFolder
            val dropdownState = rememberSaveable { mutableStateOf(false) }
            var dropdownWidth by remember { mutableStateOf(0.dp) }
            var dropdownMinHeight by remember { mutableStateOf(0.dp) }
            val dropdownOffset = rememberUpdatedState(this.maxWidth - dropdownWidth.plus(dp16))
            val density = LocalDensity.current
            val iconRotation = animateFloatAsState(
                if (dropdownState.value) 180f else 0f, tween(Theme.animVelocity.common), label = ""
            )

            FilterChip(
                selected = false,
                onClick = { send(Msg.Ui.OnChipFolderClicked).run { dropdownState.value = true } },
                label = {
                    Text(
                        text = chipLabel,
                        maxLines = SINGLE_LINE_VALUE,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                trailingIcon = {
                    IconBase(
                        icon = AppIcon.ExpandMore,
                        tint = editorColors.tint.value,
                        modifier = Modifier.rotate(iconRotation.value)
                    )
                },
                shape = Theme.shape.cornerNormal,
                border = FilterChipDefaults.filterChipBorder(editorColors.tint.value.copy(0.7f)),
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = transparent,
                    selectedContainerColor = tertiaryContainer,
                    labelColor = editorColors.tint.value,
                    iconColor = editorColors.tint.value,
                    selectedLabelColor = onTertiaryContainer
                ),
                modifier = Modifier.padding(end = dp16)
            )

            MaterialTheme(if (Theme.darkMode.value) darkColorScheme() else lightColorScheme()) {
                CompositionLocalProvider(LocalRippleTheme provides AppRipple) {

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
                        DropdownMenuItem(
                            text = { TextBodyLarge(text.folders.btnTitleCreateNewFolder) },
                            onClick = {
                                send(Msg.Ui.OnAddNewFolderClicked).run {
                                    dropdownState.value = false
                                }
                            },
                            leadingIcon = { IconBase(AppIcon.Add) })

                        HorizontalDivider(
                            Modifier.padding(start = dp8, end = dp8),
                            color = onSecondaryContainer
                        )
                        model.folders.data.forEach { item ->
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