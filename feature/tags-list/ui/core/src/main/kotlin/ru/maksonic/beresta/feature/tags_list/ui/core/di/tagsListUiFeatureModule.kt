package ru.maksonic.beresta.feature.tags_list.ui.core.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.maksonic.beresta.feature.tags_list.ui.api.TagUiMapper
import ru.maksonic.beresta.feature.tags_list.ui.api.TagsListUiApi
import ru.maksonic.beresta.feature.tags_list.ui.core.TagUiMapperImpl
import ru.maksonic.beresta.feature.tags_list.ui.core.TagsListProgram
import ru.maksonic.beresta.feature.tags_list.ui.core.TagsListSandbox
import ru.maksonic.beresta.feature.tags_list.ui.core.TagsListSheetUiCore

/**
 * @Author maksonic on 05.11.2023
 */
val tagsListUiFeatureModule = module {
    factory<TagUiMapper> { TagUiMapperImpl() }
    factory { TagsListProgram(tagsRepository = get(), mapper = get()) }
    viewModel { TagsListSandbox(program = get()) }
    factory<TagsListUiApi> { TagsListSheetUiCore() }
}