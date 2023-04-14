package ru.maksonic.beresta.feature.selected_items_counter_panel.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 05.04.2023
 */
interface SelectedItemsPanelUiApi {
    @Composable
    fun Widget(countValue: () -> Int, onSelectAction: () -> Unit, onCancelAction: () -> Unit)
}