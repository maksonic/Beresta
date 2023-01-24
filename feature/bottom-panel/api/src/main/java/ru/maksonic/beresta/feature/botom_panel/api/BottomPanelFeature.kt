package ru.maksonic.beresta.feature.botom_panel.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * @Author maksonic on 24.12.2022
 */
interface BottomPanelFeature {
    @Composable
    fun Widget(modifier: Modifier)

    @Composable
    fun PanelWithSelectCounter(
        onSelectAction: () -> Unit,
        onCancelAction: () -> Unit,
        countValue: () -> Int,
        modifier: Modifier
    )

    val state: BottomPanelSharedState
}