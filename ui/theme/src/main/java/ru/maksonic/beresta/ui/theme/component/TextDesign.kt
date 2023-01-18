package ru.maksonic.beresta.ui.theme.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 08.11.2022
 */
object TextDesign {
    val header: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Theme.color.onPrimaryContainer
        )
    val title: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Theme.color.onPrimaryContainer
        )
    val topBar: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Theme.color.onPrimaryContainer
        )

    val body: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Theme.color.onPrimaryContainer
        )

    val caption: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Theme.color.onPrimaryContainer
        )
}