package ru.maksonic.beresta.feature.tags_list.data

import ru.maksonic.beresta.feature.tags_list.domain.FetchTagByIdUseCase
import ru.maksonic.beresta.feature.tags_list.domain.TagItem
import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository

/**
 * @Author maksonic on 07.11.2023
 */
class FetchTagByIdUseCaseImpl(private val repository: TagsRepository) : FetchTagByIdUseCase {
    override fun invoke(args: Long): TagItem = repository.fetchById(args)
}