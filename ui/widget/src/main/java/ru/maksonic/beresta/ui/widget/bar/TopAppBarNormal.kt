package ru.maksonic.beresta.ui.widget.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.ArrowBack
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 16.01.2023
 */
@Composable
fun TopAppBarNormal(
    modifier: Modifier = Modifier,
    title: String = "",
    backgroundColor: () -> Color = { Color.Transparent },
    backAction: () -> Unit,
    menuActions: @Composable () -> Unit = {},
) {
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .drawBehind { drawRect(backgroundColor()) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier.size(dp8))
        IconAction(icon = { AppIcon.ArrowBack }, action = backAction)
        Text(text = title, style = TextDesign.topBar, modifier = modifier.padding(start = dp8))
        Spacer(modifier.weight(1f))
        menuActions()
        Spacer(modifier.size(dp8))
    }
}