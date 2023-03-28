package ru.maksonic.beresta.screen.main.presentation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @Author maksonic on 22.02.2023
 */
data class MainBottomPanelItem(
    val label: String = "",
    val icon: ImageVector,
    val action: () -> Unit
)