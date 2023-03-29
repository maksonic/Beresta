package ru.maksonic.beresta.feature.notes_list.api

/**
 * @Author maksonic on 29.03.2023
 */

data class NewFolderDialogSharedState(
    val isVisible: Boolean,
    val folderInputName: String,
    val updateInputField: (String) -> Unit,
    val onDismissClicked: () -> Unit,
    val onCreateNewFolderClicked: () -> Unit,
)