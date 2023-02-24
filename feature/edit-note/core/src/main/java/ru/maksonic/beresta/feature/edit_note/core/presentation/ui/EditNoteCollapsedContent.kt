package ru.maksonic.beresta.feature.edit_note.core.presentation.ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import ru.maksonic.beresta.ui.theme.color.onPrimary
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Edit

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
fun EditNoteCollapsedContent(modifier: Modifier) {
    Icon(
        imageVector = AppIcon.Edit,
        tint = onPrimary,
        contentDescription = "",
        modifier = modifier
    )
}