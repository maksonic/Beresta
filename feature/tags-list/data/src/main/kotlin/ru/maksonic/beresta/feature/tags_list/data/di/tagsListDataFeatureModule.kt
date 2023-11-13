package ru.maksonic.beresta.feature.tags_list.data.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.maksonic.beresta.common.core.CoroutineDispatchers
import ru.maksonic.beresta.feature.tags_list.data.FetchNoteTagsUseCaseImpl
import ru.maksonic.beresta.feature.tags_list.data.FetchTagByIdUseCaseImpl
import ru.maksonic.beresta.feature.tags_list.data.FetchUserSavedTagsUseCaseImpl
import ru.maksonic.beresta.feature.tags_list.data.NoteTagCacheMapper
import ru.maksonic.beresta.feature.tags_list.data.NoteTagsDataDataSource
import ru.maksonic.beresta.feature.tags_list.data.TagsRepositoryImpl
import ru.maksonic.beresta.feature.tags_list.data.tags.TagsLangStore
import ru.maksonic.beresta.feature.tags_list.domain.FetchNoteTagsUseCase
import ru.maksonic.beresta.feature.tags_list.domain.FetchTagByIdUseCase
import ru.maksonic.beresta.feature.tags_list.domain.FetchUserSavedTagsUseCase
import ru.maksonic.beresta.feature.tags_list.domain.TagsRepository

/**
 * @Author maksonic on 05.11.2023
 */
val tagsListDataFeatureModule = module {
    factory { TagsLangStore(json = get(), jsonConverter = get(), fetchAppLangUseCase = get()) }
    factory {
        NoteTagsDataDataSource(
            noteTagDao = get(),
            ioDispatcher = get(named(CoroutineDispatchers.IO))
        )
    }
    factory { NoteTagCacheMapper() }
    factory<FetchNoteTagsUseCase> { FetchNoteTagsUseCaseImpl(repository = get()) }
    factory<FetchUserSavedTagsUseCase> { FetchUserSavedTagsUseCaseImpl(repository = get()) }
    factory<FetchTagByIdUseCase> { FetchTagByIdUseCaseImpl(repository = get()) }
    factory<TagsRepository> {
        TagsRepositoryImpl(
            noteTagsCacheDataSource = get(), mapper = get(), defaultTagsStore = get()
        )
    }
}