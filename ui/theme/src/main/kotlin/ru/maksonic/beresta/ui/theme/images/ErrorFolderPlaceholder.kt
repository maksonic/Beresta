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
 * @Author maksonic on 26.05.2023
 */
val AppImage.ErrorFolderPlaceholder: ImageVector
    @Composable get() {
        _errorFolderPlaceholder = ImageVector.Builder(
            name = "ErrorFolderPlaceholder",
            defaultWidth = 100.0.dp,
            defaultHeight = 100.0.dp,
            viewportWidth = 100.0F,
            viewportHeight = 100.0F,
        ).path(
            fill = SolidColor(Color(0xFFFFC7C7)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(94.0F, 65.0F)
            curveTo(94.0F, 76.05F, 74.08F, 85.0F, 49.5F, 85.0F)
            curveTo(24.92F, 85.0F, 5.0F, 76.05F, 5.0F, 65.0F)
            curveTo(5.0F, 53.95F, 24.92F, 45.0F, 49.5F, 45.0F)
            curveTo(74.08F, 45.0F, 94.0F, 53.95F, 94.0F, 65.0F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f3),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(18.01F, 15.07F)
            curveTo(18.01F, 12.27F, 20.29F, 10.0F, 23.09F, 10.0F)
            horizontalLineTo(41.5F)
            curveTo(42.71F, 10.0F, 43.89F, 10.43F, 44.81F, 11.22F)
            lineTo(47.03F, 13.13F)
            curveTo(47.95F, 13.92F, 49.12F, 14.35F, 50.33F, 14.35F)
            horizontalLineTo(78.17F)
            curveTo(80.97F, 14.35F, 83.24F, 16.62F, 83.24F, 19.42F)
            verticalLineTo(56.38F)
            curveTo(82.96F, 58.08F, 80.97F, 61.46F, 78.17F, 61.46F)
            horizontalLineTo(23.09F)
            curveTo(20.29F, 61.46F, 18.01F, 59.19F, 18.01F, 56.38F)
            verticalLineTo(15.07F)
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
            moveTo(18.01F, 24.77F)
            curveTo(18.01F, 24.45F, 18.04F, 24.13F, 18.13F, 23.83F)
            curveTo(18.77F, 21.67F, 20.93F, 20.15F, 23.2F, 20.15F)
            horizontalLineTo(78.24F)
            curveTo(81.38F, 20.15F, 83.24F, 21.31F, 83.24F, 26.05F)
            lineTo(83.24F, 56.38F)
            curveTo(82.83F, 58.83F, 80.65F, 61.46F, 78.17F, 61.46F)
            lineTo(22.84F, 61.46F)
            curveTo(20.28F, 61.46F, 18.01F, 59.5F, 18.05F, 57.0F)
            curveTo(18.05F, 56.85F, 18.03F, 56.66F, 18.01F, 56.48F)
            curveTo(17.98F, 56.16F, 18.01F, 55.84F, 18.01F, 55.52F)
            verticalLineTo(24.77F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(18.01F, 19.62F)
            curveTo(18.01F, 19.3F, 18.04F, 18.98F, 18.13F, 18.68F)
            curveTo(18.77F, 16.53F, 20.93F, 15.0F, 23.2F, 15.0F)
            horizontalLineTo(78.24F)
            curveTo(81.38F, 15.0F, 83.24F, 16.17F, 83.24F, 20.91F)
            lineTo(83.24F, 50.82F)
            curveTo(83.24F, 51.1F, 83.22F, 51.38F, 83.16F, 51.65F)
            curveTo(82.59F, 53.97F, 80.51F, 56.31F, 78.17F, 56.31F)
            lineTo(22.84F, 56.31F)
            curveTo(20.28F, 56.31F, 18.46F, 54.5F, 18.5F, 52.0F)
            curveTo(18.5F, 51.85F, 18.52F, 51.19F, 18.5F, 51.0F)
            curveTo(18.47F, 50.68F, 18.5F, 50.82F, 18.5F, 50.5F)
            lineTo(18.01F, 19.62F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(19.5F, 50.49F)
            verticalLineTo(50.5F)
            curveTo(19.5F, 50.59F, 19.5F, 50.67F, 19.49F, 50.73F)
            curveTo(19.49F, 50.76F, 19.49F, 50.78F, 19.49F, 50.8F)
            lineTo(19.49F, 50.81F)
            curveTo(19.49F, 50.83F, 19.49F, 50.86F, 19.5F, 50.91F)
            curveTo(19.52F, 51.14F, 19.5F, 51.76F, 19.5F, 51.96F)
            curveTo(19.5F, 51.99F, 19.5F, 52.0F, 19.5F, 52.01F)
            curveTo(19.47F, 53.94F, 20.82F, 55.31F, 22.84F, 55.31F)
            lineTo(78.17F, 55.31F)
            curveTo(79.0F, 55.31F, 79.86F, 54.89F, 80.62F, 54.14F)
            curveTo(81.38F, 53.39F, 81.95F, 52.39F, 82.19F, 51.41F)
            curveTo(82.22F, 51.25F, 82.24F, 51.06F, 82.24F, 50.82F)
            lineTo(82.24F, 20.91F)
            curveTo(82.24F, 18.63F, 81.79F, 17.48F, 81.2F, 16.87F)
            curveTo(80.63F, 16.28F, 79.72F, 16.0F, 78.24F, 16.0F)
            horizontalLineTo(23.2F)
            curveTo(21.34F, 16.0F, 19.6F, 17.26F, 19.09F, 18.97F)
            curveTo(19.04F, 19.13F, 19.01F, 19.34F, 19.01F, 19.61F)
            lineTo(19.5F, 50.49F)

            moveTo(18.01F, 19.62F)
            curveTo(18.01F, 19.3F, 18.04F, 18.98F, 18.13F, 18.68F)
            curveTo(18.77F, 16.53F, 20.93F, 15.0F, 23.2F, 15.0F)
            horizontalLineTo(78.24F)
            curveTo(81.38F, 15.0F, 83.24F, 16.17F, 83.24F, 20.91F)
            lineTo(83.24F, 50.82F)
            curveTo(83.24F, 51.1F, 83.22F, 51.38F, 83.16F, 51.65F)
            curveTo(82.59F, 53.97F, 80.51F, 56.31F, 78.17F, 56.31F)
            lineTo(22.84F, 56.31F)
            curveTo(20.28F, 56.31F, 18.46F, 54.5F, 18.5F, 52.0F)
            curveTo(18.5F, 51.98F, 18.5F, 51.95F, 18.5F, 51.92F)
            curveTo(18.51F, 51.69F, 18.51F, 51.16F, 18.5F, 51.0F)
            curveTo(18.48F, 50.81F, 18.49F, 50.78F, 18.49F, 50.73F)
            curveTo(18.5F, 50.69F, 18.5F, 50.63F, 18.5F, 50.5F)
            lineTo(18.01F, 19.62F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(19.5F, 51.93F)
            lineTo(19.01F, 21.61F)
            curveTo(19.01F, 21.34F, 19.04F, 21.13F, 19.09F, 20.97F)
            curveTo(19.6F, 19.26F, 21.34F, 18.0F, 23.2F, 18.0F)
            horizontalLineTo(78.24F)
            curveTo(79.72F, 18.0F, 80.63F, 18.28F, 81.2F, 18.87F)
            curveTo(81.79F, 19.48F, 82.24F, 20.63F, 82.24F, 22.91F)
            lineTo(82.24F, 52.82F)
            curveTo(82.24F, 53.06F, 82.22F, 53.25F, 82.19F, 53.41F)
            curveTo(81.95F, 54.39F, 81.38F, 55.39F, 80.62F, 56.14F)
            curveTo(79.86F, 56.89F, 79.0F, 57.31F, 78.17F, 57.31F)
            lineTo(22.84F, 57.31F)
            curveTo(21.01F, 57.31F, 19.48F, 56.1F, 19.13F, 54.58F)
            curveTo(19.15F, 54.57F, 19.16F, 54.56F, 19.18F, 54.56F)
            curveTo(19.26F, 54.53F, 19.41F, 54.48F, 19.56F, 54.36F)
            curveTo(19.71F, 54.25F, 20.04F, 53.94F, 20.0F, 53.41F)
            curveTo(19.98F, 53.18F, 19.89F, 52.96F, 19.85F, 52.83F)
            curveTo(19.8F, 52.7F, 19.74F, 52.55F, 19.69F, 52.42F)
            curveTo(19.68F, 52.41F, 19.67F, 52.39F, 19.66F, 52.37F)
            curveTo(19.6F, 52.22F, 19.55F, 52.1F, 19.52F, 52.0F)
            curveTo(19.51F, 51.97F, 19.5F, 51.95F, 19.5F, 51.93F)

            moveTo(18.48F, 54.72F)
            lineTo(18.48F, 54.72F)
            curveTo(18.47F, 54.72F, 18.47F, 54.72F, 18.48F, 54.72F)

            moveTo(83.24F, 22.91F)
            curveTo(83.24F, 18.17F, 81.38F, 17.0F, 78.24F, 17.0F)
            horizontalLineTo(23.2F)
            curveTo(20.93F, 17.0F, 18.77F, 18.53F, 18.13F, 20.68F)
            curveTo(18.04F, 20.98F, 18.01F, 21.3F, 18.01F, 21.62F)
            lineTo(18.5F, 52.0F)
            curveTo(18.5F, 52.17F, 18.63F, 52.49F, 18.76F, 52.81F)
            curveTo(18.88F, 53.08F, 18.99F, 53.35F, 19.0F, 53.5F)
            curveTo(19.01F, 53.6F, 18.74F, 53.65F, 18.49F, 53.7F)
            curveTo(18.27F, 53.74F, 18.05F, 53.78F, 18.05F, 53.85F)
            curveTo(18.01F, 56.35F, 20.28F, 58.31F, 22.84F, 58.31F)
            lineTo(78.17F, 58.31F)
            curveTo(80.51F, 58.31F, 82.59F, 55.97F, 83.16F, 53.65F)
            curveTo(83.22F, 53.38F, 83.24F, 53.1F, 83.24F, 52.82F)
            lineTo(83.24F, 22.91F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(19.0F, 23.5F)
            curveTo(19.0F, 23.35F, 18.77F, 23.22F, 18.55F, 23.1F)
            curveTo(18.31F, 22.96F, 18.08F, 22.84F, 18.13F, 22.68F)
            curveTo(18.77F, 20.53F, 20.93F, 19.0F, 23.2F, 19.0F)
            horizontalLineTo(78.24F)
            curveTo(81.38F, 19.0F, 83.24F, 20.17F, 83.24F, 24.91F)
            lineTo(83.24F, 54.82F)
            curveTo(83.24F, 55.1F, 83.22F, 55.38F, 83.16F, 55.65F)
            curveTo(82.59F, 57.97F, 80.51F, 60.31F, 78.17F, 60.31F)
            lineTo(22.84F, 60.31F)
            curveTo(20.28F, 60.31F, 18.01F, 58.35F, 18.05F, 55.85F)
            curveTo(18.05F, 55.74F, 18.04F, 55.61F, 18.03F, 55.47F)
            curveTo(18.02F, 55.42F, 18.02F, 55.38F, 18.01F, 55.33F)
            curveTo(18.0F, 55.17F, 18.13F, 55.04F, 18.25F, 54.91F)
            curveTo(18.38F, 54.78F, 18.5F, 54.66F, 18.5F, 54.5F)
            lineTo(19.0F, 23.5F)

            moveTo(19.5F, 54.51F)
            lineTo(20.0F, 23.51F)
            verticalLineTo(23.5F)
            curveTo(20.0F, 23.19F, 19.88F, 22.96F, 19.77F, 22.8F)
            curveTo(19.67F, 22.66F, 19.54F, 22.55F, 19.46F, 22.49F)
            curveTo(19.41F, 22.45F, 19.36F, 22.42F, 19.31F, 22.39F)
            curveTo(20.01F, 20.98F, 21.56F, 20.0F, 23.2F, 20.0F)
            horizontalLineTo(78.24F)
            curveTo(79.72F, 20.0F, 80.63F, 20.28F, 81.2F, 20.87F)
            curveTo(81.79F, 21.48F, 82.24F, 22.63F, 82.24F, 24.91F)
            lineTo(82.24F, 54.82F)
            curveTo(82.24F, 55.06F, 82.22F, 55.25F, 82.19F, 55.41F)
            curveTo(81.95F, 56.39F, 81.38F, 57.39F, 80.62F, 58.14F)
            curveTo(79.86F, 58.89F, 79.0F, 59.31F, 78.17F, 59.31F)
            lineTo(22.84F, 59.31F)
            curveTo(20.73F, 59.31F, 19.02F, 57.71F, 19.05F, 55.87F)
            curveTo(19.05F, 55.76F, 19.05F, 55.65F, 19.04F, 55.54F)
            curveTo(19.09F, 55.47F, 19.17F, 55.39F, 19.24F, 55.3F)
            curveTo(19.35F, 55.14F, 19.5F, 54.87F, 19.5F, 54.51F)
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
            moveTo(18.01F, 24.77F)
            curveTo(18.01F, 24.45F, 18.04F, 24.13F, 18.13F, 23.83F)
            curveTo(18.77F, 21.67F, 20.93F, 20.15F, 23.2F, 20.15F)
            horizontalLineTo(78.24F)
            curveTo(81.38F, 20.15F, 83.24F, 21.31F, 83.24F, 26.05F)
            lineTo(83.24F, 55.96F)
            curveTo(83.24F, 56.24F, 83.22F, 56.52F, 83.16F, 56.79F)
            curveTo(82.59F, 59.11F, 80.51F, 61.46F, 78.17F, 61.46F)
            lineTo(22.84F, 61.46F)
            curveTo(20.28F, 61.46F, 18.01F, 59.5F, 18.05F, 57.0F)
            curveTo(18.05F, 56.85F, 18.03F, 56.66F, 18.01F, 56.48F)
            curveTo(17.98F, 56.16F, 18.01F, 50.59F, 18.01F, 50.27F)
            lineTo(18.01F, 24.77F)
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
            moveTo(51.16F, 55.24F)
            curveTo(59.52F, 55.24F, 66.3F, 48.47F, 66.3F, 40.12F)
            curveTo(66.3F, 31.77F, 59.52F, 25.0F, 51.16F, 25.0F)
            curveTo(42.79F, 25.0F, 36.01F, 31.77F, 36.01F, 40.12F)
            curveTo(36.01F, 48.47F, 42.79F, 55.24F, 51.16F, 55.24F)
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
            moveTo(47.22F, 36.2F)
            lineTo(55.77F, 44.76F)

            moveTo(55.78F, 36.2F)
            lineTo(47.21F, 44.76F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f0),
            fillAlpha = 0.8F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(46.26F, 35.24F)
            curveTo(46.79F, 34.71F, 47.65F, 34.71F, 48.18F, 35.24F)
            lineTo(51.5F, 38.56F)
            lineTo(54.82F, 35.24F)
            curveTo(55.35F, 34.71F, 56.21F, 34.71F, 56.74F, 35.24F)
            curveTo(57.27F, 35.77F, 57.27F, 36.63F, 56.74F, 37.16F)
            lineTo(53.42F, 40.48F)
            lineTo(56.73F, 43.8F)
            curveTo(57.26F, 44.33F, 57.26F, 45.19F, 56.73F, 45.72F)
            curveTo(56.2F, 46.25F, 55.34F, 46.25F, 54.81F, 45.72F)
            lineTo(51.5F, 42.4F)
            lineTo(48.17F, 45.73F)
            curveTo(47.64F, 46.26F, 46.78F, 46.26F, 46.25F, 45.73F)
            curveTo(45.72F, 45.19F, 45.72F, 44.33F, 46.25F, 43.8F)
            lineTo(49.57F, 40.48F)
            lineTo(46.26F, 37.17F)
            curveTo(45.73F, 36.63F, 45.73F, 35.77F, 46.26F, 35.24F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(21.85F, 63.3F)
            lineTo(28.26F, 70.12F)
            curveTo(28.97F, 70.88F, 28.98F, 72.02F, 28.28F, 72.68F)
            lineTo(22.75F, 77.87F)
            curveTo(22.05F, 78.53F, 20.91F, 78.45F, 20.21F, 77.7F)
            lineTo(12.28F, 69.27F)
            curveTo(11.57F, 68.51F, 11.57F, 67.37F, 12.27F, 66.71F)
            lineTo(16.3F, 62.93F)
            lineTo(21.85F, 63.3F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFAA0000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(21.85F, 63.3F)
            lineTo(20.36F, 64.71F)
            curveTo(19.66F, 65.37F, 18.52F, 65.29F, 17.81F, 64.54F)
            lineTo(16.3F, 62.93F)
            lineTo(21.85F, 63.3F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE63F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(16.49F, 70.52F)
            curveTo(15.97F, 69.96F, 15.98F, 69.11F, 16.52F, 68.6F)
            lineTo(19.42F, 65.88F)
            curveTo(19.95F, 65.38F, 20.81F, 65.42F, 21.33F, 65.97F)
            lineTo(24.16F, 68.98F)
            curveTo(24.68F, 69.54F, 24.67F, 70.4F, 24.14F, 70.9F)
            lineTo(21.23F, 73.62F)
            curveTo(20.7F, 74.13F, 19.85F, 74.08F, 19.32F, 73.53F)
            lineTo(16.49F, 70.52F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFE5656)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(19.41F, 70.49F)
            curveTo(19.41F, 70.39F, 19.41F, 70.34F, 19.43F, 70.31F)
            curveTo(19.45F, 70.28F, 19.47F, 70.26F, 19.51F, 70.25F)
            curveTo(19.54F, 70.23F, 19.59F, 70.24F, 19.69F, 70.26F)
            lineTo(21.04F, 70.53F)
            curveTo(21.19F, 70.56F, 21.26F, 70.57F, 21.3F, 70.61F)
            curveTo(21.33F, 70.65F, 21.34F, 70.69F, 21.34F, 70.74F)
            curveTo(21.33F, 70.79F, 21.28F, 70.84F, 21.17F, 70.94F)
            lineTo(19.84F, 72.19F)
            curveTo(19.71F, 72.31F, 19.65F, 72.37F, 19.59F, 72.37F)
            curveTo(19.54F, 72.37F, 19.5F, 72.35F, 19.47F, 72.32F)
            curveTo(19.43F, 72.27F, 19.43F, 72.18F, 19.43F, 72.01F)
            lineTo(19.41F, 70.49F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(20.33F, 68.49F)
            curveTo(20.31F, 68.37F, 20.3F, 68.31F, 20.32F, 68.27F)
            curveTo(20.33F, 68.23F, 20.36F, 68.2F, 20.4F, 68.19F)
            curveTo(20.44F, 68.17F, 20.5F, 68.19F, 20.62F, 68.22F)
            lineTo(22.89F, 68.8F)
            curveTo(23.03F, 68.84F, 23.1F, 68.85F, 23.14F, 68.89F)
            curveTo(23.16F, 68.93F, 23.18F, 68.97F, 23.17F, 69.02F)
            curveTo(23.16F, 69.06F, 23.11F, 69.11F, 23.01F, 69.21F)
            lineTo(21.18F, 70.93F)
            curveTo(21.07F, 71.03F, 21.02F, 71.08F, 20.97F, 71.08F)
            curveTo(20.93F, 71.09F, 20.88F, 71.07F, 20.85F, 71.04F)
            curveTo(20.81F, 71.01F, 20.8F, 70.93F, 20.77F, 70.79F)
            lineTo(20.33F, 68.49F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(18.88F, 69.34F)
            curveTo(19.05F, 69.53F, 19.05F, 69.82F, 18.87F, 69.99F)
            curveTo(18.69F, 70.16F, 18.4F, 70.15F, 18.22F, 69.96F)
            curveTo(18.04F, 69.77F, 18.04F, 69.48F, 18.23F, 69.31F)
            curveTo(18.41F, 69.14F, 18.7F, 69.15F, 18.88F, 69.34F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8181)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(51.68F, 76.24F)
            lineTo(43.18F, 82.22F)
            curveTo(42.24F, 82.88F, 40.96F, 82.67F, 40.31F, 81.75F)
            lineTo(35.18F, 74.46F)
            curveTo(34.53F, 73.53F, 34.76F, 72.25F, 35.7F, 71.59F)
            lineTo(46.2F, 64.2F)
            curveTo(47.14F, 63.54F, 48.43F, 63.75F, 49.08F, 64.67F)
            lineTo(52.82F, 69.99F)
            lineTo(51.68F, 76.24F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(37.34F, 72.47F)
            curveTo(36.96F, 72.74F, 36.86F, 73.27F, 37.12F, 73.64F)
            lineTo(41.73F, 80.18F)
            curveTo(41.99F, 80.56F, 42.52F, 80.65F, 42.9F, 80.38F)
            curveTo(43.29F, 80.11F, 43.38F, 79.58F, 43.12F, 79.21F)
            lineTo(38.51F, 72.67F)
            curveTo(38.25F, 72.29F, 37.72F, 72.2F, 37.34F, 72.47F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(42.89F, 68.56F)
            curveTo(42.51F, 68.83F, 42.41F, 69.36F, 42.68F, 69.73F)
            lineTo(47.28F, 76.28F)
            curveTo(47.55F, 76.65F, 48.07F, 76.74F, 48.46F, 76.47F)
            curveTo(48.84F, 76.2F, 48.94F, 75.68F, 48.67F, 75.3F)
            lineTo(44.07F, 68.76F)
            curveTo(43.8F, 68.38F, 43.28F, 68.29F, 42.89F, 68.56F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(45.67F, 66.61F)
            curveTo(45.28F, 66.88F, 45.19F, 67.4F, 45.45F, 67.78F)
            lineTo(47.47F, 70.64F)
            curveTo(47.73F, 71.02F, 48.26F, 71.11F, 48.64F, 70.84F)
            curveTo(49.03F, 70.57F, 49.12F, 70.04F, 48.86F, 69.67F)
            lineTo(46.84F, 66.8F)
            curveTo(46.58F, 66.43F, 46.05F, 66.34F, 45.67F, 66.61F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(40.12F, 70.52F)
            curveTo(39.73F, 70.79F, 39.64F, 71.31F, 39.9F, 71.69F)
            lineTo(44.51F, 78.23F)
            curveTo(44.77F, 78.61F, 45.3F, 78.69F, 45.68F, 78.42F)
            curveTo(46.06F, 78.15F, 46.16F, 77.63F, 45.89F, 77.25F)
            lineTo(41.29F, 70.71F)
            curveTo(41.02F, 70.34F, 40.5F, 70.25F, 40.12F, 70.52F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFAA0000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(51.68F, 76.24F)
            lineTo(50.29F, 74.27F)
            curveTo(49.64F, 73.34F, 49.87F, 72.06F, 50.81F, 71.4F)
            lineTo(52.82F, 69.99F)
            lineTo(51.68F, 76.24F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(76.83F, 77.46F)
            lineTo(68.64F, 72.12F)
            curveTo(67.73F, 71.53F, 67.46F, 70.35F, 68.03F, 69.48F)
            lineTo(72.52F, 62.58F)
            curveTo(73.09F, 61.71F, 74.28F, 61.48F, 75.19F, 62.07F)
            lineTo(85.32F, 68.66F)
            curveTo(86.22F, 69.25F, 86.5F, 70.44F, 85.93F, 71.31F)
            lineTo(82.65F, 76.34F)
            lineTo(76.83F, 77.46F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFAA0000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(76.83F, 77.46F)
            lineTo(78.05F, 75.59F)
            curveTo(78.62F, 74.72F, 79.81F, 74.49F, 80.72F, 75.08F)
            lineTo(82.65F, 76.34F)
            lineTo(76.83F, 77.46F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(80.69F, 68.51F)
            curveTo(81.35F, 68.95F, 81.54F, 69.84F, 81.11F, 70.5F)
            lineTo(78.75F, 74.12F)
            curveTo(78.32F, 74.79F, 77.42F, 74.98F, 76.76F, 74.55F)
            lineTo(73.14F, 72.19F)
            curveTo(72.47F, 71.75F, 72.28F, 70.86F, 72.72F, 70.2F)
            lineTo(75.07F, 66.58F)
            curveTo(75.51F, 65.91F, 76.4F, 65.72F, 77.07F, 66.15F)
            lineTo(80.69F, 68.51F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFD31F1F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(75.96F, 68.2F)
            curveTo(76.0F, 68.14F, 76.05F, 68.1F, 76.12F, 68.09F)
            curveTo(76.18F, 68.08F, 76.25F, 68.09F, 76.3F, 68.13F)
            lineTo(79.19F, 70.01F)
            curveTo(79.25F, 70.04F, 79.28F, 70.1F, 79.3F, 70.16F)
            curveTo(79.31F, 70.23F, 79.3F, 70.29F, 79.26F, 70.35F)
            curveTo(79.22F, 70.41F, 79.17F, 70.44F, 79.11F, 70.46F)
            curveTo(79.04F, 70.47F, 78.98F, 70.46F, 78.92F, 70.42F)
            lineTo(76.03F, 68.54F)
            curveTo(75.98F, 68.5F, 75.94F, 68.45F, 75.93F, 68.38F)
            curveTo(75.91F, 68.32F, 75.93F, 68.26F, 75.96F, 68.2F)

            moveTo(74.12F, 68.62F)
            curveTo(74.16F, 68.56F, 74.21F, 68.52F, 74.28F, 68.51F)
            curveTo(74.34F, 68.5F, 74.41F, 68.51F, 74.46F, 68.55F)
            lineTo(79.55F, 71.86F)
            curveTo(79.61F, 71.9F, 79.64F, 71.95F, 79.66F, 72.01F)
            curveTo(79.67F, 72.08F, 79.66F, 72.14F, 79.62F, 72.2F)
            curveTo(79.58F, 72.26F, 79.53F, 72.3F, 79.46F, 72.31F)
            curveTo(79.4F, 72.32F, 79.34F, 72.31F, 79.28F, 72.27F)
            lineTo(74.2F, 68.96F)
            curveTo(74.14F, 68.92F, 74.1F, 68.87F, 74.09F, 68.81F)
            curveTo(74.07F, 68.74F, 74.09F, 68.68F, 74.12F, 68.62F)

            moveTo(77.8F, 67.78F)
            curveTo(77.84F, 67.72F, 77.89F, 67.68F, 77.96F, 67.67F)
            curveTo(78.02F, 67.65F, 78.09F, 67.67F, 78.14F, 67.71F)
            lineTo(78.83F, 68.15F)
            curveTo(78.89F, 68.19F, 78.93F, 68.24F, 78.94F, 68.31F)
            curveTo(78.95F, 68.37F, 78.94F, 68.44F, 78.9F, 68.5F)
            curveTo(78.86F, 68.55F, 78.81F, 68.59F, 78.75F, 68.6F)
            curveTo(78.68F, 68.62F, 78.62F, 68.6F, 78.56F, 68.57F)
            lineTo(77.87F, 68.12F)
            curveTo(77.82F, 68.08F, 77.78F, 68.03F, 77.77F, 67.96F)
            curveTo(77.75F, 67.9F, 77.77F, 67.84F, 77.8F, 67.78F)

            moveTo(74.48F, 70.47F)
            curveTo(74.52F, 70.41F, 74.57F, 70.38F, 74.64F, 70.36F)
            curveTo(74.7F, 70.35F, 74.76F, 70.36F, 74.82F, 70.4F)
            lineTo(77.71F, 72.28F)
            curveTo(77.77F, 72.32F, 77.8F, 72.37F, 77.82F, 72.43F)
            curveTo(77.83F, 72.5F, 77.82F, 72.56F, 77.78F, 72.62F)
            curveTo(77.74F, 72.68F, 77.69F, 72.72F, 77.63F, 72.73F)
            curveTo(77.56F, 72.74F, 77.5F, 72.73F, 77.44F, 72.69F)
            lineTo(74.55F, 70.81F)
            curveTo(74.5F, 70.78F, 74.46F, 70.72F, 74.45F, 70.66F)
            curveTo(74.43F, 70.59F, 74.45F, 70.53F, 74.48F, 70.47F)

            moveTo(74.84F, 72.32F)
            curveTo(74.88F, 72.27F, 74.93F, 72.23F, 75.0F, 72.22F)
            curveTo(75.06F, 72.2F, 75.12F, 72.21F, 75.18F, 72.25F)
            lineTo(75.87F, 72.7F)
            curveTo(75.93F, 72.74F, 75.96F, 72.79F, 75.98F, 72.86F)
            curveTo(75.99F, 72.92F, 75.98F, 72.98F, 75.94F, 73.04F)
            curveTo(75.9F, 73.1F, 75.85F, 73.14F, 75.79F, 73.15F)
            curveTo(75.72F, 73.16F, 75.66F, 73.15F, 75.6F, 73.11F)
            lineTo(74.91F, 72.67F)
            curveTo(74.85F, 72.63F, 74.82F, 72.58F, 74.81F, 72.51F)
            curveTo(74.79F, 72.45F, 74.8F, 72.38F, 74.84F, 72.32F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(76.53F, 70.2F)
            curveTo(76.61F, 70.07F, 76.72F, 70.0F, 76.87F, 69.97F)
            curveTo(77.01F, 69.95F, 77.15F, 69.98F, 77.28F, 70.06F)
            lineTo(78.39F, 70.78F)
            curveTo(78.51F, 70.86F, 78.59F, 70.97F, 78.62F, 71.11F)
            curveTo(78.65F, 71.26F, 78.62F, 71.39F, 78.54F, 71.51F)
            curveTo(78.46F, 71.63F, 78.35F, 71.71F, 78.21F, 71.73F)
            curveTo(78.07F, 71.76F, 77.94F, 71.74F, 77.82F, 71.66F)
            lineTo(76.71F, 70.94F)
            curveTo(76.58F, 70.85F, 76.5F, 70.74F, 76.46F, 70.6F)
            curveTo(76.42F, 70.45F, 76.45F, 70.32F, 76.53F, 70.2F)

            moveTo(75.19F, 69.32F)
            curveTo(75.22F, 69.29F, 75.25F, 69.27F, 75.29F, 69.26F)
            curveTo(75.33F, 69.25F, 75.37F, 69.26F, 75.41F, 69.28F)
            lineTo(75.88F, 69.59F)
            curveTo(76.09F, 69.32F, 76.37F, 69.16F, 76.69F, 69.09F)
            curveTo(77.02F, 69.01F, 77.34F, 69.05F, 77.64F, 69.2F)
            curveTo(77.68F, 69.22F, 77.71F, 69.25F, 77.72F, 69.3F)
            curveTo(77.73F, 69.35F, 77.72F, 69.39F, 77.69F, 69.43F)
            curveTo(77.67F, 69.46F, 77.64F, 69.48F, 77.6F, 69.49F)
            curveTo(77.57F, 69.5F, 77.53F, 69.49F, 77.49F, 69.48F)
            curveTo(77.23F, 69.35F, 76.97F, 69.33F, 76.69F, 69.41F)
            curveTo(76.42F, 69.48F, 76.2F, 69.64F, 76.05F, 69.88F)
            curveTo(75.89F, 70.12F, 75.83F, 70.38F, 75.87F, 70.66F)
            curveTo(75.91F, 70.95F, 76.04F, 71.18F, 76.26F, 71.37F)
            curveTo(76.29F, 71.4F, 76.31F, 71.43F, 76.31F, 71.47F)
            curveTo(76.32F, 71.51F, 76.32F, 71.54F, 76.3F, 71.57F)
            curveTo(76.27F, 71.62F, 76.23F, 71.64F, 76.19F, 71.65F)
            curveTo(76.14F, 71.66F, 76.1F, 71.64F, 76.07F, 71.61F)
            curveTo(75.81F, 71.4F, 75.65F, 71.13F, 75.58F, 70.8F)
            curveTo(75.51F, 70.47F, 75.55F, 70.15F, 75.71F, 69.85F)
            lineTo(75.24F, 69.54F)
            curveTo(75.2F, 69.52F, 75.18F, 69.48F, 75.17F, 69.44F)
            curveTo(75.16F, 69.4F, 75.17F, 69.36F, 75.19F, 69.32F)

            moveTo(76.79F, 70.37F)
            curveTo(76.76F, 70.42F, 76.75F, 70.48F, 76.77F, 70.53F)
            curveTo(76.79F, 70.59F, 76.82F, 70.64F, 76.88F, 70.68F)
            lineTo(77.99F, 71.4F)
            curveTo(78.04F, 71.43F, 78.09F, 71.44F, 78.15F, 71.43F)
            curveTo(78.2F, 71.42F, 78.25F, 71.39F, 78.28F, 71.34F)
            curveTo(78.31F, 71.29F, 78.32F, 71.23F, 78.31F, 71.18F)
            curveTo(78.3F, 71.12F, 78.27F, 71.08F, 78.22F, 71.04F)
            lineTo(77.11F, 70.32F)
            curveTo(77.06F, 70.29F, 77.0F, 70.27F, 76.94F, 70.28F)
            curveTo(76.87F, 70.29F, 76.83F, 70.32F, 76.79F, 70.37F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(77.46F, 69.55F)
            lineTo(77.46F, 69.55F)
            curveTo(77.22F, 69.43F, 76.97F, 69.41F, 76.71F, 69.48F)
            curveTo(76.46F, 69.55F, 76.26F, 69.7F, 76.11F, 69.93F)
            curveTo(75.97F, 70.15F, 75.92F, 70.39F, 75.95F, 70.65F)
            curveTo(75.99F, 70.92F, 76.11F, 71.13F, 76.31F, 71.31F)
            lineTo(76.31F, 71.31F)
            lineTo(76.32F, 71.31F)
            curveTo(76.36F, 71.35F, 76.38F, 71.4F, 76.39F, 71.46F)
            curveTo(76.4F, 71.51F, 76.39F, 71.57F, 76.36F, 71.62F)
            curveTo(76.32F, 71.68F, 76.27F, 71.71F, 76.2F, 71.73F)
            curveTo(76.13F, 71.74F, 76.07F, 71.72F, 76.02F, 71.67F)
            curveTo(75.75F, 71.45F, 75.57F, 71.16F, 75.5F, 70.81F)
            curveTo(75.43F, 70.49F, 75.47F, 70.18F, 75.6F, 69.88F)
            lineTo(75.2F, 69.61F)
            curveTo(75.14F, 69.57F, 75.1F, 69.52F, 75.09F, 69.46F)
            curveTo(75.08F, 69.4F, 75.09F, 69.34F, 75.12F, 69.28F)
            curveTo(75.16F, 69.23F, 75.21F, 69.19F, 75.27F, 69.18F)
            curveTo(75.34F, 69.17F, 75.4F, 69.18F, 75.45F, 69.21F)
            lineTo(75.86F, 69.48F)
            curveTo(76.08F, 69.24F, 76.35F, 69.08F, 76.68F, 69.01F)
            curveTo(77.02F, 68.93F, 77.36F, 68.97F, 77.67F, 69.13F)
            curveTo(77.74F, 69.16F, 77.78F, 69.21F, 77.8F, 69.28F)
            curveTo(77.81F, 69.35F, 77.8F, 69.42F, 77.76F, 69.47F)
            curveTo(77.73F, 69.52F, 77.68F, 69.55F, 77.62F, 69.57F)
            curveTo(77.57F, 69.58F, 77.51F, 69.57F, 77.46F, 69.55F)
            lineTo(77.46F, 69.55F)

            moveTo(77.64F, 69.2F)
            curveTo(77.34F, 69.05F, 77.02F, 69.01F, 76.69F, 69.09F)
            curveTo(76.37F, 69.16F, 76.09F, 69.32F, 75.88F, 69.59F)
            lineTo(75.41F, 69.28F)
            curveTo(75.37F, 69.26F, 75.33F, 69.25F, 75.29F, 69.26F)
            curveTo(75.25F, 69.27F, 75.22F, 69.29F, 75.19F, 69.32F)
            curveTo(75.17F, 69.36F, 75.16F, 69.4F, 75.17F, 69.44F)
            curveTo(75.18F, 69.48F, 75.2F, 69.52F, 75.24F, 69.54F)
            lineTo(75.71F, 69.85F)
            curveTo(75.55F, 70.15F, 75.51F, 70.47F, 75.58F, 70.8F)
            curveTo(75.65F, 71.13F, 75.81F, 71.4F, 76.07F, 71.61F)
            curveTo(76.1F, 71.64F, 76.14F, 71.66F, 76.19F, 71.65F)
            curveTo(76.23F, 71.64F, 76.27F, 71.62F, 76.3F, 71.57F)
            curveTo(76.32F, 71.54F, 76.32F, 71.51F, 76.31F, 71.47F)
            curveTo(76.31F, 71.43F, 76.29F, 71.4F, 76.26F, 71.37F)
            curveTo(76.04F, 71.18F, 75.91F, 70.95F, 75.87F, 70.66F)
            curveTo(75.83F, 70.38F, 75.89F, 70.12F, 76.05F, 69.88F)
            curveTo(76.2F, 69.64F, 76.42F, 69.48F, 76.69F, 69.41F)
            curveTo(76.97F, 69.33F, 77.23F, 69.35F, 77.49F, 69.48F)
            curveTo(77.53F, 69.49F, 77.57F, 69.5F, 77.6F, 69.49F)
            curveTo(77.64F, 69.48F, 77.67F, 69.46F, 77.69F, 69.43F)
            curveTo(77.72F, 69.39F, 77.73F, 69.35F, 77.72F, 69.3F)
            curveTo(77.71F, 69.25F, 77.68F, 69.22F, 77.64F, 69.2F)

            moveTo(78.13F, 71.35F)
            curveTo(78.16F, 71.34F, 78.19F, 71.33F, 78.21F, 71.29F)
            curveTo(78.23F, 71.26F, 78.24F, 71.23F, 78.23F, 71.19F)
            curveTo(78.22F, 71.16F, 78.21F, 71.13F, 78.17F, 71.11F)
            lineTo(77.07F, 70.39F)
            curveTo(77.03F, 70.36F, 76.99F, 70.35F, 76.94F, 70.36F)
            curveTo(76.91F, 70.36F, 76.88F, 70.38F, 76.86F, 70.41F)
            curveTo(76.84F, 70.44F, 76.83F, 70.47F, 76.85F, 70.51F)
            curveTo(76.86F, 70.55F, 76.88F, 70.58F, 76.92F, 70.61F)
            lineTo(78.03F, 71.33F)
            curveTo(78.06F, 71.35F, 78.09F, 71.36F, 78.13F, 71.35F)

            moveTo(76.46F, 70.15F)
            curveTo(76.55F, 70.01F, 76.69F, 69.92F, 76.85F, 69.89F)
            curveTo(77.02F, 69.87F, 77.18F, 69.9F, 77.32F, 70.0F)
            lineTo(78.43F, 70.72F)
            curveTo(78.57F, 70.81F, 78.66F, 70.94F, 78.69F, 71.1F)
            curveTo(78.73F, 71.26F, 78.7F, 71.41F, 78.61F, 71.55F)
            curveTo(78.52F, 71.69F, 78.39F, 71.78F, 78.23F, 71.81F)
            curveTo(78.07F, 71.85F, 77.91F, 71.82F, 77.77F, 71.73F)
            lineTo(76.67F, 71.0F)
            curveTo(76.52F, 70.91F, 76.42F, 70.78F, 76.38F, 70.62F)
            curveTo(76.34F, 70.45F, 76.37F, 70.29F, 76.46F, 70.15F)

            moveTo(76.46F, 70.6F)
            curveTo(76.5F, 70.74F, 76.58F, 70.85F, 76.71F, 70.94F)
            lineTo(77.82F, 71.66F)
            curveTo(77.94F, 71.74F, 78.07F, 71.76F, 78.21F, 71.73F)
            curveTo(78.35F, 71.71F, 78.46F, 71.63F, 78.54F, 71.51F)
            curveTo(78.62F, 71.39F, 78.65F, 71.26F, 78.62F, 71.11F)
            curveTo(78.59F, 70.97F, 78.51F, 70.86F, 78.39F, 70.78F)
            lineTo(77.28F, 70.06F)
            curveTo(77.15F, 69.98F, 77.01F, 69.95F, 76.87F, 69.97F)
            curveTo(76.72F, 70.0F, 76.61F, 70.07F, 76.53F, 70.2F)
            curveTo(76.45F, 70.32F, 76.42F, 70.45F, 76.46F, 70.6F)

            moveTo(76.77F, 70.53F)
            curveTo(76.75F, 70.48F, 76.76F, 70.42F, 76.79F, 70.37F)
            curveTo(76.83F, 70.32F, 76.87F, 70.29F, 76.94F, 70.28F)
            curveTo(77.0F, 70.27F, 77.06F, 70.29F, 77.11F, 70.32F)
            lineTo(78.22F, 71.04F)
            curveTo(78.27F, 71.08F, 78.3F, 71.12F, 78.31F, 71.18F)
            curveTo(78.32F, 71.23F, 78.31F, 71.29F, 78.28F, 71.34F)
            curveTo(78.25F, 71.39F, 78.2F, 71.42F, 78.15F, 71.43F)
            curveTo(78.09F, 71.44F, 78.04F, 71.43F, 77.99F, 71.4F)
            lineTo(76.88F, 70.68F)
            curveTo(76.82F, 70.64F, 76.79F, 70.59F, 76.77F, 70.53F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(60.0F, 68.5F)
            lineTo(60.5F, 70.0F)
            verticalLineTo(70.5F)
            lineTo(60.0F, 71.5F)
            lineTo(61.5F, 72.0F)
            lineTo(63.31F, 71.92F)
            lineTo(63.0F, 73.0F)
            horizontalLineTo(64.0F)
            curveTo(64.5F, 73.17F, 65.5F, 73.6F, 65.5F, 74.0F)
            curveTo(65.5F, 74.4F, 66.83F, 74.17F, 67.5F, 74.0F)
            lineTo(70.5F, 73.0F)
            horizontalLineTo(72.0F)
            lineTo(72.5F, 71.5F)
            lineTo(72.0F, 70.0F)
            verticalLineTo(68.0F)
            lineTo(73.5F, 66.25F)
            lineTo(71.5F, 64.5F)
            lineTo(71.0F, 63.0F)
            horizontalLineTo(69.5F)
            lineTo(69.0F, 64.0F)
            lineTo(68.0F, 64.5F)
            lineTo(67.0F, 63.0F)
            lineTo(66.0F, 62.0F)
            curveTo(65.5F, 62.33F, 64.6F, 63.0F, 65.0F, 63.0F)
            curveTo(65.4F, 63.0F, 65.17F, 64.33F, 65.0F, 65.0F)
            lineTo(63.5F, 64.0F)
            lineTo(62.5F, 63.5F)
            lineTo(60.5F, 66.25F)
            lineTo(60.0F, 68.5F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(66.06F, 61.36F)
            lineTo(67.39F, 62.68F)
            lineTo(68.17F, 63.86F)
            lineTo(68.63F, 63.63F)
            lineTo(69.19F, 62.5F)
            horizontalLineTo(71.36F)
            lineTo(71.93F, 64.21F)
            lineTo(74.2F, 66.2F)
            lineTo(72.5F, 68.18F)
            verticalLineTo(69.92F)
            lineTo(73.03F, 71.5F)
            lineTo(72.36F, 73.5F)
            horizontalLineTo(70.58F)
            lineTo(67.64F, 74.48F)
            lineTo(67.62F, 74.49F)
            curveTo(67.27F, 74.57F, 66.74F, 74.68F, 66.28F, 74.71F)
            curveTo(66.06F, 74.73F, 65.82F, 74.73F, 65.6F, 74.68F)
            curveTo(65.5F, 74.65F, 65.36F, 74.61F, 65.24F, 74.51F)
            curveTo(65.11F, 74.4F, 65.02F, 74.25F, 65.0F, 74.07F)
            curveTo(65.0F, 74.07F, 64.99F, 74.06F, 64.99F, 74.06F)
            curveTo(64.93F, 74.0F, 64.83F, 73.93F, 64.69F, 73.85F)
            curveTo(64.44F, 73.7F, 64.14F, 73.58F, 63.92F, 73.5F)
            horizontalLineTo(62.34F)
            lineTo(62.64F, 72.45F)
            lineTo(61.43F, 72.5F)
            lineTo(59.29F, 71.79F)
            lineTo(60.0F, 70.38F)
            verticalLineTo(70.08F)
            lineTo(59.48F, 68.53F)
            lineTo(60.03F, 66.04F)
            lineTo(62.34F, 62.86F)
            lineTo(63.75F, 63.57F)
            lineTo(64.66F, 64.17F)
            curveTo(64.68F, 64.02F, 64.7F, 63.86F, 64.71F, 63.72F)
            curveTo(64.72F, 63.6F, 64.72F, 63.51F, 64.72F, 63.44F)
            curveTo(64.64F, 63.41F, 64.56F, 63.35F, 64.49F, 63.25F)
            curveTo(64.31F, 62.98F, 64.45F, 62.71F, 64.48F, 62.66F)
            curveTo(64.56F, 62.5F, 64.72F, 62.35F, 64.83F, 62.25F)
            curveTo(65.1F, 62.02F, 65.47F, 61.75F, 65.72F, 61.58F)
            lineTo(66.06F, 61.36F)

            moveTo(65.61F, 62.9F)
            curveTo(65.64F, 62.97F, 65.67F, 63.05F, 65.68F, 63.1F)
            curveTo(65.73F, 63.32F, 65.73F, 63.56F, 65.71F, 63.78F)
            curveTo(65.68F, 64.24F, 65.57F, 64.77F, 65.49F, 65.12F)
            lineTo(65.31F, 65.81F)
            lineTo(63.25F, 64.43F)
            lineTo(62.66F, 64.14F)
            lineTo(60.97F, 66.46F)
            lineTo(60.52F, 68.47F)
            lineTo(61.0F, 69.92F)
            verticalLineTo(70.62F)
            lineTo(60.71F, 71.21F)
            lineTo(61.57F, 71.5F)
            lineTo(63.98F, 71.39F)
            lineTo(63.66F, 72.5F)
            horizontalLineTo(64.08F)
            lineTo(64.16F, 72.53F)
            curveTo(64.43F, 72.62F, 64.84F, 72.78F, 65.19F, 72.98F)
            curveTo(65.36F, 73.08F, 65.54F, 73.2F, 65.69F, 73.34F)
            curveTo(65.77F, 73.43F, 65.88F, 73.55F, 65.95F, 73.72F)
            curveTo(66.01F, 73.72F, 66.1F, 73.72F, 66.22F, 73.71F)
            curveTo(66.58F, 73.69F, 67.04F, 73.6F, 67.36F, 73.52F)
            lineTo(70.42F, 72.5F)
            horizontalLineTo(71.64F)
            lineTo(71.97F, 71.5F)
            lineTo(71.5F, 70.08F)
            verticalLineTo(67.82F)
            lineTo(72.8F, 66.3F)
            lineTo(71.07F, 64.79F)
            lineTo(70.64F, 63.5F)
            horizontalLineTo(69.81F)
            lineTo(69.37F, 64.37F)
            lineTo(67.83F, 65.14F)
            lineTo(66.61F, 63.32F)
            lineTo(65.94F, 62.65F)
            curveTo(65.83F, 62.73F, 65.71F, 62.82F, 65.61F, 62.9F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(68.84F, 66.1F)
            lineTo(67.4F, 67.3F)
            lineTo(66.6F, 66.7F)
            lineTo(68.17F, 65.39F)
            lineTo(68.84F, 66.1F)

            moveTo(69.93F, 68.22F)
            lineTo(69.51F, 66.1F)
            lineTo(70.49F, 65.9F)
            lineTo(71.07F, 68.78F)
            lineTo(69.72F, 69.45F)
            lineTo(69.28F, 68.55F)
            lineTo(69.93F, 68.22F)

            moveTo(63.9F, 66.39F)
            lineTo(65.22F, 67.05F)
            lineTo(64.78F, 67.95F)
            lineTo(64.1F, 67.61F)
            lineTo(62.85F, 68.85F)
            lineTo(62.15F, 68.15F)
            lineTo(63.9F, 66.39F)

            moveTo(67.78F, 68.92F)
            lineTo(66.19F, 69.98F)
            lineTo(63.08F, 70.49F)
            lineTo(62.92F, 69.51F)
            lineTo(65.81F, 69.02F)
            lineTo(67.22F, 68.08F)
            lineTo(67.78F, 68.92F)

            moveTo(68.99F, 69.38F)
            lineTo(69.62F, 71.9F)
            lineTo(66.08F, 72.49F)
            lineTo(65.92F, 71.51F)
            lineTo(68.38F, 71.1F)
            lineTo(68.01F, 69.62F)
            lineTo(68.99F, 69.38F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(49.0F, 77.5F)
            lineTo(49.5F, 79.0F)
            verticalLineTo(79.5F)
            lineTo(49.0F, 80.5F)
            lineTo(50.5F, 81.0F)
            lineTo(52.31F, 80.92F)
            lineTo(52.0F, 82.0F)
            horizontalLineTo(53.0F)
            curveTo(53.5F, 82.17F, 54.5F, 82.6F, 54.5F, 83.0F)
            curveTo(54.5F, 83.4F, 55.83F, 83.17F, 56.5F, 83.0F)
            lineTo(59.5F, 82.0F)
            horizontalLineTo(61.0F)
            lineTo(61.5F, 80.5F)
            lineTo(61.0F, 79.0F)
            verticalLineTo(77.0F)
            lineTo(62.5F, 75.25F)
            lineTo(60.5F, 73.5F)
            lineTo(60.0F, 72.0F)
            horizontalLineTo(58.5F)
            lineTo(58.0F, 73.0F)
            lineTo(57.0F, 73.5F)
            lineTo(56.0F, 72.0F)
            lineTo(55.0F, 71.0F)
            curveTo(54.5F, 71.33F, 53.6F, 72.0F, 54.0F, 72.0F)
            curveTo(54.4F, 72.0F, 54.17F, 73.33F, 54.0F, 74.0F)
            lineTo(52.5F, 73.0F)
            lineTo(51.5F, 72.5F)
            lineTo(49.5F, 75.25F)
            lineTo(49.0F, 77.5F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(55.06F, 70.36F)
            lineTo(56.39F, 71.68F)
            lineTo(57.17F, 72.86F)
            lineTo(57.63F, 72.63F)
            lineTo(58.19F, 71.5F)
            horizontalLineTo(60.36F)
            lineTo(60.93F, 73.21F)
            lineTo(63.2F, 75.2F)
            lineTo(61.5F, 77.18F)
            verticalLineTo(78.92F)
            lineTo(62.03F, 80.5F)
            lineTo(61.36F, 82.5F)
            horizontalLineTo(59.58F)
            lineTo(56.64F, 83.48F)
            lineTo(56.62F, 83.49F)
            curveTo(56.27F, 83.57F, 55.74F, 83.68F, 55.28F, 83.71F)
            curveTo(55.06F, 83.73F, 54.82F, 83.73F, 54.6F, 83.68F)
            curveTo(54.5F, 83.65F, 54.36F, 83.61F, 54.24F, 83.51F)
            curveTo(54.11F, 83.4F, 54.02F, 83.25F, 54.0F, 83.07F)
            curveTo(54.0F, 83.07F, 53.99F, 83.06F, 53.99F, 83.06F)
            curveTo(53.93F, 83.0F, 53.83F, 82.93F, 53.69F, 82.85F)
            curveTo(53.44F, 82.7F, 53.14F, 82.58F, 52.92F, 82.5F)
            horizontalLineTo(51.34F)
            lineTo(51.64F, 81.45F)
            lineTo(50.43F, 81.5F)
            lineTo(48.29F, 80.79F)
            lineTo(49.0F, 79.38F)
            verticalLineTo(79.08F)
            lineTo(48.48F, 77.53F)
            lineTo(49.03F, 75.04F)
            lineTo(51.34F, 71.86F)
            lineTo(52.75F, 72.57F)
            lineTo(53.66F, 73.17F)
            curveTo(53.68F, 73.02F, 53.7F, 72.86F, 53.71F, 72.72F)
            curveTo(53.72F, 72.6F, 53.72F, 72.51F, 53.72F, 72.44F)
            curveTo(53.64F, 72.41F, 53.56F, 72.35F, 53.49F, 72.25F)
            curveTo(53.31F, 71.98F, 53.45F, 71.71F, 53.48F, 71.66F)
            curveTo(53.56F, 71.5F, 53.72F, 71.35F, 53.83F, 71.25F)
            curveTo(54.1F, 71.02F, 54.47F, 70.75F, 54.72F, 70.58F)
            lineTo(55.06F, 70.36F)

            moveTo(54.61F, 71.9F)
            curveTo(54.64F, 71.97F, 54.67F, 72.05F, 54.68F, 72.1F)
            curveTo(54.73F, 72.32F, 54.73F, 72.56F, 54.71F, 72.78F)
            curveTo(54.68F, 73.24F, 54.57F, 73.77F, 54.49F, 74.12F)
            lineTo(54.31F, 74.81F)
            lineTo(52.25F, 73.43F)
            lineTo(51.66F, 73.14F)
            lineTo(49.97F, 75.46F)
            lineTo(49.52F, 77.47F)
            lineTo(50.0F, 78.92F)
            verticalLineTo(79.62F)
            lineTo(49.71F, 80.21F)
            lineTo(50.57F, 80.5F)
            lineTo(52.98F, 80.39F)
            lineTo(52.66F, 81.5F)
            horizontalLineTo(53.08F)
            lineTo(53.16F, 81.53F)
            curveTo(53.43F, 81.62F, 53.84F, 81.78F, 54.19F, 81.98F)
            curveTo(54.36F, 82.08F, 54.54F, 82.2F, 54.69F, 82.34F)
            curveTo(54.77F, 82.43F, 54.88F, 82.55F, 54.95F, 82.72F)
            curveTo(55.01F, 82.72F, 55.1F, 82.72F, 55.22F, 82.71F)
            curveTo(55.58F, 82.69F, 56.04F, 82.6F, 56.36F, 82.52F)
            lineTo(59.42F, 81.5F)
            horizontalLineTo(60.64F)
            lineTo(60.97F, 80.5F)
            lineTo(60.5F, 79.08F)
            verticalLineTo(76.82F)
            lineTo(61.8F, 75.3F)
            lineTo(60.07F, 73.79F)
            lineTo(59.64F, 72.5F)
            horizontalLineTo(58.81F)
            lineTo(58.37F, 73.37F)
            lineTo(56.83F, 74.14F)
            lineTo(55.61F, 72.32F)
            lineTo(54.94F, 71.65F)
            curveTo(54.83F, 71.73F, 54.71F, 71.82F, 54.61F, 71.9F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(57.0F, 74.5F)
            lineTo(56.4F, 76.3F)
            lineTo(55.6F, 75.7F)
            lineTo(56.05F, 74.14F)
            lineTo(57.0F, 74.5F)

            moveTo(59.07F, 76.3F)
            lineTo(58.51F, 75.1F)
            lineTo(59.49F, 74.9F)
            lineTo(60.07F, 77.78F)
            lineTo(58.72F, 78.45F)
            lineTo(58.28F, 77.55F)
            lineTo(59.07F, 76.3F)

            moveTo(52.9F, 75.39F)
            lineTo(54.22F, 76.05F)
            lineTo(53.78F, 76.95F)
            lineTo(53.1F, 76.61F)
            lineTo(51.85F, 77.85F)
            lineTo(51.15F, 77.15F)
            lineTo(52.9F, 75.39F)

            moveTo(56.78F, 77.92F)
            lineTo(55.19F, 78.98F)
            lineTo(52.08F, 79.49F)
            lineTo(51.92F, 78.51F)
            lineTo(54.82F, 77.55F)
            lineTo(56.22F, 77.08F)
            lineTo(56.78F, 77.92F)

            moveTo(57.99F, 78.38F)
            lineTo(58.62F, 80.9F)
            lineTo(55.08F, 81.49F)
            lineTo(54.92F, 80.51F)
            lineTo(57.17F, 80.59F)
            lineTo(57.01F, 78.62F)
            lineTo(57.99F, 78.38F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF8080)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(25.0F, 65.5F)
            lineTo(25.5F, 67.0F)
            verticalLineTo(67.5F)
            lineTo(25.0F, 68.5F)
            lineTo(26.5F, 69.0F)
            lineTo(28.31F, 68.92F)
            lineTo(28.0F, 70.0F)
            horizontalLineTo(29.0F)
            curveTo(29.5F, 70.17F, 30.5F, 70.6F, 30.5F, 71.0F)
            curveTo(30.5F, 71.4F, 31.83F, 71.17F, 32.5F, 71.0F)
            lineTo(35.5F, 70.0F)
            horizontalLineTo(37.0F)
            lineTo(37.5F, 68.5F)
            lineTo(37.0F, 67.0F)
            verticalLineTo(65.0F)
            lineTo(38.5F, 63.25F)
            lineTo(36.5F, 61.5F)
            lineTo(36.0F, 60.0F)
            horizontalLineTo(34.5F)
            lineTo(34.0F, 61.0F)
            lineTo(33.0F, 61.5F)
            lineTo(32.0F, 60.0F)
            lineTo(31.0F, 59.0F)
            curveTo(30.5F, 59.33F, 29.6F, 60.0F, 30.0F, 60.0F)
            curveTo(30.4F, 60.0F, 30.17F, 61.33F, 30.0F, 62.0F)
            lineTo(28.5F, 61.0F)
            lineTo(27.5F, 60.5F)
            lineTo(25.5F, 63.25F)
            lineTo(25.0F, 65.5F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(31.06F, 58.36F)
            lineTo(32.39F, 59.68F)
            lineTo(33.17F, 60.86F)
            lineTo(33.63F, 60.63F)
            lineTo(34.19F, 59.5F)
            horizontalLineTo(36.36F)
            lineTo(36.93F, 61.21F)
            lineTo(39.2F, 63.2F)
            lineTo(37.5F, 65.18F)
            verticalLineTo(66.92F)
            lineTo(38.03F, 68.5F)
            lineTo(37.36F, 70.5F)
            horizontalLineTo(35.58F)
            lineTo(32.64F, 71.48F)
            lineTo(32.62F, 71.49F)
            curveTo(32.27F, 71.57F, 31.74F, 71.68F, 31.28F, 71.71F)
            curveTo(31.06F, 71.73F, 30.82F, 71.73F, 30.6F, 71.68F)
            curveTo(30.5F, 71.65F, 30.36F, 71.61F, 30.24F, 71.51F)
            curveTo(30.11F, 71.4F, 30.02F, 71.25F, 30.0F, 71.07F)
            curveTo(30.0F, 71.07F, 29.99F, 71.06F, 29.99F, 71.06F)
            curveTo(29.93F, 71.0F, 29.83F, 70.93F, 29.69F, 70.85F)
            curveTo(29.44F, 70.7F, 29.14F, 70.58F, 28.92F, 70.5F)
            horizontalLineTo(27.34F)
            lineTo(27.64F, 69.45F)
            lineTo(26.43F, 69.5F)
            lineTo(24.3F, 68.79F)
            lineTo(25.0F, 67.38F)
            verticalLineTo(67.08F)
            lineTo(24.48F, 65.53F)
            lineTo(25.03F, 63.04F)
            lineTo(27.34F, 59.86F)
            lineTo(28.75F, 60.57F)
            lineTo(29.66F, 61.17F)
            curveTo(29.68F, 61.02F, 29.7F, 60.86F, 29.71F, 60.72F)
            curveTo(29.72F, 60.6F, 29.72F, 60.51F, 29.72F, 60.44F)
            curveTo(29.64F, 60.41F, 29.56F, 60.35F, 29.49F, 60.25F)
            curveTo(29.31F, 59.98F, 29.45F, 59.71F, 29.48F, 59.66F)
            curveTo(29.56F, 59.5F, 29.72F, 59.35F, 29.83F, 59.25F)
            curveTo(30.09F, 59.02F, 30.47F, 58.75F, 30.72F, 58.58F)
            lineTo(31.06F, 58.36F)

            moveTo(30.61F, 59.9F)
            curveTo(30.64F, 59.97F, 30.67F, 60.05F, 30.68F, 60.1F)
            curveTo(30.73F, 60.32F, 30.73F, 60.56F, 30.71F, 60.78F)
            curveTo(30.68F, 61.24F, 30.57F, 61.77F, 30.49F, 62.12F)
            lineTo(30.31F, 62.81F)
            lineTo(28.25F, 61.43F)
            lineTo(27.66F, 61.14F)
            lineTo(25.97F, 63.46F)
            lineTo(25.52F, 65.47F)
            lineTo(26.0F, 66.92F)
            verticalLineTo(67.62F)
            lineTo(25.7F, 68.21F)
            lineTo(26.57F, 68.5F)
            lineTo(28.98F, 68.39F)
            lineTo(28.66F, 69.5F)
            horizontalLineTo(29.08F)
            lineTo(29.16F, 69.53F)
            curveTo(29.43F, 69.62F, 29.84F, 69.78F, 30.19F, 69.98F)
            curveTo(30.36F, 70.08F, 30.54F, 70.2F, 30.69F, 70.34F)
            curveTo(30.77F, 70.43F, 30.88F, 70.55F, 30.95F, 70.72F)
            curveTo(31.01F, 70.72F, 31.1F, 70.72F, 31.22F, 70.71F)
            curveTo(31.58F, 70.69F, 32.04F, 70.6F, 32.36F, 70.52F)
            lineTo(35.42F, 69.5F)
            horizontalLineTo(36.64F)
            lineTo(36.97F, 68.5F)
            lineTo(36.5F, 67.08F)
            verticalLineTo(64.82F)
            lineTo(37.8F, 63.3F)
            lineTo(36.07F, 61.79F)
            lineTo(35.64F, 60.5F)
            horizontalLineTo(34.81F)
            lineTo(34.37F, 61.37F)
            lineTo(32.83F, 62.14F)
            lineTo(31.61F, 60.32F)
            lineTo(30.94F, 59.65F)
            curveTo(30.83F, 59.73F, 30.71F, 59.82F, 30.61F, 59.9F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFE53F3F)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(33.9F, 62.3F)
            lineTo(32.4F, 64.3F)
            lineTo(31.6F, 63.7F)
            lineTo(32.64F, 62.71F)
            lineTo(33.9F, 62.3F)

            moveTo(34.93F, 65.22F)
            lineTo(34.51F, 63.1F)
            lineTo(35.49F, 62.9F)
            lineTo(36.07F, 65.78F)
            lineTo(34.72F, 66.45F)
            lineTo(34.28F, 65.55F)
            lineTo(34.93F, 65.22F)

            moveTo(28.9F, 63.39F)
            lineTo(30.22F, 64.05F)
            lineTo(29.78F, 64.95F)
            lineTo(29.1F, 64.61F)
            lineTo(27.85F, 65.85F)
            lineTo(27.15F, 65.15F)
            lineTo(28.9F, 63.39F)

            moveTo(32.78F, 65.92F)
            lineTo(31.19F, 66.98F)
            lineTo(28.08F, 67.49F)
            lineTo(27.92F, 66.51F)
            lineTo(30.81F, 66.02F)
            lineTo(32.22F, 65.08F)
            lineTo(32.78F, 65.92F)

            moveTo(33.99F, 66.38F)
            lineTo(34.62F, 68.9F)
            lineTo(31.08F, 69.49F)
            lineTo(30.92F, 68.51F)
            lineTo(33.38F, 68.1F)
            lineTo(33.01F, 66.62F)
            lineTo(33.99F, 66.38F)
            close()
        }.build()
        return _errorFolderPlaceholder!!
    }
private var _errorFolderPlaceholder: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconErrorFolderPlaceholderPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.ErrorFolderPlaceholder, contentDescription = null)
    }
}