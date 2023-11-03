package ru.maksonic.beresta.feature.ui.edit_note.core.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import ru.maksonic.beresta.common.ui_kit.button.ButtonIcon
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.MoreVertical
import ru.maksonic.beresta.common.ui_theme.Theme
import ru.maksonic.beresta.common.ui_theme.colors.secondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.PrimaryRipple
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.feature.ui.edit_note.core.Model
import ru.maksonic.beresta.feature.ui.edit_note.core.editorColors
import ru.maksonic.beresta.feature.ui.edit_note.core.screen.Send

/**
 * @Author maksonic on 09.09.2023
 */
@Composable
internal fun DropdownMoreMenu(
    model: Model,
    send: Send,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {
        Box(
            modifier = modifier.wrapContentSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            val dropdownState = rememberSaveable { mutableStateOf(false) }

            ButtonIcon(
                icon = AppIcon.MoreVertical,
                tint = editorColors.tint.value,
                onClick = { dropdownState.value = true })

            MaterialTheme(if (Theme.darkMode.value) darkColorScheme() else lightColorScheme()) {
                DropdownMenu(
                    expanded = dropdownState.value,
                    onDismissRequest = { dropdownState.value = false },
                    offset = DpOffset(x = -(dp16), y = 0.dp),
                    scrollState = rememberScrollState(),
                    properties = PopupProperties(true),
                    modifier = Modifier.background(secondaryContainer)
                ) {
                    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {
                        menuItemsList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = { dropdownState.value = false }
                            )
                        }
                    }
                }
            }
        }
    }
}

private val menuItemsList = listOf("Title 1", "Title 2", "Title 3")