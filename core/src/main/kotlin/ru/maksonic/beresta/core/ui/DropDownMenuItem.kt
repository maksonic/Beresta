package ru.maksonic.beresta.core.ui

import androidx.compose.ui.graphics.vector.ImageVector

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
