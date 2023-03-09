package ru.maksonic.beresta.feature.edit_note.core.screen.ui.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar

/**
 * @Author maksonic on 09.03.2023
 */
@Composable
internal fun SystemBarsOverFlowContainer(isVisibleEditorPanel: Boolean, modifier: Modifier) {
    val navBarColor =
        animateColorAsState(if (isVisibleEditorPanel) tertiaryContainer else background)

    Column(modifier.fillMaxSize()) {
        val statusBarColor = background
        SystemStatusBar(backgroundColor = { statusBarColor })
        Spacer(modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { navBarColor.value })
    }
}