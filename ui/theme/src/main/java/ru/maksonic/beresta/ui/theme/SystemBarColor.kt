package ru.maksonic.beresta.ui.theme

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @Author maksonic on 24.11.2022
 */
interface SystemBarColor {
    fun setStatusBarBackground(color: Color)
    fun getStatusBarBackgroundColor(): StateFlow<Color>
    fun setNavigationBarBackground(color: Color)
    fun getNavigationBarBackgroundColor(): StateFlow<Color>

    class Core(statusBarColor: Color, navBarColor: Color) : SystemBarColor {
        private val mutableStatusColor: MutableStateFlow<Color> = MutableStateFlow(statusBarColor)
        private val statusBarColor = mutableStatusColor.asStateFlow()
        private val mutableNavBarColor: MutableStateFlow<Color> = MutableStateFlow(navBarColor)
        private val systemNavBarColor = mutableNavBarColor.asStateFlow()

        override fun setStatusBarBackground(color: Color) {
            mutableStatusColor.value = color
        }

        override fun getStatusBarBackgroundColor(): StateFlow<Color> = statusBarColor

        override fun setNavigationBarBackground(color: Color) {
            mutableNavBarColor.value = color
        }

        override fun getNavigationBarBackgroundColor(): StateFlow<Color> = systemNavBarColor
    }
}