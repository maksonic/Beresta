package ru.maksonic.beresta.feature.tags_list.data

import ru.maksonic.beresta.feature.tags_list.domain.FetchNoteTagsUseCase
import ru.maksonic.beresta.feature.tags_list.domain.TagsList
import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository

/**
 * @Author maksonic on 11.11.2023
 */
class FetchNoteTagsUseCaseImpl(private val repository: TagsRepository) : FetchNoteTagsUseCase {
    override fun invoke(): TagsList = repository.fetchList()
}