package ru.maksonic.beresta.feature.notes.folders.api.ui

import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage

/**
 * @Author maksonic on 06.06.2023
 */
interface StickyItemsTitleFormatter {
    fun format(data: List<NoteFolderUi>, currentLang: AppLanguage): List<NoteFolderUi>

    class Core : StickyItemsTitleFormatter {

        override fun format(
            data: List<NoteFolderUi>,
            currentLang: AppLanguage
        ): List<NoteFolderUi> {
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

             val list = data.mapIndexed { index, noteFolderUi ->
                when (index) {
                    0 -> noteFolderUi.copy(title = stickyStartItemTitle)
                    data.lastIndex -> noteFolderUi.copy(title = stickyEndItemTitle)
                    else -> noteFolderUi
                }
            }
            return list
        }
    }
}