package ru.maksonic.beresta.ui.widget.functional.animation

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.OverscrollConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * @Author maksonic on 15.11.2022
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverscrollBehavior(content: @Composable () -> Unit) {
    val enabledOverscroll = compositionLocalOf<OverscrollConfiguration?> {
        OverscrollConfiguration()
    }
    val overScroll = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) null else enabledOverscroll

    CompositionLocalProvider(LocalOverscrollConfiguration provides overScroll?.current) {
        content()
    }
}