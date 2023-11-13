package ru.maksonic.beresta.feature.tags_list.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.transform
import ru.maksonic.beresta.common.data.BaseRepository
import ru.maksonic.beresta.database.tags.NoteTagCache
import ru.maksonic.beresta.feature.tags_list.data.tags.TagsLangStore
import ru.maksonic.beresta.feature.tags_list.domain.NoteDefaultTagDomain
import ru.maksonic.beresta.feature.tags_list.domain.TagsList
import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository

/**
 * @Author maksonic on 13.10.2023
 */
class TagsRepositoryImpl(
    private val noteTagsCacheDataSource: NoteTagsDataDataSource,
    private val defaultTagsStore: TagsLangStore,
    private val mapper: NoteTagCacheMapper,
) : BaseRepository<NoteTagCache, NoteDefaultTagDomain>(
    noteTagsCacheDataSource, mapper
), TagsRepository {
    override fun fetchList(): Flow<List<NoteDefaultTagDomain>> = combineTransform(
        defaultTagsStore.fetchTags(), noteTagsCacheDataSource.fetchCacheNoteTagsList()
    ) { defaultTagsResult, userTags ->
        val userSavedTags = mapper.mapListTo(userTags)

        defaultTagsResult.onSuccess { defaultTags -> emit(defaultTags + userSavedTags) }
        defaultTagsResult.onFailure { emit(userSavedTags) }
    }

    override fun fetchUserSavedTags(): TagsList =
        noteTagsCacheDataSource.fetchCacheNoteTagsList().transform { emit(mapper.mapListTo(it)) }

    override fun fetchById(itemId: Long): Flow<NoteDefaultTagDomain> =
        noteTagsCacheDataSource.fetchCacheOneItemById(itemId).transform { emit(mapper.mapTo(it)) }
}