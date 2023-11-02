package ru.maksonic.beresta.feature.folders_list.ui.core

import androidx.compose.runtime.mutableLongStateOf
import ru.maksonic.beresta.feature.folders_list.ui.api.FoldersChipsRowUiApi
import ru.maksonic.beresta.platform.core.ui.update

/**
 * @Author maksonic on 20.10.2023
 */
class CurrentSelectedFolderUiStoreCore : FoldersChipsRowUiApi.CurrentSelectedFolderStore {
    override val id = mutableLongStateOf(1)
    override fun updateId(value: Long) = id.update(value)
}