package ru.maksonic.beresta.screen.trash_list

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import ru.maksonic.beresta.navigation.router.router.TrashScreenRouter
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.bar.TopAppBarCollapsingLarge

/**
 * @Author maksonic on 24.02.2023
 */
@Composable
fun TrashScreen(router: TrashScreenRouter) {
    Content(router)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(router: TrashScreenRouter, modifier: Modifier = Modifier) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopAppBarCollapsingLarge(
                scrollBehavior = scrollBehavior,
                title = "Trash screen",
                onBackAction = { router.onBack }
            )
        },
        containerColor = background,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddings ->

    }
}