package ru.maksonic.beresta.feature.edit_note.ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.color.onTertiaryContainer
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.DriveFile
import ru.maksonic.beresta.ui.theme.icons.Edit
import ru.maksonic.beresta.ui.widget.functional.rippledClick

/**
 * @Author maksonic on 26.04.2023
 */
@Composable
internal fun CollapsedContent(
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