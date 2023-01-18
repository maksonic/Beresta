package ru.maksonic.beresta.screen.settings

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 16.01.2023
 */
@Composable
fun SettingsScreen() {
    Content()
}

@Composable
private fun Content(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()
    val firstVisibleItem = scrollState.isVisibleFirstItem()
    val topBarColor = animateColorAsState(
        targetValue = if (firstVisibleItem.value) background else tertiaryContainer
    )

    Column {
        SystemStatusBar(changeableBackgroundColor = { topBarColor.value })
        TopAppBarNormal(
            title = stringResource(id = R.string.txt_title_setting),
            backgroundColor = { topBarColor.value },
            backAction = { }
        )
        LazyColumn(state = scrollState, modifier = modifier.weight(1f)) {
            items(50) {
                Text("Setting title", modifier = modifier.padding(top = dp16))
            }
        }
    }


}