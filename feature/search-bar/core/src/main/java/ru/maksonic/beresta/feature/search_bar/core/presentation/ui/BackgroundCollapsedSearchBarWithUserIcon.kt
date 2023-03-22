package ru.maksonic.beresta.feature.search_bar.core.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.core.R
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.functional.clickAction

/**
 * @Author maksonic on 22.02.2023
 */
@Composable
internal fun BackgroundCollapsedSearchBarWithUserIcon(modifier: Modifier = Modifier) {
    Box(
        modifier
            .padding(start = dp16)
            .statusBarsPadding()
            .height(Theme.widgetSize.topBarNormalHeight),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_user_avatar),
            contentDescription = "",
            modifier = modifier
                .clip(CircleShape)
                .size(36.dp)
                .clickAction(rippleColor = primary) { }
        )
    }
}