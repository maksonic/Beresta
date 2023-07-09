package ru.maksonic.beresta.feature.folders_chips.api.domain

/**
 * @Author maksonic on 30.03.2023
 */
interface FoldersInteractor {
    suspend fun addFolder(folder: FolderDomain)
    suspend fun updateFolder(folder: FolderDomain)
    suspend fun removeFolder(folder: FolderDomain)
    suspend fun updateAll(folders: List<FolderDomain>)
    suspend fun deleteNote(note: FolderDomain)
    suspend fun deleteAll(notes: List<FolderDomain>)

    class Impl(private val repository: FoldersRepository) : FoldersInteractor {
        override suspend fun addFolder(folder: FolderDomain) = repository.addNewItem(folder)
        override suspend fun updateFolder(folder: FolderDomain) = repository.updateItem(folder)
        override suspend fun removeFolder(folder: FolderDomain) = repository.deleteItem(folder)
        override suspend fun updateAll(folders: List<FolderDomain>) =
            repository.updateAllItems(folders)

        override suspend fun deleteNote(note: FolderDomain) = repository.deleteItem(note)
        override suspend fun deleteAll(notes: List<FolderDomain>) =
            repository.clearItemsList(notes)
    }
}