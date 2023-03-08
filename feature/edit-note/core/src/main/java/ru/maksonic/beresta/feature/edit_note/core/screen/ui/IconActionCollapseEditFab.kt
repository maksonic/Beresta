package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 06.03.2023
 */
@Composable
internal fun IconActionCollapseEditFab(onBtnClicked: () -> Unit, modifier: Modifier = Modifier) {
    BoxWithScaleInOutOnClick(
        onClick = { onBtnClicked() },
        modifier = modifier
            .statusBarsPadding()
            .padding(start = dp4)
            .size(Theme.widgetSize.minimumTouchTargetSize)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = primaryContainer),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = modifier.size(36.dp),
        ) {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(imageVector = AppIcon.ArrowBack, contentDescription = "", tint = onBackground)
            }
        }
    }
}