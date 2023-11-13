package ru.maksonic.beresta.feature.ui.add_tag_dialog.core

import androidx.compose.runtime.Composable
import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogState
import ru.maksonic.beresta.feature.ui.add_tag_dialog.api.AddTagDialogUiApi

/**
 * @Author maksonic on 13.11.2023
 */
class AddTagDialogUiCore : AddTagDialogUiApi {

    @Composable
    override fun Widget(sharedState: AddTagDialogState, hideDialog: () -> Unit) {
        Container(sharedState, hideDialog)
    }
}