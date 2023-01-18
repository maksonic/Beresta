package ru.maksonic.beresta.ui.widget.bar

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import ru.maksonic.beresta.ui.theme.R
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.widget.button.IconAction

/**
 * @Author maksonic on 16.01.2023
 */
@Composable
fun TopAppBarNormal(
    title: String,
    backgroundColor: () -> Color,
    backAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    /*TopAppBar(
        title = { Text(text = title, style = TextDesign.title) },
        navigationIcon = {
           *//* IconAction(
                icon = painterResource(id = R.drawable.ic_arrow_back), action = backAction
            )*//*
        },
        backgroundColor = backgroundColor(),
        elevation = Theme.elevation.disable
    )*/
    Row(
        modifier
            .fillMaxWidth()
            .height(Theme.widgetSize.topBarNormalHeight)
            .drawBehind { drawRect(backgroundColor()) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier.size(dp8))
        IconAction(icon = painterResource(id = R.drawable.ic_arrow_back), action = backAction)
        Text(text = title, style = TextDesign.topBar, modifier = modifier.padding(start = dp8))
    }
}

/*@Composable
fun TopAppBarNormal(title: String, backgroundColor: () -> Color, backAction: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, style = TextDesign.title) },
        navigationIcon = {
            IconAction(
                icon = painterResource(id = R.drawable.ic_arrow_back), action = backAction
            )
        },
        backgroundColor = backgroundColor(),
        elevation = Theme.elevation.disable
    )
}*/