package ru.maksonic.beresta.feature.folders_chips.core

import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.StickyFoldersTitleFormatter
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 04.07.2023
 */
class StickyFoldersTitleFormatterCore : StickyFoldersTitleFormatter {

    companion object {
        const val STICKY_START_ITEM_ID = 1L
        const val STICKY_END_ITEM_ID = 2L
    }

    override fun format(folder: FolderUi, currentLang: AppLanguage): String {
        val stickyStartItemTitle = when (currentLang) {
            AppLanguage.RUSSIAN -> "Все заметки"
            AppLanguage.ENGLISH -> "All"
            AppLanguage.CHINESE -> "所有笔记"
            AppLanguage.CHINESE_TR -> "所有筆記"
        }

        val stickyEndItemTitle = when (currentLang) {
            AppLanguage.RUSSIAN -> "Без категории"
            AppLanguage.ENGLISH -> "Uncategorized"
            AppLanguage.CHINESE -> "未分类"
            AppLanguage.CHINESE_TR -> "未分類"
        }

        return when (folder.id) {
            STICKY_START_ITEM_ID -> stickyStartItemTitle
            STICKY_END_ITEM_ID -> stickyEndItemTitle
            else -> folder.title
        }
    }
}