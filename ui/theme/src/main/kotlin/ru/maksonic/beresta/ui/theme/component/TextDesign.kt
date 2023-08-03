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
    val displaySmall: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.Normal,
            color = Theme.color.onBackground
        )
    val header: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Theme.color.onBackground
        )
    val title: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Theme.color.onBackground
        )
    val topBar: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Theme.color.onBackground
        )

    val bodyPrimary: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Theme.color.onBackground
        )
    val bodyPrimaryMedium: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Theme.color.onBackground
        )

    val bodySecondary: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Theme.color.onBackground
        )

    val main: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 22.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 28.0.sp,
            color = Theme.color.onBackground
        )

    val captionSmall: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Theme.color.onBackground
        )
    val captionNormal: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Theme.color.onBackground
        )

    val captionRegular: TextStyle
        @Composable
        get() = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Theme.color.onBackground
        )
}