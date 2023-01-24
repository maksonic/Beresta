package ru.maksonic.beresta.feature.trash_list.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.notes_list.api.collection.NotesCollection
import ru.maksonic.beresta.feature.trash_list.ui.core.Feature
import ru.maksonic.beresta.feature.trash_list.ui.core.TrashSandbox
import ru.maksonic.beresta.navigation.router.router.TrashScreenRouter
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.color.tertiaryContainer
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.isVisibleFirstItem

/**
 * @Author maksonic on 23.01.2023
 */
internal typealias SendMessage = (Feature.Msg) -> Unit

@Composable
fun TrashListScreen(router: TrashScreenRouter, sandbox: TrashSandbox = koinViewModel()) {
    val model = sandbox.model.collectAsState().value

    Content(model, msg = sandbox::sendMsg)
}

@Composable
fun Content(model: Feature.Model, msg: SendMessage, modifier: Modifier = Modifier) {
    val trashScreenState = rememberLazyListState()
    val isVisibleFirstItem = trashScreenState.isVisibleFirstItem()

    val topBarColor =
        animateColorAsState(targetValue = if (isVisibleFirstItem.value) background else tertiaryContainer)

    val bottomBarColor =
        animateColorAsState(targetValue = if (isVisibleFirstItem.value) background else tertiaryContainer)

    Column(modifier.fillMaxSize()) {
        SystemStatusBar(changeableBackgroundColor = { topBarColor.value })
        TopAppBarNormal(
            title = stringResource(R.string.title_scr_trash),
            backgroundColor = { topBarColor.value },
            backAction = { msg(Feature.Msg.Ui.TopBarBackPressed) }
        )

        LazyColumn(state = trashScreenState, modifier = modifier.weight(1f)) {
            items(items = NotesCollection(model.notes).notes, key = { note -> note.id }) {

            }
        }
        SystemNavigationBar(changeableBackgroundColor = { bottomBarColor.value })
    }
}