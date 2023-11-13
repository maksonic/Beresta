package ru.maksonic.beresta.feature.ui.add_tag_dialog.core.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 13.11.2023
 */
class AddTagDialogProgram(
    private val repository: TagsRepository,
    private val mapper: TagUiMapper,
    private val ioDispatcher: CoroutineDispatcher
) : ElmProgram<Msg, Cmd> {

    private companion object {
        private const val DEFAULT_TAG_ID = 0L
    }

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchTagById -> fetchTagById(cmd.id, consumer)
            is Cmd.SaveOrUpdateCurrentTag -> saveOrUpdateCurrentFolder(cmd.tag)
        }
    }

    private suspend fun fetchTagById(id: Long, consumer: (Msg) -> Unit) = runCatching {
        if (id == DEFAULT_TAG_ID) {
            consumer(Msg.Inner.FetchedTagResult(NoteTagUi.Empty))
        } else {
            CoroutineScope(ioDispatcher).launch {
                repository.fetchById(id).cancellable().collect { tagDomain ->
                    consumer(Msg.Inner.FetchedTagResult(mapper.mapTo(tagDomain))).let {
                        this.coroutineContext.job.cancel()
                    }
                }
            }
        }
    }.onFailure {
        consumer(Msg.Inner.FetchedTagResult(NoteTagUi.Empty))
    }

    private suspend fun saveOrUpdateCurrentFolder(tag: NoteTagUi) = repository.let {
        val tagDomain = mapper.mapFrom(tag)
        if (tag.id == DEFAULT_TAG_ID) it.add(tagDomain) else it.update(tagDomain)
    }
}