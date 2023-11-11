package ru.maksonic.beresta.feature.tags_list.domain

import ru.maksonic.beresta.common.domain.UseCase

/**
 * @Author maksonic on 07.11.2023
 */
interface FetchTagByIdUseCase: UseCase.WithArgs<TagItem, Long>