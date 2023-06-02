package ru.maksonic.beresta.screen.trash_list.folders.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.feature.top_bar_counter.api.TopBarCounterApi
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.screen.trash_list.folders.core.Model
import ru.maksonic.beresta.screen.trash_list.folders.core.Msg
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal

/**
 * @Author maksonic on 30.05.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TrashFoldersScreenContent(
    model: State<Model>,
    send: SendMessage,
    topBarCounterApi: TopBarCounterApi.Ui,
    modifier: Modifier = Modifier
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val scrollState = rememberLazyListState()

    // Box(modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {

    Scaffold(
        topBar = {
            TopAppBarNormal(
                title = text.trash.topBarTitleTrashedFolders,
                scrollBehavior = scrollBehavior,
                onBackAction = { send(Msg.Ui.OnTopBarBackPressed) })
        },
        containerColor = background,
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddings ->
        Column(
            modifier
                .fillMaxSize()
                .padding(paddings)
                .verticalScroll(rememberScrollState())) {
            repeat(30) {
                Box(
                    modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(dp16)
                        .background(Color.Red))
            }
        }
        //   }
    }
}
