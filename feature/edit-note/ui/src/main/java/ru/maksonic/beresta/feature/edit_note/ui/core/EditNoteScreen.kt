package ru.maksonic.beresta.feature.edit_note.ui.core

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextField
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.ui.core.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.ui.core.core.Eff
import ru.maksonic.beresta.feature.edit_note.ui.core.core.Model
import ru.maksonic.beresta.feature.edit_note.ui.core.core.Msg
import ru.maksonic.beresta.feature.edit_note.ui.core.widget.MessagePlaceholder
import ru.maksonic.beresta.feature.edit_note.ui.core.widget.TitlePlaceholder
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.BaseTextFieldColors
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle

/**
 * @Author maksonic on 24.01.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun EditNoteScreen(router: EditNoteRouter, sandbox: EditNoteSandbox = koinViewModel()) {
    val model = sandbox.model.collectAsState().value

    HandleUiEffects(sandbox.effects, router)
    Content(model, sandbox::sendMsg)
}

@Composable
private fun HandleUiEffects(effects: Flow<Eff>, router: EditNoteRouter) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router.onBack()
            is Eff.Nothing -> {}
        }
    }
}

@Composable
private fun Content(model: Model, send: SendMessage, modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize()) {
        val topBarBackgroundColor = background
        val navBarBackgroundColor = background

        LazyColumn(
            modifier
                .systemBarsPadding()
                .padding(top = Theme.widgetSize.topBarNormalHeight)
                .fillMaxSize()
        ) {
            item {
                TextField(
                    value = model.note.title,
                    onValueChange = { send(Msg.Inner.UpdateCurrentTitle(it)) },
                    textStyle = TextDesign.header,
                    placeholder = { TitlePlaceholder() },
                    colors = BaseTextFieldColors,
                    modifier = modifier.fillMaxWidth()
                )
            }
            item {
                TextField(
                    value = model.note.message,
                    onValueChange = { send(Msg.Inner.UpdateCurrentMessage(it)) },
                    textStyle = TextDesign.main,
                    placeholder = { MessagePlaceholder() },
                    colors = BaseTextFieldColors,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

        Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.End) {
            SystemStatusBar(changeableBackgroundColor = { topBarBackgroundColor })
            TopAppBarNormal(
                title = "",
                backgroundColor = { topBarBackgroundColor },
                backAction = { send(Msg.Ui.OnTopBarBackClicked) })
            Spacer(modifier.weight(1f))
            FloatingActionButton(onClick = {
                send(Msg.Ui.OnSaveNoteClicked(model.note))
            }, modifier = modifier.padding(bottom = dp16, end = dp16)) {

            }
            SystemNavigationBar(changeableBackgroundColor = { navBarBackgroundColor })
        }
    }
}

