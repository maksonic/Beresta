package ru.maksonic.beresta.ui.theme.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.maksonic.beresta.ui.theme.BerestaTheme
import ru.maksonic.beresta.ui.theme.Theme

/**
 * @Author maksonic on 09.06.2023
 */
val AppImage.EmptySearch: ImageVector
    @Composable get() {

        _emptySearch = ImageVector.Builder(
            name = "EmptySearch",
            defaultWidth = 100.0.dp,
            defaultHeight = 100.0.dp,
            viewportWidth = 100.0F,
            viewportHeight = 100.0F,
        ).path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(47.49F, 55.04F)
            moveToRelative(-44.49F, 0.0F)
            arcToRelative(44.49F, 44.49F, 0.0F, true, true, 88.99F, 0.0F)
            arcToRelative(44.49F, 44.49F, 0.0F, true, true, -88.99F, 0.0F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c2),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(58.07F, 1.86F)
            curveToRelative(-0.81F, -0.03F, -1.64F, 0.06F, -2.47F, 0.28F)
            lineTo(22.44F, 11.02F)
            curveToRelative(-4.41F, 1.18F, -7.02F, 5.71F, -5.84F, 10.12F)
            lineToRelative(12.67F, 47.28F)
            curveToRelative(1.18F, 4.41F, 5.71F, 7.02F, 10.12F, 5.84F)
            lineToRelative(33.15F, -8.88F)
            curveToRelative(4.41F, -1.18F, 7.02F, -5.71F, 5.84F, -10.12F)
            lineTo(65.72F, 7.98F)
            curveTo(64.76F, 4.4F, 61.59F, 2.0F, 58.07F, 1.86F)

            moveTo(47.0F, 82.0F)
            arcTo(26.0F, 2.5F, 0.0F, false, false, 21.0F, 84.5F)
            arcTo(26.0F, 2.5F, 0.0F, false, false, 47.0F, 87.0F)
            arcTo(26.0F, 2.5F, 0.0F, false, false, 73.0F, 84.5F)
            arcTo(26.0F, 2.5F, 0.0F, false, false, 47.0F, 82.0F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c1),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(72.99F, 22.9F)
            verticalLineToRelative(39.95F)
            curveToRelative(0.0F, 4.41F, -3.58F, 7.99F, -8.0F, 7.99F)
            horizontalLineTo(30.07F)
            curveToRelative(-4.42F, 0.0F, -8.0F, -3.58F, -8.0F, -7.99F)
            verticalLineTo(13.46F)
            curveToRelative(0.0F, -4.41F, 3.58F, -7.99F, 8.0F, -7.99F)
            horizontalLineToRelative(25.46F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.s3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(72.99F, 22.9F)
            horizontalLineToRelative(-9.46F)
            curveToRelative(-4.42F, 0.0F, -8.0F, -3.58F, -8.0F, -7.99F)
            verticalLineTo(5.47F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c2.copy(alpha = 0.6f)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(31.86F, 17.22F)
            curveToRelative(-1.8F, 0.0F, -3.26F, 1.46F, -3.26F, 3.26F)
            curveToRelative(0.0F, 1.8F, 1.46F, 3.26F, 3.26F, 3.26F)
            horizontalLineToRelative(13.71F)
            curveToRelative(1.8F, 0.0F, 3.27F, -1.46F, 3.27F, -3.26F)
            curveToRelative(0.0F, -1.8F, -1.46F, -3.26F, -3.27F, -3.26F)

            moveTo(31.86F, 30.27F)
            curveToRelative(-1.8F, 0.0F, -3.26F, 1.46F, -3.26F, 3.26F)
            curveToRelative(0.0F, 1.8F, 1.46F, 3.26F, 3.26F, 3.26F)
            horizontalLineToRelative(31.34F)
            curveToRelative(1.8F, 0.0F, 3.26F, -1.46F, 3.26F, -3.26F)
            curveToRelative(0.0F, -1.8F, -1.46F, -3.26F, -3.26F, -3.26F)

            moveTo(31.86F, 43.33F)
            curveToRelative(-1.8F, 0.0F, -3.26F, 1.46F, -3.26F, 3.26F)
            curveToRelative(0.0F, 1.8F, 1.46F, 3.26F, 3.26F, 3.26F)
            horizontalLineToRelative(31.34F)
            curveToRelative(1.8F, 0.0F, 3.26F, -1.46F, 3.26F, -3.26F)
            curveToRelative(0.0F, -1.8F, -1.46F, -3.26F, -3.26F, -3.26F)

            moveTo(31.86F, 56.39F)
            curveToRelative(-1.8F, 0.0F, -3.26F, 1.46F, -3.26F, 3.26F)
            curveToRelative(0.0F, 1.8F, 1.46F, 3.26F, 3.26F, 3.26F)
            horizontalLineToRelative(31.34F)
            curveToRelative(1.8F, 0.0F, 3.26F, -1.46F, 3.26F, -3.26F)
            curveToRelative(0.0F, -1.8F, -1.46F, -3.26F, -3.26F, -3.26F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(48.55F, 45.77F)
            horizontalLineToRelative(-2.61F)
            curveToRelative(0.01F, -0.73F, 0.07F, -1.34F, 0.19F, -1.83F)
            curveToRelative(0.12F, -0.5F, 0.31F, -0.95F, 0.59F, -1.36F)
            curveToRelative(0.28F, -0.41F, 0.65F, -0.84F, 1.11F, -1.29F)
            curveToRelative(0.36F, -0.34F, 0.68F, -0.67F, 0.97F, -0.97F)
            curveToRelative(0.29F, -0.31F, 0.52F, -0.64F, 0.69F, -0.98F)
            curveToRelative(0.17F, -0.35F, 0.26F, -0.75F, 0.26F, -1.21F)
            curveToRelative(0.0F, -0.49F, -0.08F, -0.91F, -0.25F, -1.25F)
            curveToRelative(-0.16F, -0.34F, -0.41F, -0.61F, -0.73F, -0.79F)
            curveToRelative(-0.31F, -0.18F, -0.7F, -0.27F, -1.17F, -0.27F)
            curveToRelative(-0.39F, 0.0F, -0.76F, 0.08F, -1.1F, 0.23F)
            curveToRelative(-0.34F, 0.15F, -0.62F, 0.38F, -0.83F, 0.7F)
            curveToRelative(-0.21F, 0.31F, -0.32F, 0.73F, -0.34F, 1.24F)
            lineTo(42.51F, 38.0F)
            curveToRelative(0.02F, -0.98F, 0.25F, -1.81F, 0.7F, -2.48F)
            curveToRelative(0.45F, -0.67F, 1.06F, -1.18F, 1.83F, -1.51F)
            curveToRelative(0.77F, -0.34F, 1.62F, -0.5F, 2.57F, -0.5F)
            curveToRelative(1.05F, 0.0F, 1.94F, 0.18F, 2.68F, 0.54F)
            curveToRelative(0.74F, 0.35F, 1.31F, 0.87F, 1.7F, 1.55F)
            curveToRelative(0.4F, 0.67F, 0.6F, 1.48F, 0.6F, 2.44F)
            curveToRelative(0.0F, 0.69F, -0.14F, 1.31F, -0.41F, 1.88F)
            curveToRelative(-0.27F, 0.55F, -0.63F, 1.07F, -1.07F, 1.56F)
            curveToRelative(-0.44F, 0.48F, -0.91F, 0.95F, -1.42F, 1.43F)
            curveToRelative(-0.44F, 0.4F, -0.73F, 0.83F, -0.89F, 1.3F)
            curveToRelative(-0.16F, 0.46F, -0.24F, 0.99F, -0.25F, 1.58F)

            moveTo(45.71F, 49.43F)
            curveToRelative(0.0F, -0.42F, 0.14F, -0.78F, 0.43F, -1.07F)
            curveToRelative(0.29F, -0.3F, 0.68F, -0.45F, 1.18F, -0.45F)
            curveToRelative(0.5F, 0.0F, 0.89F, 0.15F, 1.18F, 0.45F)
            curveToRelative(0.29F, 0.29F, 0.43F, 0.64F, 0.43F, 1.07F)
            curveToRelative(0.0F, 0.42F, -0.14F, 0.78F, -0.43F, 1.08F)
            curveToRelative(-0.29F, 0.29F, -0.68F, 0.43F, -1.18F, 0.43F)
            curveToRelative(-0.5F, 0.0F, -0.89F, -0.14F, -1.18F, -0.43F)
            curveToRelative(-0.29F, -0.3F, -0.43F, -0.66F, -0.43F, -1.08F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.s3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveToRelative(49.05F, 46.27F)
            horizontalLineToRelative(-3.61F)
            lineToRelative(0.01F, -0.51F)
            curveToRelative(0.01F, -0.75F, 0.07F, -1.4F, 0.2F, -1.94F)
            curveToRelative(0.13F, -0.56F, 0.35F, -1.07F, 0.66F, -1.52F)
            lineToRelative(0.0F, -0.01F)
            curveToRelative(0.3F, -0.44F, 0.7F, -0.89F, 1.17F, -1.36F)
            lineToRelative(0.0F, -0.0F)
            curveToRelative(0.35F, -0.34F, 0.67F, -0.66F, 0.95F, -0.95F)
            curveToRelative(0.26F, -0.28F, 0.46F, -0.57F, 0.61F, -0.86F)
            curveToRelative(0.13F, -0.27F, 0.21F, -0.6F, 0.21F, -0.99F)
            curveToRelative(0.0F, -0.44F, -0.07F, -0.78F, -0.2F, -1.04F)
            curveToRelative(-0.12F, -0.25F, -0.29F, -0.44F, -0.52F, -0.56F)
            lineToRelative(-0.0F, -0.0F)
            curveToRelative(-0.22F, -0.12F, -0.52F, -0.2F, -0.92F, -0.2F)
            curveToRelative(-0.32F, 0.0F, -0.62F, 0.06F, -0.89F, 0.19F)
            lineToRelative(-0.01F, 0.0F)
            curveToRelative(-0.25F, 0.11F, -0.45F, 0.28F, -0.61F, 0.52F)
            lineToRelative(-0.0F, 0.0F)
            curveToRelative(-0.14F, 0.21F, -0.24F, 0.52F, -0.25F, 0.98F)
            lineTo(45.82F, 38.5F)
            lineTo(42.0F, 38.5F)
            lineToRelative(0.01F, -0.51F)
            curveToRelative(0.02F, -1.06F, 0.27F, -1.99F, 0.79F, -2.76F)
            curveToRelative(0.51F, -0.75F, 1.19F, -1.32F, 2.04F, -1.69F)
            curveTo(45.68F, 33.18F, 46.6F, 33.0F, 47.61F, 33.0F)
            curveToRelative(1.1F, 0.0F, 2.07F, 0.19F, 2.9F, 0.59F)
            curveToRelative(0.83F, 0.39F, 1.47F, 0.98F, 1.92F, 1.75F)
            curveToRelative(0.45F, 0.76F, 0.67F, 1.67F, 0.67F, 2.69F)
            curveToRelative(0.0F, 0.76F, -0.15F, 1.46F, -0.46F, 2.09F)
            lineToRelative(-0.0F, 0.0F)
            curveToRelative(-0.29F, 0.6F, -0.68F, 1.16F, -1.14F, 1.67F)
            lineToRelative(-0.0F, 0.0F)
            curveToRelative(-0.45F, 0.49F, -0.93F, 0.97F, -1.44F, 1.46F)
            lineToRelative(-0.01F, 0.01F)
            curveToRelative(-0.39F, 0.35F, -0.63F, 0.72F, -0.75F, 1.09F)
            lineToRelative(-0.0F, 0.0F)
            curveToRelative(-0.14F, 0.4F, -0.21F, 0.88F, -0.22F, 1.43F)

            moveTo(51.99F, 35.59F)
            curveTo(51.6F, 34.91F, 51.03F, 34.39F, 50.29F, 34.04F)
            curveTo(49.55F, 33.68F, 48.65F, 33.5F, 47.61F, 33.5F)
            curveToRelative(-0.95F, 0.0F, -1.8F, 0.17F, -2.57F, 0.5F)
            curveToRelative(-0.77F, 0.34F, -1.38F, 0.84F, -1.83F, 1.51F)
            curveToRelative(-0.37F, 0.55F, -0.6F, 1.22F, -0.68F, 1.98F)
            curveToRelative(-0.02F, 0.16F, -0.03F, 0.33F, -0.03F, 0.5F)
            horizontalLineToRelative(2.82F)
            curveToRelative(0.02F, -0.52F, 0.13F, -0.93F, 0.34F, -1.24F)
            curveToRelative(0.21F, -0.32F, 0.49F, -0.55F, 0.83F, -0.7F)
            curveToRelative(0.34F, -0.16F, 0.71F, -0.23F, 1.1F, -0.23F)
            curveToRelative(0.47F, 0.0F, 0.86F, 0.09F, 1.17F, 0.27F)
            curveToRelative(0.32F, 0.18F, 0.56F, 0.44F, 0.73F, 0.79F)
            curveToRelative(0.16F, 0.34F, 0.25F, 0.76F, 0.25F, 1.25F)
            curveToRelative(0.0F, 0.45F, -0.09F, 0.86F, -0.26F, 1.21F)
            curveToRelative(-0.17F, 0.34F, -0.4F, 0.67F, -0.69F, 0.98F)
            curveToRelative(-0.29F, 0.3F, -0.61F, 0.63F, -0.97F, 0.97F)
            curveToRelative(-0.46F, 0.45F, -0.83F, 0.88F, -1.11F, 1.29F)
            curveToRelative(-0.27F, 0.41F, -0.47F, 0.86F, -0.59F, 1.36F)
            curveToRelative(-0.09F, 0.37F, -0.15F, 0.82F, -0.17F, 1.33F)
            curveToRelative(-0.01F, 0.16F, -0.01F, 0.33F, -0.01F, 0.5F)
            horizontalLineToRelative(2.61F)
            curveToRelative(0.01F, -0.59F, 0.09F, -1.12F, 0.25F, -1.58F)
            curveToRelative(0.16F, -0.47F, 0.45F, -0.9F, 0.89F, -1.3F)
            curveToRelative(0.51F, -0.48F, 0.98F, -0.95F, 1.42F, -1.43F)
            curveToRelative(0.44F, -0.48F, 0.79F, -1.0F, 1.07F, -1.56F)
            curveToRelative(0.27F, -0.56F, 0.41F, -1.19F, 0.41F, -1.88F)
            curveToRelative(0.0F, -0.95F, -0.2F, -1.77F, -0.6F, -2.44F)

            moveTo(48.86F, 50.86F)
            curveToRelative(-0.4F, 0.4F, -0.94F, 0.58F, -1.54F, 0.58F)
            curveToRelative(-0.6F, 0.0F, -1.13F, -0.18F, -1.54F, -0.58F)
            lineToRelative(-0.0F, -0.0F)
            curveToRelative(-0.38F, -0.39F, -0.58F, -0.88F, -0.58F, -1.43F)
            curveToRelative(0.0F, -0.55F, 0.19F, -1.03F, 0.58F, -1.42F)
            curveToRelative(0.4F, -0.41F, 0.94F, -0.59F, 1.54F, -0.59F)
            curveToRelative(0.6F, 0.0F, 1.14F, 0.18F, 1.54F, 0.59F)
            curveToRelative(0.38F, 0.39F, 0.58F, 0.87F, 0.58F, 1.42F)
            curveToRelative(0.0F, 0.55F, -0.19F, 1.03F, -0.58F, 1.43F)

            moveTo(46.14F, 48.36F)
            curveToRelative(-0.29F, 0.29F, -0.43F, 0.64F, -0.43F, 1.07F)
            curveToRelative(0.0F, 0.42F, 0.14F, 0.78F, 0.43F, 1.08F)
            curveToRelative(0.29F, 0.29F, 0.68F, 0.43F, 1.18F, 0.43F)
            curveToRelative(0.5F, 0.0F, 0.89F, -0.14F, 1.18F, -0.43F)
            curveToRelative(0.29F, -0.3F, 0.43F, -0.66F, 0.43F, -1.08F)
            curveToRelative(0.0F, -0.42F, -0.14F, -0.78F, -0.43F, -1.07F)
            curveToRelative(-0.29F, -0.3F, -0.68F, -0.45F, -1.18F, -0.45F)
            curveToRelative(-0.5F, 0.0F, -0.89F, 0.15F, -1.18F, 0.45F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(60.19F, 55.97F)
            lineTo(61.52F, 54.64F)
            lineTo(64.18F, 57.31F)
            lineTo(62.85F, 58.64F)
            lineTo(60.19F, 55.97F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.s3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(58.19F, 57.97F)
            curveTo(57.09F, 56.87F, 57.09F, 55.08F, 58.19F, 53.98F)
            lineTo(59.52F, 52.65F)
            curveTo(60.62F, 51.54F, 62.41F, 51.54F, 63.51F, 52.65F)
            lineTo(66.18F, 55.31F)
            curveTo(67.28F, 56.41F, 67.28F, 58.2F, 66.18F, 59.3F)
            lineTo(64.84F, 60.63F)
            curveTo(63.74F, 61.74F, 61.95F, 61.74F, 60.85F, 60.63F)
            lineTo(58.19F, 57.97F)

            moveTo(60.19F, 55.97F)
            lineTo(61.52F, 54.64F)
            lineTo(64.18F, 57.31F)
            lineTo(62.85F, 58.64F)
            lineTo(60.19F, 55.97F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(64.84F, 61.96F)
            lineTo(67.51F, 59.3F)
            lineTo(82.15F, 73.94F)
            lineTo(79.49F, 76.61F)
            lineTo(64.84F, 61.96F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.s3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(62.85F, 63.96F)
            curveTo(61.75F, 62.86F, 61.75F, 61.07F, 62.85F, 59.97F)
            lineTo(65.51F, 57.31F)
            curveTo(66.61F, 56.2F, 68.4F, 56.2F, 69.5F, 57.31F)
            lineTo(84.15F, 71.95F)
            curveTo(85.25F, 73.05F, 85.25F, 74.84F, 84.15F, 75.94F)
            lineTo(81.48F, 78.6F)
            curveTo(80.38F, 79.71F, 78.59F, 79.71F, 77.49F, 78.6F)
            lineTo(62.85F, 63.96F)

            moveTo(64.84F, 61.96F)
            lineTo(79.49F, 76.61F)
            lineTo(82.15F, 73.94F)
            lineTo(67.51F, 59.3F)
            lineTo(64.84F, 61.96F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(58.19F, 52.65F)
            curveTo(64.07F, 46.77F, 64.07F, 37.23F, 58.19F, 31.35F)
            curveTo(52.31F, 25.47F, 42.77F, 25.47F, 36.89F, 31.35F)
            curveTo(31.01F, 37.23F, 31.01F, 46.77F, 36.89F, 52.65F)
            curveTo(42.77F, 58.53F, 52.31F, 58.53F, 58.19F, 52.65F)

            moveTo(60.85F, 55.31F)
            curveTo(68.2F, 47.96F, 68.2F, 36.04F, 60.85F, 28.69F)
            curveTo(53.5F, 21.33F, 41.58F, 21.33F, 34.23F, 28.69F)
            curveTo(26.88F, 36.04F, 26.88F, 47.96F, 34.23F, 55.31F)
            curveTo(41.58F, 62.66F, 53.5F, 62.66F, 60.85F, 55.31F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.s3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(56.86F, 51.32F)
            curveTo(62.0F, 46.17F, 62.0F, 37.83F, 56.86F, 32.68F)
            curveTo(51.71F, 27.53F, 43.37F, 27.53F, 38.22F, 32.68F)
            curveTo(33.08F, 37.83F, 33.08F, 46.17F, 38.22F, 51.32F)
            curveTo(43.37F, 56.46F, 51.71F, 56.46F, 56.86F, 51.32F)

            moveTo(62.18F, 27.35F)
            curveTo(70.27F, 35.44F, 70.27F, 48.55F, 62.18F, 56.64F)
            curveTo(54.1F, 64.73F, 40.98F, 64.73F, 32.9F, 56.64F)
            curveTo(24.81F, 48.55F, 24.81F, 35.44F, 32.9F, 27.35F)
            curveTo(40.98F, 19.27F, 54.1F, 19.27F, 62.18F, 27.35F)

            moveTo(58.19F, 31.35F)
            curveTo(64.07F, 37.23F, 64.07F, 46.77F, 58.19F, 52.65F)
            curveTo(52.31F, 58.53F, 42.77F, 58.53F, 36.89F, 52.65F)
            curveTo(31.01F, 46.77F, 31.01F, 37.23F, 36.89F, 31.35F)
            curveTo(42.77F, 25.47F, 52.31F, 25.47F, 58.19F, 31.35F)

            moveTo(60.85F, 28.69F)
            curveTo(68.2F, 36.04F, 68.2F, 47.96F, 60.85F, 55.31F)
            curveTo(53.5F, 62.66F, 41.58F, 62.66F, 34.23F, 55.31F)
            curveTo(26.88F, 47.96F, 26.88F, 36.04F, 34.23F, 28.69F)
            curveTo(41.58F, 21.33F, 53.5F, 21.33F, 60.85F, 28.69F)
            close()
        }.build()
        return _emptySearch!!
    }
private var _emptySearch: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconEmptySearchPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.EmptySearch, contentDescription = null)
    }
}