package ru.maksonic.beresta.feature.folders_list.data.di

import org.koin.dsl.module
import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.folders_list.data.FetchFolderByIdUseCaseImpl
import ru.maksonic.beresta.feature.folders_list.data.FetchFoldersTrashListUseCaseImpl
import ru.maksonic.beresta.feature.folders_list.data.FetchFoldersUseCaseImpl
import ru.maksonic.beresta.feature.folders_list.data.FoldersRepositoryImpl
import ru.maksonic.beresta.feature.folders_list.data.StickyFoldersTitleFormatterCore
import ru.maksonic.beresta.feature.folders_list.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_list.domain.FoldersRepository
import ru.maksonic.beresta.feature.folders_list.domain.StickyFoldersTitleFormatter
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFolderByIdUseCase
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersTrashListUseCase
import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersUseCase

/**
 * @Author maksonic on 13.10.2023
 */
val foldersListDataModule = module {
    single<FoldersRepository> {
        FoldersRepositoryImpl(
            foldersCacheDataSource = get(),
            mapper = get(),
            appLangRepository = get(),
            stickyFoldersTitleFormatter = get(),
            dateFormatter = get()
        )
    }
    single { FoldersInteractor(repository = get()) }
    factory<FetchFoldersUseCase> { FetchFoldersUseCaseImpl(repository = get()) }
    factory<FetchFolderByIdUseCase> { FetchFolderByIdUseCaseImpl(repository = get()) }
    factory<FetchFoldersTrashListUseCase> { FetchFoldersTrashListUseCaseImpl(repository = get()) }
    factory<StickyFoldersTitleFormatter<AppLangDomain>> { StickyFoldersTitleFormatterCore() }
}