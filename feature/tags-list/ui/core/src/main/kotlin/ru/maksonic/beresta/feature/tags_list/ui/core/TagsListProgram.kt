package ru.maksonic.beresta.feature.tags_list.ui.core

import kotlinx.coroutines.delay
import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 05.11.2023
 */
class TagsListProgram(
    private val tagsRepository: TagsRepository,
    private val mapper: TagUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchTags -> fetchTags(cmd.noteTagsIds, consumer)
        }
    }

    private suspend fun fetchTags(noteTagsIds: List<Long>?, consumer: (Msg) -> Unit) = runCatching {
        delay(300)
        tagsRepository.fetchList().collect { tags ->
            val tagsUi = mapper.mapListTo(tags)
            val tagsList = if (noteTagsIds.isNullOrEmpty()) {
                tagsUi
            } else {
                tagsUi.map { tag -> tag.copy(isSelected = noteTagsIds.contains(tag.id)) }
            }
            consumer(Msg.Inner.FetchedTagsResult(NoteTagUi.Collection(tagsList)))
        }
    }
}