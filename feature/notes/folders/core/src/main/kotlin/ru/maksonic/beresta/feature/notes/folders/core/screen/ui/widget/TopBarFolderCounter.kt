package ru.maksonic.beresta.feature.notes.folders.core.screen.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.SurfacePro
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.SystemStatusBarHeight

/**
 * @Author maksonic on 16.05.2023
 */
@Composable
internal fun TopBarFolderCounter(
    counterBar: TopBarCounterApi.Ui,
    topBarTonal: State<Dp>,
    modifier: Modifier = Modifier
) {
    SurfacePro(tonalElevation = topBarTonal.value) {
        Column(
            modifier
                .statusBarsPadding()
                .fillMaxWidth()
                .height(Theme.widgetSize.topBarLargeCollapsedHeight),
            verticalArrangement = Arrangement.Center
        ) {
            counterBar.Widget(isShowShareIcon = false)
        }
    }
}