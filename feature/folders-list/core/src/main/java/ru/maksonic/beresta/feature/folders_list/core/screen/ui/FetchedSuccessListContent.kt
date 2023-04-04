package ru.maksonic.beresta.feature.folders_list.core.screen.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import ru.maksonic.beresta.feature.folders_list.api.ui.FilterChipUi
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Model
import ru.maksonic.beresta.feature.folders_list.core.screen.core.Msg
import ru.maksonic.beresta.ui.theme.Theme
import ru.maksonic.beresta.ui.theme.color.*
import ru.maksonic.beresta.ui.theme.component.*
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Done
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick

/**
 * @Author maksonic on 03.04.2023
 */
@Composable
internal fun FetchedSuccessListContent(
    model: Model,
    send: SendMessage,
    folders: FilterChipUi.Collection,
    scrollState: () -> LazyListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = scrollState(),
        contentPadding = PaddingValues(
            top = dp16,
            bottom = Theme.widgetSize.btnPrimaryHeight.plus(dp16)
        ),
        modifier = modifier.fillMaxSize()
    ) {
        items(folders.data, key = { it.id }) { folder ->
            val update = folder.copy(isCurrent = folder.id == model.currentSelectedFolderId)
            FolderItem(update, onFolderClick = { send(Msg.Ui.OnFolderClicked(folder.id)) })
        }
    }
}

@Composable
fun FolderItem(folder: FilterChipUi, onFolderClick: () -> Unit, modifier: Modifier = Modifier) {
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val isSelectedColors = if (isFocusedItem.value) tertiary else secondary
    val colors = if (isFocusedItem.value) outlineVariant else primaryContainer
    val backgroundColor = animateColorAsState(if (folder.isSelected) isSelectedColors else colors)

    BoxWithScaleInOutOnClick(
        onClick = onFolderClick,
        onLongClick = { },
        backgroundColor = { backgroundColor.value },
        shape = Shape.cornerNormal,
        modifier = modifier
            .padding(start = dp16, end = dp16, bottom = dp10)
            .onFocusChanged { isFocusedItem.value = it.isFocused }
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(top = dp10, bottom = dp10, start = dp8)
        ) {

            Box(modifier.size(dp32), contentAlignment = Alignment.Center) {
                if (folder.isCurrent) {
                    Icon(
                        imageVector = AppIcon.Done,
                        tint = tertiaryContainer,
                        contentDescription = ""
                    )
                }
            }

            Text(
                text = folder.title,
                style = TextDesign.title.copy(color = onPrimaryContainer),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier.padding(start = dp8, end = dp8)
            )
        }
    }
}