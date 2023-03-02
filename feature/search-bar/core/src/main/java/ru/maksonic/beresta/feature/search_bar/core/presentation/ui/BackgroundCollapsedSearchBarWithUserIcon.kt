package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.UserAccountCircleAvatarTopBarWidget

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun BackgroundCollapsedSearchBarWithUserIcon(
    searchTopBarBackground: () -> Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .drawBehind { drawRect(searchTopBarBackground()) }
    ) {
        SystemStatusBar(backgroundColor = searchTopBarBackground)

        Box(
            modifier
                .fillMaxWidth()
                .height(Theme.widgetSize.topBarMediumHeight), contentAlignment = Alignment.CenterStart
        ) {
            UserAccountCircleAvatarTopBarWidget(modifier.padding(top = dp8, start = dp16))
        }
    }
}