package ru.maksonic.beresta.screen.main.presentation

import androidx.compose.ui.graphics.vector.ImageVector
import ru.maksonic.beresta.screen.main.presentation.core.Msg

/**
 * @Author maksonic on 22.02.2023
 */
data class MainBottomPanelItem(val title: String = "", val icon: ImageVector, val msg: Msg)