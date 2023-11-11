package ru.maksonic.beresta.feature.tags_list.ui.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.compose.koinViewModel
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.platform.elm.compose.ElmComposableEffectHandler

/**
 * @Author maksonic on 05.11.2023
 */
internal typealias Send = (Msg) -> Unit

@Composable
internal fun Container(
    passedNoteTagsIds: List<Long>?,
    updatedNoteTags: (List<NoteTagUi>) -> Unit,
    hideSheet: () -> Unit,
    sandbox: TagsListSandbox = koinViewModel()
) {
    val model by sandbox.model.collectAsStateWithLifecycle()

    HandleUiEffects(sandbox.effects, updatedNoteTags)

    LaunchedEffect(Unit) {
        sandbox.send(Msg.Inner.FetchedTags(passedNoteTagsIds))
    }

    Content(model, sandbox::send, hideSheet)
}

@Composable
private fun HandleUiEffects(
    effects: Flow<Eff>,
    updatedNoteTags: (List<NoteTagUi>) -> Unit
) {

    ElmComposableEffectHandler(effects) { eff ->
        when (eff) {
            is Eff.UpdateNoteTags -> updatedNoteTags(eff.tags)
        }
    }
}