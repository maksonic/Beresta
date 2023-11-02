package ru.maksonic.beresta.feature.ui.edit_note.core.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.helpers.modifier.rippledClick
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.DriveFile
import ru.maksonic.beresta.common.ui_kit.icons.Edit
import ru.maksonic.beresta.common.ui_theme.colors.onTertiaryContainer
import ru.maksonic.beresta.common.ui_theme.colors.tertiaryContainer

/**
 * @Author maksonic on 16.10.2023
 */
@Composable
internal fun ContentCollapsed(
    isBlankNote: Boolean,
    onExpandFabClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(tertiaryContainer)
            .rippledClick(rippleColor = onTertiaryContainer) { onExpandFabClicked() },
        contentAlignment = Alignment.Center
    ) {
        val icon = if (isBlankNote) AppIcon.Edit else AppIcon.DriveFile

        Icon(
            imageVector = icon,
            tint = onTertiaryContainer,
            contentDescription = "",
            modifier = modifier
        )
    }
}