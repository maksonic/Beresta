package ru.maksonic.beresta.common.ui_kit.widget.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import ru.maksonic.beresta.common.core.ext.SINGLE_LINE_VALUE
import ru.maksonic.beresta.common.ui_kit.dialog.dropdown.DropdownMenuItem
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.PrimaryRipple
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 08.07.2023
 */
@Composable
fun SettingDropdownClickableItem(
    settingItem: SettingItem,
    dropdownMenuItems: List<DropdownMenuItem>,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier.fillMaxWidth()) {
        val shapeDropdownMenuState = rememberSaveable { mutableStateOf(false) }
        var dropdownWidth by remember { mutableStateOf(0.dp) }
        val dropdownOffset = rememberUpdatedState(this.maxWidth - dropdownWidth.plus(dp16))
        val density = LocalDensity.current

        SettingClickableItem(settingItem.copy(onClick = { shapeDropdownMenuState.value = true }))

        MaterialTheme(if (Theme.darkMode.value) darkColorScheme() else lightColorScheme()) {
            DropdownMenu(
                expanded = shapeDropdownMenuState.value,
                onDismissRequest = { shapeDropdownMenuState.value = false },
                offset = DpOffset(dropdownOffset.value, -(5).dp),
                properties = PopupProperties(true),
                modifier = modifier
                    .onGloballyPositioned { coordinates ->
                        dropdownWidth = with(density) { coordinates.size.width.toDp() }
                    }
                    .background(secondaryContainer)
            ) {
                CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {
                    dropdownMenuItems.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item.title,
                                    style = TextDesign.bodyMedium,
                                    maxLines = SINGLE_LINE_VALUE,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            onClick = {
                                item.onClick().run { shapeDropdownMenuState.value = false }
                            },
                        )
                    }
                }
            }
        }
    }
}