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
 * @Author maksonic on 02.06.2023
 */
val AppImage.TrashedNotesList: ImageVector
    @Composable get() {
        _trashedNotesList = ImageVector.Builder(
            name = "TrashedNotesList",
            defaultWidth = 100.0.dp,
            defaultHeight = 100.0.dp,
            viewportWidth = 100.0F,
            viewportHeight = 100.0F,
        ).path(
            fill = SolidColor(Theme.color.error),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(54.06F, 84.01F)
            curveToRelative(-8.12F, 0.0F, -15.54F, 0.47F, -22.07F, 1.21F)
            curveToRelative(-1.16F, -0.07F, -2.23F, -0.11F, -3.08F, -0.11F)
            curveToRelative(-4.28F, 0.0F, -8.13F, 0.91F, -10.85F, 2.35F)
            curveTo(9.19F, 89.42F, 4.0F, 91.82F, 4.0F, 93.59F)
            curveToRelative(0.0F, 1.63F, 4.47F, 2.44F, 12.14F, 2.83F)
            curveToRelative(2.61F, 2.16F, 7.35F, 3.6F, 12.77F, 3.6F)
            curveToRelative(5.1F, 0.0F, 16.52F, -1.28F, 24.5F, -3.23F)
            curveToRelative(0.24F, -0.0F, 0.42F, 0.0F, 0.65F, 0.0F)
            curveToRelative(29.42F, 0.0F, 42.61F, -6.06F, 42.61F, -9.59F)
            curveToRelative(0.0F, -3.53F, -13.19F, -3.2F, -42.61F, -3.2F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.t3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(59.63F, 37.96F)
            curveToRelative(-13.98F, 0.0F, -25.31F, 1.79F, -25.31F, 4.0F)
            curveToRelative(0.0F, 2.21F, 11.33F, 4.0F, 25.31F, 4.0F)
            curveToRelative(13.98F, 0.0F, 25.31F, -1.79F, 25.31F, -4.0F)
            curveToRelative(0.0F, -2.21F, -11.33F, -4.0F, -25.31F, -4.0F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(73.78F, 40.0F)
            lineToRelative(-7.91F, 13.56F)
            curveToRelative(-0.87F, 1.5F, -2.8F, 2.0F, -4.3F, 1.13F)
            lineTo(49.72F, 47.78F)
            curveToRelative(-1.5F, -0.88F, -2.01F, -2.8F, -1.13F, -4.3F)
            lineToRelative(9.78F, -16.77F)
            curveToRelative(0.87F, -1.5F, 2.8F, -2.0F, 4.3F, -1.13F)
            lineToRelative(8.64F, 5.04F)
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
            moveToRelative(64.29F, 38.0F)
            curveToRelative(-0.45F, -0.01F, -0.9F, 0.21F, -1.15F, 0.63F)
            curveToRelative(-0.36F, 0.61F, -0.15F, 1.4F, 0.46F, 1.76F)
            lineToRelative(3.99F, 2.33F)
            curveToRelative(0.61F, 0.36F, 1.4F, 0.15F, 1.75F, -0.46F)
            curveTo(69.7F, 41.64F, 69.5F, 40.86F, 68.88F, 40.5F)
            lineTo(64.89F, 38.17F)
            curveTo(64.7F, 38.06F, 64.5F, 38.01F, 64.29F, 38.0F)

            moveTo(55.16F, 38.62F)
            curveToRelative(-0.45F, -0.01F, -0.9F, 0.22F, -1.15F, 0.64F)
            curveToRelative(-0.36F, 0.61F, -0.15F, 1.4F, 0.46F, 1.75F)
            lineToRelative(4.43F, 2.59F)
            curveToRelative(0.61F, 0.36F, 1.4F, 0.15F, 1.75F, -0.46F)
            curveToRelative(0.36F, -0.61F, 0.15F, -1.4F, -0.46F, -1.75F)
            lineToRelative(-4.43F, -2.59F)
            curveToRelative(-0.19F, -0.11F, -0.4F, -0.17F, -0.61F, -0.17F)

            moveTo(64.32F, 42.41F)
            curveToRelative(-0.67F, -0.02F, -1.34F, 0.32F, -1.7F, 0.94F)
            lineToRelative(-0.8F, 1.37F)
            curveToRelative(-0.53F, 0.91F, -0.22F, 2.07F, 0.68F, 2.6F)
            lineToRelative(1.37F, 0.8F)
            curveToRelative(0.91F, 0.53F, 2.07F, 0.22F, 2.6F, -0.68F)
            lineToRelative(0.8F, -1.37F)
            curveToRelative(0.53F, -0.91F, 0.22F, -2.07F, -0.68F, -2.6F)
            lineToRelative(-1.37F, -0.8F)
            curveToRelative(-0.28F, -0.17F, -0.59F, -0.25F, -0.9F, -0.26F)

            moveTo(52.58F, 43.05F)
            curveToRelative(-0.45F, -0.01F, -0.9F, 0.22F, -1.15F, 0.64F)
            curveToRelative(-0.36F, 0.61F, -0.15F, 1.4F, 0.46F, 1.75F)
            lineToRelative(10.64F, 6.2F)
            curveToRelative(0.61F, 0.36F, 1.4F, 0.15F, 1.75F, -0.46F)
            curveToRelative(0.36F, -0.61F, 0.15F, -1.4F, -0.46F, -1.76F)
            lineTo(53.19F, 43.23F)
            curveToRelative(-0.19F, -0.11F, -0.4F, -0.17F, -0.61F, -0.17F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c4),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(73.78F, 40.0F)
            lineToRelative(-3.21F, -1.87F)
            curveToRelative(-1.5F, -0.88F, -2.01F, -2.8F, -1.13F, -4.3F)
            lineToRelative(1.87F, -3.21F)
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
            moveToRelative(60.06F, 36.61F)
            curveToRelative(1.85F, 1.08F, 4.23F, 0.46F, 5.31F, -1.39F)
            curveToRelative(1.08F, -1.85F, 0.45F, -4.23F, -1.4F, -5.31F)
            curveToRelative(-1.85F, -1.08F, -4.23F, -0.46F, -5.31F, 1.39F)
            curveToRelative(-1.08F, 1.85F, -0.45F, 4.23F, 1.4F, 5.31F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(60.75F, 30.45F)
            curveToRelative(-0.01F, 0.0F, -0.03F, 0.0F, -0.04F, 0.01F)
            curveToRelative(-0.12F, 0.03F, -0.18F, 0.15F, -0.15F, 0.27F)
            lineToRelative(0.19F, 0.72F)
            lineToRelative(-0.72F, 0.19F)
            curveToRelative(-0.12F, 0.03F, -0.18F, 0.15F, -0.15F, 0.26F)
            curveToRelative(0.03F, 0.12F, 0.15F, 0.18F, 0.26F, 0.15F)
            lineToRelative(0.72F, -0.19F)
            lineToRelative(0.19F, 0.72F)
            curveToRelative(0.03F, 0.12F, 0.15F, 0.18F, 0.27F, 0.15F)
            curveToRelative(0.12F, -0.03F, 0.18F, -0.15F, 0.15F, -0.27F)
            lineTo(61.27F, 31.75F)
            lineTo(61.99F, 31.56F)
            curveToRelative(0.12F, -0.03F, 0.18F, -0.15F, 0.15F, -0.26F)
            curveToRelative(-0.03F, -0.12F, -0.15F, -0.18F, -0.26F, -0.15F)
            lineToRelative(-0.72F, 0.19F)
            lineToRelative(-0.19F, -0.72F)
            curveToRelative(-0.03F, -0.1F, -0.12F, -0.17F, -0.22F, -0.16F)

            moveTo(63.63F, 32.13F)
            curveToRelative(-0.01F, 0.0F, -0.03F, 0.0F, -0.04F, 0.01F)
            curveToRelative(-0.12F, 0.03F, -0.18F, 0.15F, -0.15F, 0.26F)
            lineToRelative(0.19F, 0.72F)
            lineToRelative(-0.72F, 0.19F)
            curveToRelative(-0.12F, 0.03F, -0.18F, 0.15F, -0.15F, 0.26F)
            curveToRelative(0.03F, 0.12F, 0.15F, 0.18F, 0.26F, 0.15F)
            lineToRelative(0.72F, -0.19F)
            lineToRelative(0.19F, 0.72F)
            curveToRelative(0.03F, 0.12F, 0.15F, 0.18F, 0.26F, 0.15F)
            curveToRelative(0.12F, -0.03F, 0.18F, -0.15F, 0.15F, -0.26F)
            lineTo(64.15F, 33.43F)
            lineTo(64.88F, 33.24F)
            curveToRelative(0.12F, -0.03F, 0.18F, -0.15F, 0.15F, -0.27F)
            curveToRelative(-0.03F, -0.12F, -0.15F, -0.18F, -0.27F, -0.15F)
            lineToRelative(-0.72F, 0.19F)
            lineToRelative(-0.19F, -0.72F)
            curveToRelative(-0.03F, -0.1F, -0.12F, -0.17F, -0.22F, -0.16F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(59.62F, 33.38F)
            arcTo(0.48F, 0.48F, 0.0F, false, false, 59.5F, 34.04F)
            curveToRelative(0.84F, 1.19F, 1.71F, 1.67F, 3.21F, 1.88F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, 0.54F, -0.4F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, -0.4F, -0.54F)
            curveToRelative(-1.39F, -0.19F, -1.81F, -0.41F, -2.56F, -1.48F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, -0.66F, -0.12F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(47.76F, 10.93F)
            lineToRelative(7.85F, 13.6F)
            curveToRelative(0.87F, 1.5F, 0.35F, 3.42F, -1.15F, 4.29F)
            lineToRelative(-11.88F, 6.86F)
            curveToRelative(-1.5F, 0.87F, -3.43F, 0.35F, -4.29F, -1.15F)
            lineTo(28.57F, 17.73F)
            curveToRelative(-0.87F, -1.5F, -0.35F, -3.42F, 1.15F, -4.29F)
            lineToRelative(8.67F, -5.0F)
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
            moveToRelative(36.15F, 13.93F)
            curveToRelative(-0.69F, -0.01F, -1.39F, 0.15F, -2.03F, 0.52F)
            curveToRelative(-1.86F, 1.07F, -2.5F, 3.45F, -1.42F, 5.3F)
            curveToRelative(1.07F, 1.86F, 3.45F, 2.49F, 5.3F, 1.42F)
            curveToRelative(1.86F, -1.07F, 2.5F, -3.45F, 1.42F, -5.3F)
            curveToRelative(-0.7F, -1.22F, -1.97F, -1.91F, -3.28F, -1.94F)

            moveTo(46.37F, 15.26F)
            curveToRelative(-0.21F, 0.01F, -0.42F, 0.06F, -0.61F, 0.17F)
            lineToRelative(-4.0F, 2.31F)
            curveToRelative(-0.61F, 0.35F, -0.83F, 1.14F, -0.47F, 1.75F)
            curveToRelative(0.35F, 0.61F, 1.14F, 0.82F, 1.75F, 0.47F)
            lineToRelative(4.0F, -2.31F)
            curveToRelative(0.61F, -0.35F, 0.82F, -1.14F, 0.47F, -1.75F)
            curveToRelative(-0.24F, -0.42F, -0.69F, -0.65F, -1.14F, -0.64F)

            moveTo(48.1F, 18.64F)
            curveToRelative(-0.31F, 0.01F, -0.61F, 0.09F, -0.9F, 0.25F)
            lineToRelative(-1.38F, 0.79F)
            curveToRelative(-0.91F, 0.52F, -1.22F, 1.69F, -0.7F, 2.6F)
            lineToRelative(0.79F, 1.37F)
            curveToRelative(0.52F, 0.91F, 1.69F, 1.22F, 2.6F, 0.7F)
            lineToRelative(1.38F, -0.79F)
            curveToRelative(0.91F, -0.52F, 1.22F, -1.69F, 0.7F, -2.6F)
            lineToRelative(-0.79F, -1.38F)
            curveToRelative(-0.36F, -0.63F, -1.02F, -0.97F, -1.7F, -0.95F)

            moveTo(42.82F, 23.23F)
            curveToRelative(-0.21F, 0.01F, -0.42F, 0.06F, -0.61F, 0.17F)
            lineToRelative(-4.44F, 2.56F)
            curveToRelative(-0.61F, 0.35F, -0.82F, 1.14F, -0.47F, 1.75F)
            curveToRelative(0.35F, 0.61F, 1.14F, 0.82F, 1.75F, 0.47F)
            lineTo(43.5F, 25.63F)
            curveToRelative(0.61F, -0.35F, 0.82F, -1.14F, 0.47F, -1.75F)
            curveToRelative(-0.24F, -0.42F, -0.69F, -0.65F, -1.14F, -0.64F)

            moveTo(51.61F, 24.08F)
            curveToRelative(-0.21F, 0.01F, -0.42F, 0.06F, -0.61F, 0.17F)
            lineToRelative(-10.66F, 6.16F)
            curveToRelative(-0.61F, 0.35F, -0.83F, 1.14F, -0.47F, 1.75F)
            curveToRelative(0.35F, 0.61F, 1.14F, 0.82F, 1.75F, 0.47F)
            lineToRelative(10.67F, -6.16F)
            curveToRelative(0.61F, -0.35F, 0.83F, -1.14F, 0.47F, -1.75F)
            curveTo(52.51F, 24.3F, 52.07F, 24.07F, 51.61F, 24.08F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c4),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(47.76F, 10.93F)
            lineToRelative(-3.22F, 1.86F)
            curveToRelative(-1.5F, 0.87F, -3.43F, 0.35F, -4.29F, -1.15F)
            lineTo(38.39F, 8.43F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(37.26F, 15.04F)
            curveToRelative(-0.09F, 0.0F, -0.18F, 0.07F, -0.2F, 0.16F)
            lineToRelative(-0.19F, 0.72F)
            lineToRelative(-0.72F, -0.19F)
            curveToRelative(-0.12F, -0.03F, -0.23F, 0.04F, -0.27F, 0.15F)
            curveToRelative(-0.03F, 0.12F, 0.04F, 0.23F, 0.15F, 0.27F)
            lineTo(36.75F, 16.34F)
            lineTo(36.55F, 17.06F)
            curveToRelative(-0.03F, 0.12F, 0.04F, 0.23F, 0.15F, 0.26F)
            curveToRelative(0.12F, 0.03F, 0.23F, -0.04F, 0.26F, -0.15F)
            lineToRelative(0.2F, -0.72F)
            lineToRelative(0.72F, 0.19F)
            curveToRelative(0.12F, 0.03F, 0.23F, -0.04F, 0.26F, -0.15F)
            curveTo(38.18F, 16.38F, 38.12F, 16.26F, 38.0F, 16.23F)
            lineTo(37.28F, 16.03F)
            lineTo(37.47F, 15.31F)
            curveToRelative(0.03F, -0.12F, -0.04F, -0.23F, -0.15F, -0.27F)
            curveToRelative(-0.02F, -0.01F, -0.04F, -0.01F, -0.06F, -0.01F)

            moveTo(34.37F, 16.71F)
            curveToRelative(-0.09F, 0.0F, -0.18F, 0.07F, -0.2F, 0.16F)
            lineToRelative(-0.19F, 0.72F)
            lineToRelative(-0.72F, -0.19F)
            curveToRelative(-0.12F, -0.03F, -0.23F, 0.04F, -0.27F, 0.15F)
            curveToRelative(-0.03F, 0.12F, 0.04F, 0.23F, 0.15F, 0.27F)
            lineToRelative(0.72F, 0.19F)
            lineToRelative(-0.2F, 0.72F)
            curveToRelative(-0.03F, 0.12F, 0.04F, 0.23F, 0.15F, 0.26F)
            curveToRelative(0.12F, 0.03F, 0.23F, -0.04F, 0.26F, -0.15F)
            lineTo(34.28F, 18.12F)
            lineTo(35.0F, 18.31F)
            curveToRelative(0.12F, 0.03F, 0.23F, -0.04F, 0.26F, -0.15F)
            curveToRelative(0.03F, -0.12F, -0.04F, -0.23F, -0.15F, -0.27F)
            lineToRelative(-0.72F, -0.19F)
            lineToRelative(0.19F, -0.72F)
            curveToRelative(0.03F, -0.12F, -0.04F, -0.23F, -0.15F, -0.26F)
            curveToRelative(-0.02F, -0.01F, -0.04F, -0.01F, -0.06F, -0.01F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(38.63F, 17.86F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, -0.67F, 0.09F)
            curveToRelative(-0.86F, 1.11F, -1.25F, 1.36F, -2.56F, 1.48F)
            arcTo(0.48F, 0.48F, 0.0F, false, false, 34.98F, 19.95F)
            arcTo(0.48F, 0.48F, 0.0F, false, false, 35.5F, 20.38F)
            curveToRelative(1.45F, -0.14F, 2.3F, -0.65F, 3.22F, -1.85F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, -0.09F, -0.67F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(91.45F, 18.16F)
            lineTo(80.15F, 29.06F)
            curveToRelative(-1.25F, 1.2F, -3.24F, 1.17F, -4.44F, -0.09F)
            lineToRelative(-9.52F, -9.88F)
            curveToRelative(-1.21F, -1.25F, -1.17F, -3.24F, 0.08F, -4.44F)
            lineTo(80.24F, 1.18F)
            curveToRelative(1.25F, -1.2F, 3.24F, -1.17F, 4.44F, 0.09F)
            lineToRelative(6.94F, 7.21F)
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
            moveToRelative(81.85F, 4.59F)
            curveToRelative(-0.93F, 0.03F, -1.85F, 0.39F, -2.58F, 1.08F)
            curveToRelative(-1.54F, 1.49F, -1.59F, 3.95F, -0.1F, 5.49F)
            curveToRelative(1.49F, 1.54F, 3.95F, 1.59F, 5.49F, 0.11F)
            curveTo(86.2F, 9.78F, 86.25F, 7.33F, 84.76F, 5.78F)
            curveTo(83.97F, 4.96F, 82.9F, 4.56F, 81.85F, 4.59F)

            moveTo(73.56F, 11.71F)
            curveToRelative(-0.33F, -0.01F, -0.66F, 0.11F, -0.91F, 0.36F)
            curveToRelative(-0.51F, 0.49F, -0.52F, 1.3F, -0.03F, 1.81F)
            lineToRelative(3.56F, 3.7F)
            curveToRelative(0.49F, 0.51F, 1.3F, 0.52F, 1.81F, 0.03F)
            curveToRelative(0.51F, -0.49F, 0.52F, -1.3F, 0.03F, -1.81F)
            lineToRelative(-3.56F, -3.7F)
            curveToRelative(-0.25F, -0.26F, -0.57F, -0.39F, -0.9F, -0.39F)

            moveTo(82.5F, 13.59F)
            curveToRelative(-0.33F, -0.01F, -0.66F, 0.11F, -0.91F, 0.36F)
            curveToRelative(-0.51F, 0.49F, -0.52F, 1.3F, -0.03F, 1.81F)
            lineToRelative(3.2F, 3.32F)
            curveToRelative(0.49F, 0.51F, 1.3F, 0.53F, 1.81F, 0.04F)
            curveToRelative(0.51F, -0.49F, 0.52F, -1.3F, 0.03F, -1.81F)
            lineToRelative(-3.2F, -3.33F)
            curveToRelative(-0.25F, -0.26F, -0.57F, -0.39F, -0.9F, -0.39F)

            moveTo(69.86F, 15.27F)
            curveToRelative(-0.33F, -0.01F, -0.66F, 0.11F, -0.91F, 0.36F)
            curveToRelative(-0.51F, 0.49F, -0.52F, 1.3F, -0.03F, 1.81F)
            lineToRelative(8.54F, 8.87F)
            curveToRelative(0.49F, 0.51F, 1.3F, 0.52F, 1.81F, 0.03F)
            curveToRelative(0.51F, -0.49F, 0.52F, -1.3F, 0.03F, -1.81F)
            lineTo(70.76F, 15.66F)
            curveToRelative(-0.25F, -0.26F, -0.57F, -0.39F, -0.9F, -0.39F)

            moveTo(81.15F, 17.82F)
            curveToRelative(-0.49F, -0.01F, -0.98F, 0.17F, -1.35F, 0.53F)
            lineToRelative(-1.14F, 1.1F)
            curveToRelative(-0.76F, 0.73F, -0.78F, 1.93F, -0.05F, 2.69F)
            lineToRelative(1.1F, 1.14F)
            curveToRelative(0.73F, 0.76F, 1.93F, 0.78F, 2.69F, 0.05F)
            lineToRelative(1.14F, -1.1F)
            curveToRelative(0.76F, -0.73F, 0.78F, -1.93F, 0.05F, -2.69F)
            lineToRelative(-1.1F, -1.14F)
            curveToRelative(-0.36F, -0.38F, -0.85F, -0.57F, -1.33F, -0.58F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c4),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(91.45F, 18.16F)
            lineToRelative(-2.58F, -2.68F)
            curveToRelative(-1.21F, -1.25F, -1.17F, -3.24F, 0.08F, -4.44F)
            lineToRelative(2.67F, -2.58F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(81.45F, 5.42F)
            curveToRelative(-0.11F, 0.01F, -0.2F, 0.1F, -0.2F, 0.21F)
            lineToRelative(-0.01F, 0.75F)
            lineToRelative(-0.75F, -0.01F)
            curveToRelative(-0.12F, -0.0F, -0.22F, 0.09F, -0.22F, 0.21F)
            curveToRelative(-0.0F, 0.12F, 0.09F, 0.22F, 0.21F, 0.22F)
            lineToRelative(0.75F, 0.01F)
            lineToRelative(-0.01F, 0.75F)
            curveToRelative(-0.0F, 0.12F, 0.09F, 0.22F, 0.21F, 0.22F)
            curveToRelative(0.12F, 0.0F, 0.22F, -0.09F, 0.22F, -0.21F)
            lineToRelative(0.01F, -0.75F)
            lineToRelative(0.75F, 0.01F)
            curveToRelative(0.12F, 0.0F, 0.22F, -0.09F, 0.22F, -0.21F)
            curveToRelative(0.0F, -0.12F, -0.09F, -0.22F, -0.21F, -0.22F)
            lineTo(81.67F, 6.39F)
            lineTo(81.69F, 5.64F)
            curveToRelative(0.0F, -0.12F, -0.09F, -0.22F, -0.21F, -0.22F)
            curveToRelative(-0.01F, -0.0F, -0.01F, -0.0F, -0.02F, 0.0F)

            moveTo(83.79F, 7.82F)
            curveToRelative(-0.12F, -0.0F, -0.22F, 0.09F, -0.22F, 0.21F)
            lineToRelative(-0.01F, 0.75F)
            lineToRelative(-0.75F, -0.01F)
            curveToRelative(-0.12F, -0.0F, -0.22F, 0.09F, -0.22F, 0.21F)
            curveToRelative(-0.0F, 0.12F, 0.09F, 0.22F, 0.21F, 0.22F)
            lineToRelative(0.75F, 0.01F)
            lineToRelative(-0.01F, 0.75F)
            curveToRelative(-0.0F, 0.12F, 0.09F, 0.22F, 0.21F, 0.22F)
            curveToRelative(0.12F, 0.0F, 0.22F, -0.09F, 0.22F, -0.21F)
            lineToRelative(0.01F, -0.75F)
            lineToRelative(0.75F, 0.01F)
            curveToRelative(0.12F, 0.0F, 0.22F, -0.09F, 0.22F, -0.21F)
            curveToRelative(0.0F, -0.12F, -0.09F, -0.22F, -0.21F, -0.22F)
            lineToRelative(-0.75F, -0.01F)
            lineToRelative(0.01F, -0.75F)
            curveToRelative(0.0F, -0.12F, -0.09F, -0.22F, -0.21F, -0.22F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(79.63F, 7.94F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, -0.29F, 0.61F)
            curveToRelative(0.48F, 1.38F, 1.19F, 2.07F, 2.57F, 2.68F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, 0.63F, -0.24F)
            arcToRelative(0.48F, 0.48F, 0.0F, false, false, -0.24F, -0.63F)
            curveTo(81.01F, 9.79F, 80.67F, 9.46F, 80.24F, 8.23F)
            arcTo(0.48F, 0.48F, 0.0F, false, false, 79.63F, 7.94F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.t1),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(34.31F, 41.96F)
            curveToRelative(0.0F, 0.0F, 7.99F, 45.29F, 8.66F, 48.63F)
            curveToRelative(0.67F, 3.33F, 7.16F, 3.41F, 16.65F, 3.33F)
            curveToRelative(9.49F, 0.08F, 15.99F, 0.0F, 16.65F, -3.33F)
            curveToRelative(0.67F, -3.33F, 8.66F, -48.63F, 8.66F, -48.63F)
            curveToRelative(-4.37F, 2.74F, -9.96F, 3.6F, -25.31F, 4.0F)
            curveTo(44.27F, 45.56F, 38.68F, 44.7F, 34.31F, 41.96F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF000000)),
            fillAlpha = 0.2F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(39.42F, 48.64F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.09F, 1.54F)
            lineToRelative(5.99F, 35.3F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.54F, 1.09F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.09F, -1.54F)
            lineTo(40.96F, 49.73F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.54F, -1.09F)

            moveTo(79.16F, 48.64F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.54F, 1.09F)
            lineToRelative(-5.99F, 35.3F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.09F, 1.54F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.54F, -1.09F)
            lineTo(80.26F, 50.18F)
            arcTo(1.33F, 1.33F, 0.0F, false, false, 79.16F, 48.64F)

            moveTo(59.63F, 49.96F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.33F, 1.33F)
            lineTo(58.29F, 87.25F)
            arcTo(1.33F, 1.33F, 0.0F, false, false, 59.63F, 88.59F)
            arcTo(1.33F, 1.33F, 0.0F, false, false, 60.96F, 87.25F)
            lineTo(60.96F, 51.29F)
            arcTo(1.33F, 1.33F, 0.0F, false, false, 59.63F, 49.96F)

            moveTo(69.72F, 49.96F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.43F, 1.23F)
            lineTo(65.63F, 86.49F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.23F, 1.43F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.43F, -1.23F)
            lineToRelative(2.66F, -35.3F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.23F, -1.43F)

            moveTo(48.84F, 49.96F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.2F, 1.45F)
            lineToRelative(3.33F, 35.3F)
            arcTo(1.33F, 1.33F, 0.0F, false, false, 52.42F, 87.91F)
            arcTo(1.33F, 1.33F, 0.0F, false, false, 53.63F, 86.46F)
            lineTo(50.29F, 51.16F)
            arcTo(1.33F, 1.33F, 0.0F, false, false, 48.84F, 49.96F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.t3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(45.0F, 51.73F)
            curveToRelative(-5.49F, -0.17F, -13.48F, 7.19F, -19.08F, 18.07F)
            curveToRelative(-6.4F, 12.43F, -7.24F, 24.74F, -1.89F, 27.49F)
            curveToRelative(5.35F, 2.75F, 14.87F, -5.09F, 21.27F, -17.52F)
            curveToRelative(6.4F, -12.43F, 7.24F, -24.74F, 1.89F, -27.49F)
            curveToRelative(-0.67F, -0.34F, -1.4F, -0.52F, -2.19F, -0.55F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.t0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(45.85F, 50.94F)
            curveTo(51.2F, 53.7F, 50.35F, 66.01F, 43.96F, 78.43F)
            curveTo(37.56F, 90.86F, 28.04F, 98.71F, 22.69F, 95.95F)
            curveTo(17.34F, 93.2F, 18.19F, 80.89F, 24.58F, 68.46F)
            curveTo(30.98F, 56.03F, 40.5F, 48.19F, 45.85F, 50.94F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.t2),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(43.24F, 53.58F)
            curveToRelative(-0.64F, 0.01F, -1.34F, 0.16F, -2.08F, 0.43F)
            curveToRelative(-1.47F, 0.54F, -3.07F, 1.54F, -4.72F, 2.92F)
            curveToRelative(-3.29F, 2.76F, -6.66F, 6.97F, -9.14F, 11.8F)
            curveToRelative(-2.48F, 4.82F, -4.26F, 10.07F, -4.9F, 14.41F)
            curveToRelative(-0.32F, 2.17F, -0.36F, 4.08F, -0.06F, 5.62F)
            curveToRelative(0.31F, 1.54F, 0.97F, 2.65F, 1.98F, 3.17F)
            curveToRelative(1.03F, 0.53F, 2.35F, 0.44F, 3.86F, -0.16F)
            curveToRelative(1.5F, -0.6F, 3.14F, -1.69F, 4.83F, -3.16F)
            curveToRelative(3.37F, -2.94F, 6.82F, -7.32F, 9.3F, -12.15F)
            curveToRelative(2.48F, -4.82F, 4.18F, -9.89F, 4.74F, -14.06F)
            curveToRelative(0.28F, -2.09F, 0.28F, -3.91F, -0.05F, -5.38F)
            curveToRelative(-0.33F, -1.47F, -1.01F, -2.54F, -2.03F, -3.06F)
            curveToRelative(-0.51F, -0.26F, -1.1F, -0.39F, -1.74F, -0.37F)

            moveTo(43.24F, 54.91F)
            curveToRelative(0.46F, -0.01F, 0.82F, 0.07F, 1.12F, 0.22F)
            curveToRelative(0.58F, 0.3F, 1.06F, 0.94F, 1.34F, 2.17F)
            curveToRelative(0.28F, 1.23F, 0.3F, 2.92F, 0.03F, 4.9F)
            curveToRelative(-0.54F, 3.96F, -2.18F, 8.92F, -4.61F, 13.63F)
            curveToRelative(-2.4F, 4.67F, -5.78F, 8.95F, -8.99F, 11.75F)
            curveToRelative(-1.61F, 1.4F, -3.15F, 2.41F, -4.45F, 2.93F)
            curveToRelative(-1.31F, 0.52F, -2.21F, 0.5F, -2.76F, 0.21F)
            curveToRelative(-0.54F, -0.28F, -1.02F, -0.93F, -1.28F, -2.24F)
            curveToRelative(-0.26F, -1.3F, -0.24F, -3.09F, 0.07F, -5.16F)
            curveToRelative(0.62F, -4.14F, 2.35F, -9.29F, 4.77F, -13.99F)
            curveToRelative(2.4F, -4.67F, 5.69F, -8.76F, 8.81F, -11.39F)
            curveToRelative(1.56F, -1.31F, 3.06F, -2.23F, 4.32F, -2.69F)
            curveToRelative(0.63F, -0.23F, 1.17F, -0.34F, 1.63F, -0.35F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.t3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(37.61F, 66.77F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -1.8F, 0.54F)
            lineToRelative(-4.66F, 8.66F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 0.54F, 1.8F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.8F, -0.54F)
            lineToRelative(4.66F, -8.66F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -0.54F, -1.8F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.t2),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Round,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(35.87F, 65.97F)
            curveToRelative(-0.79F, -0.04F, -1.51F, 0.21F, -2.15F, 0.57F)
            curveToRelative(-1.28F, 0.72F, -2.36F, 1.92F, -3.21F, 3.28F)
            curveToRelative(-0.84F, 1.36F, -1.46F, 2.88F, -1.43F, 4.42F)
            curveToRelative(0.01F, 0.77F, 0.22F, 1.58F, 0.72F, 2.26F)
            curveToRelative(0.5F, 0.68F, 1.28F, 1.17F, 2.19F, 1.39F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.62F, -0.97F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, -0.97F, -1.62F)
            curveToRelative(-0.43F, -0.11F, -0.58F, -0.24F, -0.69F, -0.39F)
            curveToRelative(-0.11F, -0.15F, -0.19F, -0.36F, -0.2F, -0.73F)
            curveToRelative(-0.01F, -0.72F, 0.37F, -1.91F, 1.03F, -2.97F)
            curveToRelative(0.65F, -1.05F, 1.57F, -1.98F, 2.25F, -2.36F)
            curveToRelative(0.34F, -0.19F, 0.59F, -0.24F, 0.69F, -0.23F)
            curveToRelative(0.1F, 0.01F, 0.09F, -0.02F, 0.22F, 0.14F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 1.87F, 0.21F)
            arcToRelative(1.33F, 1.33F, 0.0F, false, false, 0.21F, -1.87F)
            curveToRelative(-0.53F, -0.67F, -1.36F, -1.09F, -2.15F, -1.14F)
            close()
        }.build()
        return _trashedNotesList!!
    }
private var _trashedNotesList: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconTrashedNotesListPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.TrashedNotesList, contentDescription = null)
    }
}