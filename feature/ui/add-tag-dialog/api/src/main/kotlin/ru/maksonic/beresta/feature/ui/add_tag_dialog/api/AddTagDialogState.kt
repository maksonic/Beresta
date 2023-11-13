package ru.maksonic.beresta.feature.ui.add_tag_dialog.api

/**
 * @Author maksonic on 13.11.2023
 */
data class AddTagDialogState(
    val isVisible: Boolean,
    val isNewTag: Boolean,
    val passedTagId: Long
) {
    companion object {
        val Initial = AddTagDialogState(
            isVisible = false,
            isNewTag = true,
            passedTagId = 0L
        )
    }
}