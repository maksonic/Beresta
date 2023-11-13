package ru.maksonic.beresta.screen.settings.tags.core

import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository
import ru.maksonic.beresta.feature.tags_list.ui.api.NoteTagUi
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram

/**
 * @Author maksonic on 12.11.2023
 */
class SettingsTagsProgram(
    private val repository: TagsRepository,
    private val tagUiMapper: TagUiMapper
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchData -> fetchTags(consumer)
            is Cmd.DeleteTag -> deleteTag(cmd.tag)
        }
    }

    private suspend fun fetchTags(consumer: (Msg) -> Unit) = runCatching {
        repository.fetchUserSavedTags().collect { tagsDomain ->
            val tags = tagUiMapper.mapListTo(tagsDomain)

            consumer(Msg.Inner.FetchedDataResult(NoteTagUi.Collection(tags)))
        }
    }.onFailure {
        consumer(Msg.Inner.FetchedDataResult(NoteTagUi.Collection(emptyList())))
    }

    private suspend fun deleteTag(tag: NoteTagUi) = repository.delete(tagUiMapper.mapFrom(tag))
}