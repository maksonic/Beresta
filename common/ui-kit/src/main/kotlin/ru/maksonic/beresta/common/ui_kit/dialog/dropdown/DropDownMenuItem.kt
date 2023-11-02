package ru.maksonic.beresta.common.ui_kit.dialog.dropdown

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.common.core.ext.SINGLE_LINE_VALUE
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign

/**
 * @Author maksonic on 08.09.2023
 */
data class DropdownMenuItem(
    val title: String,
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
    val enabled: Boolean = true,
    val onClick: () -> Unit
)

@Composable
fun DropdownMenuTitle(title: String) {
    Text(
        text = title,
        style = TextDesign.bodyMedium,
        maxLines = SINGLE_LINE_VALUE,
        overflow = TextOverflow.Ellipsis
    )
}