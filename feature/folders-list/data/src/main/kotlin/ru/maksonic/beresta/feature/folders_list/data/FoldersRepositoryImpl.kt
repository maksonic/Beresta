package ru.maksonic.beresta.feature.folders_list.data

import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.common.data.BaseRepository
import ru.maksonic.beresta.database.folders.FolderCache
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.app_lang.domain.AppLangRepository
import ru.maksonic.beresta.feature.app_lang.domain.formatter.DateFormatter
import ru.maksonic.beresta.feature.folders_list.data.local.FolderCacheMapper
import ru.maksonic.beresta.feature.folders_list.data.local.FoldersDataDataSource
import ru.maksonic.beresta.feature.folders_list.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_list.domain.FolderDomainItem
import ru.maksonic.beresta.feature.folders_list.domain.FoldersDomainList
import ru.maksonic.beresta.feature.folders_list.domain.FoldersRepository
import ru.maksonic.beresta.feature.folders_list.domain.StickyFoldersTitleFormatter

/**
 * @Author maksonic on 13.10.2023
 */
class FoldersRepositoryImpl(
    private val foldersCacheDataSource: FoldersDataDataSource,
    private val mapper: FolderCacheMapper,
    private val appLangRepository: AppLangRepository,
    private val stickyFoldersTitleFormatter: StickyFoldersTitleFormatter<AppLangDomain>,
    private val dateFormatter: DateFormatter
) : BaseRepository<FolderCache, FolderDomain>(foldersCacheDataSource, mapper), FoldersRepository {
    override fun fetchList(): FoldersDomainList = combine(
        foldersCacheDataSource.fetchCacheFoldersList(),
        appLangRepository.fetchAppCurrentLang()
    ) { folders, lang ->
        mapper.mapListTo(folders).map { folder ->
            folder.copy(title = stickyFoldersTitleFormatter.format(folder, lang))
        }
    }

    override fun fetchFoldersTrashList(): FoldersDomainList = combine(
        foldersCacheDataSource.fetchCacheFoldersTrashList(),
        appLangRepository.fetchAppCurrentLang()
    ) { folders, lang ->
        mapper.mapListTo(folders).map { folder ->
            val dateMovedToTrash = folder.dateMovedToTrashRaw?.let { date ->
                dateFormatter.fetchDateByLang(date, lang)
            }

            folder.copy(dateMovedToTrash = dateMovedToTrash)
        }
    }

    override fun fetchById(itemId: Long): FolderDomainItem =
        foldersCacheDataSource.fetchCacheOneItemById(itemId).transform { emit(mapper.mapTo(it)) }
}