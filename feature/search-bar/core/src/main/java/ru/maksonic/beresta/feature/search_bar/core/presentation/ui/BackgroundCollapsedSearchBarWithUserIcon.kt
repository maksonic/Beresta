package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.UserAccountCircleAvatarTopBarWidget

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun BackgroundCollapsedSearchBarWithUserIcon(modifier: Modifier = Modifier) {
    Row(
        modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .background(background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        UserAccountCircleAvatarTopBarWidget(modifier.padding(end = dp16))
    }
}