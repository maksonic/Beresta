package ru.maksonic.beresta.feature.wallpaper_picker.ui.core

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.maksonic.beresta.feature.wallpaper_picker.domain.BackgroundColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.TintColor
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpaperRepository
import ru.maksonic.beresta.feature.wallpaper_picker.domain.WallpapersDataContainer
import ru.maksonic.beresta.feature.wallpaper_picker.domain.helpers.TextureStyle

/**
 * @Author maksonic on 30.10.2023
 */

@Stable
@Immutable
data class WallpapersUiState(
    val wallpapers: WallpapersDataContainer<Color>,
    val styles: TextureStyle<Color>,
    val tints: List<TintColor<Color>>,
    val backgrounds: List<BackgroundColor<Color>>,
) {
    companion object {
        val Initial = WallpapersUiState(
            WallpapersDataContainer.empty(),
            emptyList(),
            emptyList(),
            emptyList()
        )
    }
}

class WallpaperPickerViewModel(
    repository: WallpaperRepository<Color>
) : ViewModel() {
    private val _state = MutableStateFlow(WallpapersUiState.Initial)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = WallpapersUiState(
                wallpapers = repository.fetchAllWallpapers(),
                styles = repository.fetchTexturesReadyStyles(),
                tints = repository.fetchTexturesTintColors(),
                backgrounds = repository.fetchTexturesBackgroundColors()
            )
        }
    }
}