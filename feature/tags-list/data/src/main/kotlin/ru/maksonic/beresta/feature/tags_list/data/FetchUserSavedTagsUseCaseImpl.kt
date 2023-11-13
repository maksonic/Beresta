package ru.maksonic.beresta.feature.tags_list.data

import ru.maksonic.beresta.feature.tags_list.domain.FetchUserSavedTagsUseCase
import ru.maksonic.beresta.feature.tags_list.domain.TagsList
import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository

/**
 * @Author maksonic on 13.11.2023
 */
class FetchUserSavedTagsUseCaseImpl(
    private val repository: TagsRepository
) : FetchUserSavedTagsUseCase {
    override fun invoke(): TagsList = repository.fetchUserSavedTags()
}