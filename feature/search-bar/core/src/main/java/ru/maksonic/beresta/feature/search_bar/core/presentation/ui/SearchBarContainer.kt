package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun SearchBarContainer(modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
        val systemBarsColor = background
        SystemStatusBar(backgroundColor = { systemBarsColor })
        Spacer(modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { systemBarsColor })
    }
}