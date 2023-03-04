package ru.maksonic.beresta.feature.edit_note.core.screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.edit_note.core.screen.core.EditNoteSandbox
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Eff
import ru.maksonic.beresta.feature.edit_note.core.screen.core.Msg
import ru.maksonic.beresta.navigation.router.router.EditNoteRouter
import ru.maksonic.beresta.ui.theme.color.background
import ru.maksonic.beresta.ui.widget.SystemNavigationBar
import ru.maksonic.beresta.ui.widget.SystemStatusBar
import ru.maksonic.beresta.ui.widget.bar.TopAppBarNormal
import ru.maksonic.beresta.ui.widget.functional.HandleEffectsWithLifecycle
import ru.maksonic.beresta.ui.widget.functional.noRippleClickable

/**
 * @Author maksonic on 04.03.2023
 */
internal typealias SendMessage = (Msg) -> Unit

@Composable
fun EditNoteScreen(
    modifier: Modifier = Modifier,
    isExpandedFab: Boolean = false,
    collapseFabWidget: () -> Unit = {},
    router: EditNoteRouter? = null,
) {
    Content(
        modifier = modifier,
        isExpandedFab = isExpandedFab,
        collapseFabWidget = collapseFabWidget,
        router = router
    )
}

@Composable
fun Content(
    isExpandedFab: Boolean = false,
    collapseFabWidget: () -> Unit,
    router: EditNoteRouter?,
    sandbox: EditNoteSandbox = koinViewModel(),
    modifier: Modifier
) {

    HandleEffects(effects = sandbox.effects, router = router)
    val bg = background

    Column(
        modifier
            .fillMaxSize()
            .background(background)
            .noRippleClickable { }) {

        SystemStatusBar(backgroundColor = { bg })
        TopAppBarNormal(
            title = "",
            backgroundColor = { bg },
            backAction = {
                if (isExpandedFab) collapseFabWidget() else sandbox.sendMsg(Msg.Ui.OnTopBarBackPressed)
            },
        )
        Spacer(modifier.weight(1f))
        SystemNavigationBar(backgroundColor = { bg })
    }
}

@Composable
private fun HandleEffects(effects: Flow<Eff>, router: EditNoteRouter?) {
    HandleEffectsWithLifecycle(effects) { eff ->
        when (eff) {
            is Eff.NavigateBack -> router?.let { it.onBack() } ?: {}
        }
    }
}
