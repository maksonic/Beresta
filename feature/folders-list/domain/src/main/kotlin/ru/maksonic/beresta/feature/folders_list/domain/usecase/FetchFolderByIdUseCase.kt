package ru.maksonic.beresta.feature.folders_list.domain.usecase

import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.folders_list.domain.FolderDomainItem

/**
 * @Author maksonic on 15.10.2023
 */
interface FetchFolderByIdUseCase : UseCase.WithArgs<FolderDomainItem, Long>