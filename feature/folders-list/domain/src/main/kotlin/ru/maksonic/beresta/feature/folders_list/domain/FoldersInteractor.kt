package ru.maksonic.beresta.feature.folders_list.domain

import ru.maksonic.beresta.common.domain.BaseInteractor

/**
 * @Author maksonic on 13.10.2023
 */
class FoldersInteractor(repository: FoldersRepository) : BaseInteractor<FolderDomain>(repository)