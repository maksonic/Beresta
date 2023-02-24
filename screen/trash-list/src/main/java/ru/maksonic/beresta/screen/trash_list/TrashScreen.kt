package ru.maksonic.beresta.screen.trash_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.navigation.router.router.TrashScreenRouter
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
fun TrashScreen(router: TrashScreenRouter) {
    Content(router)
}

@Composable
private fun Content(router: TrashScreenRouter, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .background(background)
    ) {
        val topBarBackground = background

        SystemStatusBar(backgroundColor = { topBarBackground })
        TopAppBarNormal(
            title = "Trash",
            backgroundColor = { topBarBackground },
            backAction = router.onBack
        )
        Spacer(modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { topBarBackground })
    }
}