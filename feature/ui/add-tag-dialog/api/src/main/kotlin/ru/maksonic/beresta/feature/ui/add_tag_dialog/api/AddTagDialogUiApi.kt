package ru.maksonic.beresta.feature.ui.add_tag_dialog.api

import androidx.compose.runtime.Composable

/**
 * @Author maksonic on 13.11.2023
 */
interface AddTagDialogUiApi {
    @Composable
    fun Widget(sharedState: AddTagDialogState, hideDialog: () -> Unit)
}