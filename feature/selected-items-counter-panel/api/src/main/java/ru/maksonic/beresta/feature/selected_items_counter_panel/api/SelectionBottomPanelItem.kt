package ru.maksonic.beresta.feature.selected_items_counter_panel.api

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @Author maksonic on 22.02.2023
 */
data class SelectionBottomPanelItem(
    val label: String = "",
    val icon: ImageVector,
    val action: () -> Unit
)