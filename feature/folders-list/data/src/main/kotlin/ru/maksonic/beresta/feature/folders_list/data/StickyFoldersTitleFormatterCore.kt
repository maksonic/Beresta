package ru.maksonic.beresta.feature.folders_list.data

import ru.maksonic.beresta.feature.app_lang.domain.AppLangDomain
import ru.maksonic.beresta.feature.folders_list.domain.FolderDomain
import ru.maksonic.beresta.feature.folders_list.domain.StickyFoldersTitleFormatter

/**
 * @Author maksonic on 18.10.2023
 */
class StickyFoldersTitleFormatterCore : StickyFoldersTitleFormatter<AppLangDomain> {

    companion object {
        private const val STICKY_START_ITEM_ID = 1L
        private const val STICKY_END_ITEM_ID = 2L
    }

    override fun format(folder: FolderDomain, currentLang: AppLangDomain): String {
        val stickyStartItemTitle = when (currentLang) {
            AppLangDomain.RUSSIAN -> "Все заметки"
            AppLangDomain.ENGLISH -> "All"
            AppLangDomain.CHINESE -> "所有笔记"
            AppLangDomain.CHINESE_TR -> "所有筆記"
        }

        val stickyEndItemTitle = when (currentLang) {
            AppLangDomain.RUSSIAN -> "Без категории"
            AppLangDomain.ENGLISH -> "Uncategorized"
            AppLangDomain.CHINESE -> "未分类"
            AppLangDomain.CHINESE_TR -> "未分類"
        }

        return when (folder.id) {
            STICKY_START_ITEM_ID -> stickyStartItemTitle
            STICKY_END_ITEM_ID -> stickyEndItemTitle
            else -> folder.title
        }
    }
}