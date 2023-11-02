package ru.maksonic.beresta.feature.folders_list.domain

/**
 * @Author maksonic on 18.10.2023
 */
interface StickyFoldersTitleFormatter<AppLangDomain> {
    fun format(folder: FolderDomain, currentLang: AppLangDomain): String
}