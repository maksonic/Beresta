package ru.maksonic.beresta.feature.edit_note.ui.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import ru.maksonic.beresta.core.ui.DropdownMenuItem
import ru.maksonic.beresta.feature.edit_note.ui.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.ui.SendMessage
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.secondaryContainer
import ru.maksonic.beresta.ui.theme.component.PrimaryRipple
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.MoreVertical
import ru.maksonic.beresta.ui.widget.button.ClickableIcon
import ru.maksonic.beresta.ui.widget.text.DropdownMenuTitle

/**
 * @Author maksonic on 09.09.2023
 */
@Composable
internal fun DropdownMoreMenu(
    model: State<Model>,
    send: SendMessage,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalRippleTheme provides PrimaryRipple) {
        Box(
            modifier = modifier.wrapContentSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            val dropdownState = rememberSaveable { mutableStateOf(false) }

            ClickableIcon(icon = AppIcon.MoreVertical, onClick = { dropdownState.value = true })

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
                        menuItemsList(send).forEach { item ->
                            DropdownMenuItem(
                                text = { DropdownMenuTitle(item.title) },
                                onClick = { dropdownState.value = false }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun menuItemsList(send: SendMessage) = listOf(
    DropdownMenuItem(
        title = "Title 1",
        onClick = {}
    ),
    DropdownMenuItem(
        title = "Title 2",
        onClick = {}
    ),
    DropdownMenuItem(
        title = "Title 3",
        onClick = {}
    ),

    )