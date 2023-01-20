package ru.maksonic.beresta.feature.botom_panel.ui

/**
 * @Author maksonic on 20.01.2023
 */
data class PanelItem(val iconId: Int, val title: String = "", val action: () -> Unit)
