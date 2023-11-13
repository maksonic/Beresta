package ru.maksonic.beresta.common.ui_kit.widget.trash_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.beresta.common.ui_kit.icons.AppIcon
import ru.maksonic.beresta.common.ui_kit.icons.Close
import ru.maksonic.beresta.common.ui_kit.icons.Delete
import ru.maksonic.beresta.common.ui_kit.icons.Restart
import ru.maksonic.beresta.common.ui_kit.sheet.ModalSheetItem
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 20.10.2023
 */
@Composable
fun TrashModalSheetDeleteDataContent(
    hideSheet: () -> Unit,
    onRestoreClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        ModalSheetItem(AppIcon.Restart, text.shared.btnTitleRestore, onRestoreClicked)
        ModalSheetItem(AppIcon.Delete, text.shared.btnTitleDelete, onDeleteClicked)
        ModalSheetItem(AppIcon.Close, text.shared.btnTitleClose, hideSheet)
        Spacer(modifier.size(dp24))
    }
}