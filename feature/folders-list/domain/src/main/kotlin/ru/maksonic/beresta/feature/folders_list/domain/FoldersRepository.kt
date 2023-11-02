package ru.maksonic.beresta.feature.folders_list.domain

import ru.maksonic.beresta.common.domain.Repository

/**
 * @Author maksonic on 13.10.2023
 */
interface FoldersRepository : Repository<FolderDomain> {
    fun fetchFoldersTrashList(): FoldersDomainList
}