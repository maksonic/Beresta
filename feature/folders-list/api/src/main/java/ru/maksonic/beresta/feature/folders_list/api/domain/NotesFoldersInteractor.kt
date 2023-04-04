package ru.maksonic.beresta.feature.folders_list.api.domain

/**
 * @Author maksonic on 30.03.2023
 */
interface NotesFoldersInteractor {
    suspend fun addFolder(folder: NoteFolderDomain)
    suspend fun updateFolder(folder: NoteFolderDomain)
    suspend fun removeFolder(folder: NoteFolderDomain)


    class Impl(private val repository: NotesFoldersRepository) : NotesFoldersInteractor {
        override suspend fun addFolder(folder: NoteFolderDomain) = repository.addNewItem(folder)
        override suspend fun updateFolder(folder: NoteFolderDomain) = repository.updateItem(folder)
        override suspend fun removeFolder(folder: NoteFolderDomain) = repository.removeItem(folder)
    }
}