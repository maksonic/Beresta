package ru.maksonic.beresta.ui.widget.bar.bottom

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @Author maksonic on 22.06.2023
 */
data class BaseBottomBarItem(
    val icon: ImageVector,
    val action: () -> Unit,
    val label: String = "",
    val isEmpty: Boolean = false,
)