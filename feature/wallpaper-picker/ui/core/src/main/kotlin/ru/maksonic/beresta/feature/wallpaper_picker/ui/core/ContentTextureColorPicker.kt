package ru.maksonic.beresta.feature.wallpaper_picker.ui.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import ru.maksonic.beresta.common.ui_kit.button.dialog.ButtonDialogPrimary
import ru.maksonic.beresta.common.ui_kit.button.dialog.ButtonDialogSecondary
import ru.maksonic.beresta.common.ui_kit.widget.marker_color_picker.SelectableCircleItem
import ru.maksonic.beresta.common.ui_theme.colors.onSecondaryContainer
import ru.maksonic.beresta.common.ui_theme.provide.dp16
import ru.maksonic.beresta.common.ui_theme.provide.dp24
import ru.maksonic.beresta.common.ui_theme.provide.dp8
import ru.maksonic.beresta.common.ui_theme.typography.TextDesign
import ru.maksonic.beresta.feature.wallpaper_picker.domain.helpers.TextureStyle
import ru.maksonic.beresta.feature.wallpaper_picker.domain.wallpaper.WallpaperTexture
import ru.maksonic.beresta.feature.wallpaper_picker.ui.api.helpers.findById
import ru.maksonic.beresta.language_engine.shell.provider.text

/**
 * @Author maksonic on 30.10.2023
 */
@Composable
internal fun ContentTextureColorPicker(
    state: WallpapersUiState,
    texture: WallpaperTexture<Color>?,
    updateWallpaper: (WallpaperTexture<Color>) -> Unit,
    onAcceptClicked: () -> Unit,
    onBack: () -> Unit,
) {
    if (texture != null) {
        TextureSettingsContent(
            state = state,
            texture = texture,
            updateWallpaper = updateWallpaper,
            onAcceptClicked = onAcceptClicked,
        )
    } else {
        UnselectedTextureContent(onBack)
    }
}

@Composable
private fun TextureSettingsContent(
    state: WallpapersUiState,
    texture: WallpaperTexture<Color>,
    updateWallpaper: (WallpaperTexture<Color>) -> Unit,
    onAcceptClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .padding(bottom = dp24)
    ) {
        val tintRowState = rememberLazyListState()
        val backgroundColorRowState = rememberLazyListState()
        val scope = rememberCoroutineScope()

        LaunchedEffect(Unit) {
            val tIndex = with(state.tints) { indexOf(findById(texture.tintColor.id)) }
            val bIndex = with(state.backgrounds) { indexOf(findById(texture.backgroundColor.id)) }

            scope.launch {
                if (tIndex >= 0) tintRowState.animateScrollToItem(tIndex)
            }
            scope.launch {
                if (bIndex >= 0) backgroundColorRowState.animateScrollToItem(bIndex)
            }
        }

        TextureStyleScrollableItem(
            readyTextureStyles = state.styles,
            texture = texture,
            updateWallpaper = updateWallpaper,
            scrollState = rememberLazyListState(),
            modifier = modifier.weight(1f)
        )

        HorizontalDivider(
            color = onSecondaryContainer,
            modifier = Modifier.padding(top = dp16, bottom = dp8)
        )

        TextureScrollableItem(
            state = state,
            scrollState = tintRowState,
            currentSelectedColorId = texture.tintColor.id,
            title = text.editor.noteWallpaperCategoryColor,
            modifier = modifier.weight(1f),
            onClicked = { tintId ->
                updateWallpaper(
                    texture.copy(
                        tintColor = texture.tintColor.copy(tintId), isTextureStyle = false
                    )
                )
            }
        )

        TextureScrollableItem(
            isTintColors = false,
            state = state,
            scrollState = backgroundColorRowState,
            currentSelectedColorId = texture.backgroundColor.id,
            title = text.editor.hintWallpaperPickerBackground,
            modifier = modifier.weight(1f),
            onClicked = { backgroundId ->
                updateWallpaper(
                    texture.copy(
                        backgroundColor = texture.backgroundColor.copy(backgroundId),
                        isTextureStyle = false
                    )
                )
            }
        )

        Row(
            modifier
                .fillMaxWidth()
                .padding(top = dp24, start = dp16, end = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16)
        ) {
            ButtonDialogSecondary(
                title = text.shared.btnTitleByDefault,
                onClick = {
                    updateWallpaper(
                        texture.copy(
                            tintColor = texture.tintColor.copy(0L),
                            backgroundColor = texture.backgroundColor.copy(0)
                        )
                    ).run {
                        scope.launch {
                            tintRowState.animateScrollToItem(0)
                            backgroundColorRowState.animateScrollToItem(0)
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            )
            ButtonDialogPrimary(
                title = text.shared.btnTitleAccept,
                onClick = onAcceptClicked,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun UnselectedTextureContent(onBack: () -> Unit) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ButtonDialogPrimary(title = text.editor.dialogTitleWallpaperPicker, onClick = onBack)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColorsOpacityTextureContent(onBack: () -> Unit, modifier: Modifier = Modifier) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val state = SliderState()
        Slider(state, modifier = modifier.fillMaxWidth())
    }
}

@Composable
private fun TextureScrollableItem(
    isTintColors: Boolean = true,
    state: WallpapersUiState,
    scrollState: LazyListState,
    currentSelectedColorId: Long,
    title: String,
    modifier: Modifier,
    onClicked: (Long) -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = TextDesign.labelMedium,
            modifier = Modifier.padding(start = dp16, top = dp16, bottom = dp16)
        )

        LazyRow(
            state = scrollState,
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = dp16, end = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16)
        ) {
            if (isTintColors) {
                items(state.tints) { color ->
                    SelectableCircleItem(
                        primaryColor = color.value,
                        isSelected = color.id == currentSelectedColorId,
                        onClick = { onClicked(color.id) },
                        modifier = modifier
                    )
                }
            } else {
                items(state.backgrounds) { color ->
                    SelectableCircleItem(
                        primaryColor = color.value,
                        isSelected = color.id == currentSelectedColorId,
                        onClick = { onClicked(color.id) },
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
private fun TextureStyleScrollableItem(
    scrollState: LazyListState,
    readyTextureStyles: TextureStyle<Color>,
    texture: WallpaperTexture<Color>,
    updateWallpaper: (WallpaperTexture<Color>) -> Unit,
    modifier: Modifier,
) {
    Column(modifier) {
        Text(
            text = text.editor.hintWallpaperPickerStyle,
            style = TextDesign.labelMedium,
            modifier = Modifier.padding(start = dp16, top = dp16, bottom = dp16)
        )

        LazyRow(
            state = scrollState,
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = dp16, end = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16)
        ) {
            items(readyTextureStyles) { style ->
                /**
                 * [style.first.id] -> Texture tint ID.
                 * [style.first.value] -> Texture tint COLOR.
                 * [style.second.id] -> Texture background ID.
                 * [style.second.value] -> Texture background ID.
                 * */
                SelectableCircleItem(
                    isSelected = style.first.id == texture.tintColor.id &&
                            style.second.id == texture.backgroundColor.id,
                    isVisibleSecondColor = true,
                    primaryColor = style.second.value,
                    secondaryColor = style.first.value,
                    onClick = {
                        updateWallpaper(
                            texture.copy(
                                tintColor = style.first,
                                backgroundColor = style.second,
                                isTextureStyle = true
                            )
                        )
                    }
                )
            }
        }
    }
}