package ru.maksonic.beresta.feature.sorting_sheet.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.maksonic.beresta.common.domain.UseCase
import ru.maksonic.beresta.feature.sorting_sheet.domain.sort.FoldersSortDomain

/**
 * @Author maksonic on 17.10.2023
 */
interface FetchFoldersSortUseCase : UseCase.Default<Flow<FoldersSortDomain>>