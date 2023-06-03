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
 * @Author maksonic on 03.06.2023
 */
val AppImage.TrashedFolder: ImageVector
    @Composable get() {
        _trashedFolder = ImageVector.Builder(
            name = "TrashedFolder",
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
            moveTo(54.06F, 80.9F)
            curveTo(45.94F, 80.9F, 38.53F, 81.37F, 31.99F, 82.11F)
            curveTo(30.83F, 82.04F, 29.76F, 82.0F, 28.91F, 82.0F)
            curveTo(24.63F, 82.0F, 20.78F, 82.91F, 18.06F, 84.35F)
            curveTo(9.19F, 86.31F, 4.0F, 88.71F, 4.0F, 90.48F)
            curveTo(4.0F, 92.11F, 8.47F, 92.92F, 16.15F, 93.31F)
            curveTo(18.76F, 95.47F, 23.5F, 96.91F, 28.91F, 96.91F)
            curveTo(34.01F, 96.91F, 45.44F, 95.63F, 53.41F, 93.68F)
            curveTo(53.65F, 93.68F, 53.83F, 93.68F, 54.06F, 93.68F)
            curveTo(83.48F, 93.68F, 96.67F, 87.63F, 96.67F, 84.1F)
            curveTo(96.67F, 80.57F, 83.48F, 80.9F, 54.06F, 80.9F)
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
            moveTo(82.21F, 33.6F)
            curveTo(82.21F, 35.91F, 70.34F, 37.78F, 55.7F, 37.78F)
            curveTo(41.06F, 37.78F, 29.19F, 35.91F, 29.19F, 33.6F)
            curveTo(29.19F, 31.29F, 41.06F, 29.41F, 55.7F, 29.41F)
            curveTo(70.34F, 29.41F, 82.21F, 31.29F, 82.21F, 33.6F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f2),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(43.65F, 3.7F)
            curveTo(44.43F, 2.21F, 46.28F, 1.64F, 47.77F, 2.43F)
            lineTo(57.57F, 7.59F)
            curveTo(58.21F, 7.93F, 58.71F, 8.49F, 58.98F, 9.17F)
            lineTo(59.63F, 10.81F)
            curveTo(59.9F, 11.48F, 60.4F, 12.04F, 61.05F, 12.38F)
            lineTo(75.86F, 20.19F)
            curveTo(77.35F, 20.97F, 77.92F, 22.82F, 77.13F, 24.31F)
            lineTo(66.77F, 43.98F)
            curveTo(66.14F, 44.8F, 64.14F, 46.04F, 62.65F, 45.26F)
            lineTo(33.34F, 29.81F)
            curveTo(31.85F, 29.02F, 31.28F, 27.18F, 32.06F, 25.68F)
            lineTo(43.65F, 3.7F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f2),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(40.93F, 8.86F)
            curveTo(41.02F, 8.69F, 41.12F, 8.53F, 41.25F, 8.39F)
            curveTo(42.2F, 7.43F, 43.77F, 7.22F, 44.98F, 7.86F)
            lineTo(74.27F, 23.29F)
            curveTo(75.94F, 24.17F, 76.6F, 25.32F, 75.28F, 27.84F)
            lineTo(66.77F, 43.98F)
            curveTo(65.87F, 45.17F, 63.97F, 45.95F, 62.65F, 45.26F)
            lineTo(33.21F, 29.74F)
            curveTo(31.84F, 29.02F, 31.19F, 27.34F, 31.91F, 26.02F)
            curveTo(31.95F, 25.94F, 31.99F, 25.84F, 32.03F, 25.73F)
            curveTo(32.11F, 25.56F, 32.21F, 25.4F, 32.3F, 25.23F)
            lineTo(40.93F, 8.86F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c1),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.391984F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(42.54F, 6.21F)
            curveTo(42.63F, 6.05F, 42.72F, 5.91F, 42.84F, 5.79F)
            curveTo(43.72F, 4.89F, 45.2F, 4.69F, 46.34F, 5.29F)
            lineTo(75.62F, 20.73F)
            curveToRelative(0.82F, 0.43F, 1.34F, 0.9F, 1.53F, 1.54F)
            curveToRelative(0.19F, 0.64F, 0.05F, 1.5F, -0.61F, 2.74F)
            lineToRelative(-8.43F, 15.99F)
            curveToRelative(-0.05F, 0.09F, -0.1F, 0.17F, -0.16F, 0.24F)
            curveToRelative(-0.44F, 0.53F, -1.1F, 0.96F, -1.79F, 1.18F)
            curveToRelative(-0.7F, 0.22F, -1.41F, 0.22F, -1.98F, -0.08F)
            lineTo(34.74F, 26.83F)
            curveToRelative(-1.28F, -0.68F, -1.88F, -2.24F, -1.22F, -3.45F)
            curveToRelative(0.04F, -0.07F, 0.08F, -0.16F, 0.11F, -0.24F)
            curveToRelative(0.01F, -0.02F, 0.02F, -0.05F, 0.03F, -0.07F)
            curveToRelative(0.04F, -0.11F, 0.1F, -0.2F, 0.15F, -0.3F)
            curveToRelative(0.03F, -0.06F, 0.07F, -0.12F, 0.11F, -0.19F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.391984F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(41.98F, 7.28F)
            curveTo(42.07F, 7.12F, 42.16F, 6.97F, 42.27F, 6.86F)
            curveTo(43.16F, 5.96F, 44.64F, 5.76F, 45.78F, 6.36F)
            lineTo(75.06F, 21.79F)
            curveTo(75.88F, 22.22F, 76.41F, 22.7F, 76.59F, 23.33F)
            curveTo(76.78F, 23.97F, 76.64F, 24.83F, 75.99F, 26.08F)
            lineTo(67.55F, 42.07F)
            curveTo(67.51F, 42.16F, 67.46F, 42.24F, 67.4F, 42.31F)
            curveTo(66.95F, 42.83F, 66.3F, 43.27F, 65.6F, 43.49F)
            curveTo(64.91F, 43.71F, 64.2F, 43.71F, 63.62F, 43.41F)
            lineTo(34.18F, 27.89F)
            curveTo(32.89F, 27.21F, 32.3F, 25.65F, 32.96F, 24.44F)
            curveTo(33.0F, 24.37F, 33.04F, 24.28F, 33.07F, 24.2F)
            curveTo(33.08F, 24.18F, 33.09F, 24.16F, 33.1F, 24.13F)
            curveTo(33.14F, 24.03F, 33.19F, 23.94F, 33.25F, 23.84F)
            curveTo(33.29F, 23.78F, 33.32F, 23.71F, 33.36F, 23.64F)
            lineTo(41.98F, 7.28F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.391984F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(41.42F, 8.34F)
            curveTo(41.51F, 8.18F, 41.6F, 8.04F, 41.71F, 7.92F)
            curveTo(42.6F, 7.02F, 44.08F, 6.82F, 45.22F, 7.42F)
            lineTo(74.5F, 22.86F)
            curveToRelative(0.82F, 0.43F, 1.34F, 0.9F, 1.53F, 1.54F)
            curveToRelative(0.19F, 0.64F, 0.05F, 1.5F, -0.61F, 2.74F)
            lineToRelative(-8.43F, 15.99F)
            curveToRelative(-0.05F, 0.09F, -0.1F, 0.17F, -0.16F, 0.24F)
            curveToRelative(-0.44F, 0.53F, -1.1F, 0.96F, -1.79F, 1.18F)
            curveToRelative(-0.7F, 0.22F, -1.41F, 0.22F, -1.98F, -0.08F)
            lineTo(33.62F, 28.95F)
            curveToRelative(-1.28F, -0.68F, -1.88F, -2.24F, -1.22F, -3.45F)
            curveToRelative(0.04F, -0.07F, 0.08F, -0.16F, 0.11F, -0.24F)
            curveToRelative(0.01F, -0.02F, 0.02F, -0.05F, 0.03F, -0.07F)
            curveToRelative(0.04F, -0.11F, 0.1F, -0.2F, 0.15F, -0.3F)
            curveToRelative(0.03F, -0.06F, 0.07F, -0.12F, 0.11F, -0.19F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f1),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(40.93F, 8.86F)
            curveTo(41.02F, 8.69F, 41.12F, 8.53F, 41.25F, 8.39F)
            curveTo(42.2F, 7.43F, 43.77F, 7.22F, 44.98F, 7.86F)
            lineTo(74.27F, 23.29F)
            curveToRelative(1.67F, 0.88F, 2.33F, 2.02F, 1.01F, 4.55F)
            lineTo(66.85F, 43.83F)
            curveToRelative(-0.05F, 0.1F, -0.11F, 0.19F, -0.18F, 0.27F)
            curveToRelative(-0.94F, 1.11F, -2.75F, 1.82F, -4.02F, 1.15F)
            lineTo(33.21F, 29.74F)
            curveToRelative(-1.36F, -0.72F, -2.02F, -2.39F, -1.3F, -3.71F)
            curveToRelative(0.04F, -0.08F, 0.08F, -0.19F, 0.13F, -0.29F)
            curveToRelative(0.07F, -0.18F, 0.18F, -0.34F, 0.27F, -0.51F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f2),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveToRelative(50.01F, 34.37F)
            curveToRelative(4.45F, 2.35F, 9.96F, 0.64F, 12.3F, -3.8F)
            curveToRelative(2.34F, -4.44F, 0.63F, -9.95F, -3.82F, -12.29F)
            curveToRelative(-4.45F, -2.35F, -9.96F, -0.64F, -12.3F, 3.8F)
            curveToRelative(-2.34F, 4.44F, -0.63F, 9.95F, 3.82F, 12.29F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(51.06F, 19.87F)
            curveTo(51.01F, 19.87F, 50.96F, 19.88F, 50.91F, 19.89F)
            curveTo(50.65F, 19.98F, 50.5F, 20.26F, 50.58F, 20.53F)
            lineTo(51.1F, 22.2F)
            lineTo(49.42F, 22.72F)
            curveTo(49.15F, 22.8F, 49.0F, 23.08F, 49.09F, 23.35F)
            curveTo(49.17F, 23.62F, 49.46F, 23.77F, 49.72F, 23.69F)
            lineTo(51.4F, 23.17F)
            lineTo(51.91F, 24.84F)
            curveTo(52.0F, 25.11F, 52.28F, 25.26F, 52.55F, 25.17F)
            curveTo(52.82F, 25.09F, 52.97F, 24.81F, 52.88F, 24.54F)
            lineTo(52.37F, 22.87F)
            lineTo(54.04F, 22.35F)
            curveTo(54.31F, 22.27F, 54.46F, 21.98F, 54.38F, 21.71F)
            curveTo(54.29F, 21.45F, 54.01F, 21.3F, 53.74F, 21.38F)
            lineTo(52.07F, 21.9F)
            lineTo(51.55F, 20.23F)
            curveTo(51.48F, 20.01F, 51.28F, 19.87F, 51.06F, 19.87F)

            moveTo(57.98F, 23.52F)
            curveTo(57.93F, 23.52F, 57.88F, 23.52F, 57.83F, 23.54F)
            curveTo(57.56F, 23.62F, 57.41F, 23.91F, 57.5F, 24.17F)
            lineTo(58.01F, 25.84F)
            lineTo(56.34F, 26.36F)
            curveTo(56.07F, 26.45F, 55.92F, 26.73F, 56.01F, 27.0F)
            curveTo(56.09F, 27.27F, 56.37F, 27.41F, 56.64F, 27.33F)
            lineTo(58.31F, 26.81F)
            lineTo(58.83F, 28.48F)
            curveTo(58.91F, 28.75F, 59.2F, 28.9F, 59.47F, 28.82F)
            curveTo(59.73F, 28.74F, 59.88F, 28.45F, 59.8F, 28.19F)
            lineTo(59.28F, 26.51F)
            lineTo(60.96F, 25.99F)
            curveTo(61.22F, 25.91F, 61.38F, 25.63F, 61.29F, 25.36F)
            curveTo(61.21F, 25.09F, 60.93F, 24.94F, 60.66F, 25.03F)
            lineTo(58.98F, 25.54F)
            lineTo(58.46F, 23.87F)
            curveTo(58.4F, 23.66F, 58.2F, 23.52F, 57.98F, 23.52F)

            moveTo(49.38F, 27.34F)
            arcTo(0.39F, 0.39F, 0.0F, false, false, 49.09F, 27.42F)
            arcTo(0.39F, 0.39F, 0.0F, false, false, 49.02F, 27.97F)
            curveTo(51.03F, 30.59F, 52.77F, 31.44F, 56.21F, 31.76F)
            arcTo(0.39F, 0.39F, 0.0F, false, false, 56.64F, 31.41F)
            arcTo(0.39F, 0.39F, 0.0F, false, false, 56.29F, 30.98F)
            curveTo(52.93F, 30.66F, 51.58F, 30.01F, 49.64F, 27.49F)
            arcTo(0.39F, 0.39F, 0.0F, false, false, 49.38F, 27.34F)
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
            moveTo(29.19F, 33.6F)
            curveTo(29.19F, 33.6F, 37.56F, 81.04F, 38.26F, 84.52F)
            curveTo(38.96F, 88.01F, 45.76F, 88.1F, 55.7F, 88.01F)
            curveTo(65.64F, 88.1F, 72.44F, 88.01F, 73.14F, 84.52F)
            curveTo(73.84F, 81.04F, 82.21F, 33.6F, 82.21F, 33.6F)
            curveTo(77.63F, 36.47F, 71.78F, 37.37F, 55.7F, 37.79F)
            curveTo(39.62F, 37.37F, 33.77F, 36.47F, 29.19F, 33.6F)
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
            moveToRelative(34.54F, 40.6F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.14F, 1.61F)
            lineToRelative(6.28F, 36.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.61F, 1.14F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.14F, -1.61F)
            lineTo(36.15F, 41.74F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.61F, -1.14F)

            moveTo(76.16F, 40.6F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.61F, 1.14F)
            lineToRelative(-6.28F, 36.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.14F, 1.61F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.61F, -1.14F)
            lineToRelative(6.28F, -36.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.14F, -1.61F)

            moveTo(55.7F, 41.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.4F, 1.39F)
            verticalLineToRelative(37.67F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.4F, 1.4F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.39F, -1.4F)
            lineTo(57.1F, 43.37F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.39F, -1.39F)

            moveTo(66.27F, 41.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.5F, 1.29F)
            lineToRelative(-2.79F, 36.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.29F, 1.5F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.5F, -1.29F)
            lineToRelative(2.79F, -36.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.29F, -1.5F)

            moveTo(44.41F, 41.98F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.26F, 1.52F)
            lineToRelative(3.49F, 36.97F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.52F, 1.26F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.26F, -1.52F)
            lineTo(45.93F, 43.23F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.52F, -1.26F)
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
            moveTo(42.67F, 44.4F)
            curveTo(48.27F, 47.29F, 47.39F, 60.18F, 40.69F, 73.19F)
            curveTo(33.99F, 86.21F, 24.01F, 94.43F, 18.41F, 91.54F)
            curveTo(12.81F, 88.66F, 13.69F, 75.77F, 20.39F, 62.75F)
            curveTo(27.09F, 49.73F, 37.07F, 41.52F, 42.67F, 44.4F)
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
            moveTo(41.28F, 43.01F)
            curveTo(46.88F, 45.89F, 45.99F, 58.78F, 39.29F, 71.8F)
            curveTo(32.59F, 84.82F, 22.62F, 93.03F, 17.01F, 90.15F)
            curveTo(11.41F, 87.26F, 12.3F, 74.37F, 19.0F, 61.36F)
            curveTo(25.7F, 48.34F, 35.67F, 40.12F, 41.28F, 43.01F)
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
            moveToRelative(38.54F, 45.77F)
            curveToRelative(-0.67F, 0.01F, -1.4F, 0.17F, -2.17F, 0.45F)
            curveToRelative(-1.54F, 0.56F, -3.22F, 1.61F, -4.94F, 3.06F)
            curveToRelative(-3.45F, 2.89F, -6.97F, 7.3F, -9.57F, 12.36F)
            curveToRelative(-2.6F, 5.05F, -4.46F, 10.54F, -5.13F, 15.09F)
            curveToRelative(-0.34F, 2.28F, -0.38F, 4.28F, -0.06F, 5.88F)
            curveToRelative(0.32F, 1.61F, 1.01F, 2.77F, 2.07F, 3.32F)
            curveToRelative(1.08F, 0.55F, 2.46F, 0.46F, 4.04F, -0.17F)
            curveToRelative(1.58F, -0.63F, 3.29F, -1.77F, 5.06F, -3.31F)
            curveToRelative(3.53F, -3.08F, 7.14F, -7.67F, 9.74F, -12.72F)
            curveToRelative(2.6F, -5.05F, 4.37F, -10.36F, 4.97F, -14.72F)
            curveToRelative(0.3F, -2.19F, 0.3F, -4.09F, -0.05F, -5.63F)
            curveToRelative(-0.35F, -1.54F, -1.06F, -2.66F, -2.13F, -3.21F)
            curveToRelative(-0.54F, -0.28F, -1.15F, -0.4F, -1.82F, -0.39F)

            moveTo(38.54F, 47.16F)
            curveToRelative(0.48F, -0.01F, 0.87F, 0.08F, 1.17F, 0.24F)
            curveToRelative(0.61F, 0.31F, 1.11F, 0.98F, 1.4F, 2.28F)
            curveToRelative(0.29F, 1.29F, 0.31F, 3.06F, 0.03F, 5.13F)
            curveToRelative(-0.56F, 4.14F, -2.29F, 9.35F, -4.82F, 14.27F)
            curveToRelative(-2.52F, 4.89F, -6.05F, 9.37F, -9.42F, 12.31F)
            curveToRelative(-1.68F, 1.47F, -3.3F, 2.52F, -4.66F, 3.07F)
            curveToRelative(-1.37F, 0.55F, -2.31F, 0.52F, -2.89F, 0.22F)
            curveToRelative(-0.57F, -0.29F, -1.07F, -0.98F, -1.35F, -2.35F)
            curveToRelative(-0.27F, -1.36F, -0.25F, -3.23F, 0.07F, -5.41F)
            curveToRelative(0.64F, -4.34F, 2.46F, -9.73F, 4.99F, -14.66F)
            curveToRelative(2.52F, -4.89F, 5.96F, -9.18F, 9.23F, -11.93F)
            curveToRelative(1.64F, -1.37F, 3.2F, -2.34F, 4.52F, -2.82F)
            curveToRelative(0.66F, -0.24F, 1.23F, -0.35F, 1.71F, -0.36F)
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
            moveToRelative(32.64F, 59.58F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -1.89F, 0.57F)
            lineToRelative(-4.88F, 9.07F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 0.57F, 1.89F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.89F, -0.57F)
            lineToRelative(4.88F, -9.07F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, -0.57F, -1.89F)
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
            moveToRelative(30.82F, 58.74F)
            curveToRelative(-0.83F, -0.05F, -1.58F, 0.22F, -2.25F, 0.6F)
            curveToRelative(-1.34F, 0.75F, -2.47F, 2.01F, -3.36F, 3.43F)
            curveToRelative(-0.88F, 1.43F, -1.53F, 3.01F, -1.5F, 4.63F)
            curveToRelative(0.01F, 0.81F, 0.22F, 1.65F, 0.75F, 2.37F)
            curveToRelative(0.53F, 0.71F, 1.34F, 1.22F, 2.29F, 1.46F)
            arcTo(1.4F, 1.4F, 0.0F, false, false, 28.45F, 70.21F)
            arcTo(1.4F, 1.4F, 0.0F, false, false, 27.44F, 68.52F)
            curveToRelative(-0.45F, -0.11F, -0.61F, -0.26F, -0.72F, -0.41F)
            curveToRelative(-0.11F, -0.15F, -0.2F, -0.38F, -0.21F, -0.76F)
            curveToRelative(-0.01F, -0.76F, 0.39F, -2.01F, 1.08F, -3.11F)
            curveToRelative(0.69F, -1.1F, 1.65F, -2.07F, 2.36F, -2.47F)
            curveToRelative(0.36F, -0.2F, 0.62F, -0.25F, 0.72F, -0.24F)
            curveToRelative(0.1F, 0.01F, 0.09F, -0.02F, 0.23F, 0.15F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 1.96F, 0.22F)
            arcToRelative(1.4F, 1.4F, 0.0F, false, false, 0.22F, -1.96F)
            curveTo(32.51F, 59.23F, 31.65F, 58.79F, 30.82F, 58.74F)
            close()
        }.build()
        return _trashedFolder!!
    }
private var _trashedFolder: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconTrashedFolderPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.TrashedFolder, contentDescription = null)
    }
}