package ru.maksonic.beresta.feature.notes.list.api

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @Author maksonic on 26.04.2023
 */
data class BaseBottomBarItem(
    val label: String = "",
    val icon: ImageVector,
    val action: () -> Unit
)