package ru.maksonic.beresta.feature.notes.list.core.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import ru.maksonic.beresta.feature.notes.list.api.domain.DateFormatter
import ru.maksonic.beresta.feature.notes.list.api.ui.NoteUi
import ru.maksonic.beresta.language_engine.shell.provider.AppLanguage
import ru.maksonic.beresta.language_engine.shell.provider.text
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.color.inverseSurface
import ru.maksonic.beresta.ui.theme.color.onPrimaryContainer
import ru.maksonic.beresta.ui.theme.color.outlineVariant
import ru.maksonic.beresta.ui.theme.color.primary
import ru.maksonic.beresta.ui.theme.color.primaryContainer
import ru.maksonic.beresta.ui.theme.color.secondary
import ru.maksonic.beresta.ui.theme.color.tertiary
import ru.maksonic.beresta.ui.theme.component.Shape
import ru.maksonic.beresta.ui.theme.component.TextDesign
import ru.maksonic.beresta.ui.theme.component.dp12
import ru.maksonic.beresta.ui.theme.component.dp16
import ru.maksonic.beresta.ui.theme.component.dp24
import ru.maksonic.beresta.ui.theme.component.dp4
import ru.maksonic.beresta.ui.theme.component.dp6
import ru.maksonic.beresta.ui.theme.component.dp8
import ru.maksonic.beresta.ui.theme.icons.AppIcon
import ru.maksonic.beresta.ui.theme.icons.Pin
import ru.maksonic.beresta.ui.widget.button.BoxWithScaleInOutOnClick
import ru.maksonic.beresta.ui.widget.functional.animation.AnimateFadeInOut

/**
 * @Author maksonic on 24.04.2023
 */
private const val MAX_TITLE_LENGTH = 100
private const val MAX_MESSAGE_LENGTH = 200

@Composable
internal fun NoteListItemContent(
    note: NoteUi,
    isSelected: Boolean,
    onNoteClicked: (id: Long) -> Unit,
    onNoteLongClicked: (id: Long) -> Unit,
    isEnabledClick: Boolean = true,
    isTrashPlacement: Boolean = false,
    currentAppLang: AppLanguage,
    formatter: DateFormatter? = null,
    modifier: Modifier = Modifier
) {
    val isFocusedItem = rememberSaveable { mutableStateOf(false) }
    val isSelectedColors = if (isFocusedItem.value) tertiary else secondary
    val colors = if (isFocusedItem.value) outlineVariant else primaryContainer
    val backgroundColor =
        animateColorAsState(
            if (isSelected) isSelectedColors else colors,
            label = ""
        )

    BoxWithScaleInOutOnClick(
        isEnabled = isEnabledClick,
        onClick = { onNoteClicked(note.id) },
        onLongClick = { onNoteLongClicked(note.id) },
        backgroundColor = backgroundColor,
        shape = Shape.cornerBig,
        modifier = modifier
            .padding(bottom = dp12, start = dp6, end = dp6)
            .onFocusChanged { isFocusedItem.value = it.isFocused }
    ) {
        Column(
            modifier
                .fillMaxWidth()
                .padding(start = dp16)
        ) {
            TopPanelIndication(isPinned = { note.isPinned })
            CardContent(
                note = note,
                maxTitleLength = MAX_TITLE_LENGTH,
                maxMessageLength = MAX_MESSAGE_LENGTH,
                isTrashPlacement = isTrashPlacement,
                currentAppLanguage = currentAppLang,
                formatter = formatter
            )
        }
    }
}

@Composable
private fun CardContent(
    modifier: Modifier = Modifier,
    note: NoteUi,
    maxTitleLength: Int,
    maxMessageLength: Int,
    isPlaceholder: Boolean = false,
    isTrashPlacement: Boolean = false,
    currentAppLanguage: AppLanguage,
    formatter: DateFormatter? = null,
) {
    val dateCreation = rememberUpdatedState(
        formatter?.fetchFormattedUiDate(
            note.dateCreationRaw,
            currentAppLanguage
        )
    )

    val isTrashItem = isTrashPlacement && formatter != null && note.dateMovedToTrashRaw != null
    val hintPrefixRemovedDate = text.trash.hintRemovedDatePrefix
    val removeDate = if (isTrashItem) rememberUpdatedState(
        formatter!!.fetchFormattedUiDate(
            note.dateMovedToTrashRaw!!,
            currentAppLanguage
        )
    ) else remember { mutableStateOf("") }

    val date = when {
        isPlaceholder -> remember { mutableStateOf("") }
        isTrashPlacement -> remember { mutableStateOf("$hintPrefixRemovedDate ${removeDate.value}") }
        else -> dateCreation
    }

    Text(
        text = note.title.take(maxTitleLength),
        style = TextDesign.title.copy(color = onPrimaryContainer),
        maxLines = 1,
        softWrap = false,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.padding(end = dp8)
    )
    Text(
        text = note.message.take(maxMessageLength),
        style = TextDesign.bodyPrimary.copy(color = onPrimaryContainer),
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier.padding(top = dp8, end = dp8)
    )
    Text(
        text = date.value ?: "",
        style = TextDesign.captionSmall.copy(color = inverseSurface),
        modifier = modifier.padding(top = dp16, bottom = dp8)
    )
}

@Composable
private fun TopPanelIndication(isPinned: () -> Boolean, modifier: Modifier = Modifier) {

    Row(
        modifier
            .height(dp24)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimateFadeInOut(
            visible = isPinned()
        ) {
            Icon(
                imageVector = AppIcon.Pin,
                modifier = modifier
                    .padding(end = dp4)
                    .size(dp16),
                tint = primary,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun BottomPanelIndication(modifier: Modifier = Modifier) {
    Row(
        modifier
            .height(dp16)
            .fillMaxWidth()
    ) {

    }
}

@Composable
internal fun NoteItemPlaceholder(
    animateColor: Animatable<Color, AnimationVector4D>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(start = dp6, end = dp6, bottom = dp12)
            .clip(Shape.cornerBig)
            .drawBehind { drawRect(animateColor.value) }
    ) {
        TopPanelIndication(isPinned = { false })
        CardContent(
            note = NoteUi.Preview,
            maxTitleLength = 0,
            maxMessageLength = 0,
            currentAppLanguage = AppLanguage.ENGLISH,
            isPlaceholder = true,
        )
    }
}

@Preview
@Composable
private fun NoteItemPreview() {
    BerestaTheme {
        NoteListItemContent(
            note = NoteUi.Preview,
            isSelected = false,
            {},
            {},
            currentAppLang = AppLanguage.ENGLISH
        )
    }
}