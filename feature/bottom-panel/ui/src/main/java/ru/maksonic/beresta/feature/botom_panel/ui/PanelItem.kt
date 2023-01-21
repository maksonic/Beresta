package ru.maksonic.beresta.feature.botom_panel.ui

/**
 * @Author maksonic on 20.01.2023
 */
data class PanelItem(val iconId: Int, val titleId: Int? = null, val action: () -> Unit)
