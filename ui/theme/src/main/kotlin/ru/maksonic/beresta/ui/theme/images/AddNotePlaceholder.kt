package ru.maksonic.beresta.ui.theme.images

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
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
 * @Author maksonic on 24.05.2023
 */
val AppImage.AddNotePlaceholder: ImageVector
    @Composable get() {

        _addNotePlaceholder = ImageVector.Builder(
            name = "AddNotePlaceholder",
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
            moveTo(100.0F, 92.13F)
            curveTo(100.0F, 95.13F, 77.61F, 97.57F, 49.99F, 97.57F)
            curveTo(22.37F, 97.57F, -0.01F, 95.13F, -0.01F, 92.13F)
            curveTo(-0.01F, 89.13F, 22.37F, 86.69F, 49.99F, 86.69F)
            curveTo(77.61F, 86.69F, 100.0F, 89.13F, 100.0F, 92.13F)
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
            moveTo(16.65F, 46.83F)
            curveTo(16.65F, 44.03F, 18.93F, 41.76F, 21.73F, 41.76F)
            horizontalLineTo(40.14F)
            curveTo(41.35F, 41.76F, 42.53F, 42.19F, 43.45F, 42.98F)
            lineTo(45.67F, 44.89F)
            curveTo(46.59F, 45.68F, 47.76F, 46.11F, 48.97F, 46.11F)
            horizontalLineTo(76.81F)
            curveTo(79.61F, 46.11F, 81.88F, 48.38F, 81.88F, 51.18F)
            verticalLineTo(88.14F)
            curveTo(81.88F, 90.95F, 79.61F, 93.22F, 76.81F, 93.22F)
            horizontalLineTo(21.73F)
            curveTo(18.93F, 93.22F, 16.65F, 90.95F, 16.65F, 88.14F)
            verticalLineTo(46.83F)
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
            moveTo(22.76F, 56.03F)
            curveTo(23.21F, 53.64F, 25.3F, 51.91F, 27.74F, 51.91F)
            horizontalLineTo(82.78F)
            curveTo(85.91F, 51.91F, 88.3F, 54.72F, 87.78F, 57.81F)
            lineTo(82.59F, 88.98F)
            curveTo(82.18F, 91.42F, 80.06F, 93.22F, 77.58F, 93.22F)
            horizontalLineTo(21.29F)
            curveTo(18.73F, 93.22F, 16.65F, 91.14F, 16.65F, 88.58F)
            curveTo(16.65F, 88.29F, 16.68F, 88.0F, 16.74F, 87.71F)
            lineTo(22.76F, 56.03F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f2),
            fillAlpha = 0.65F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(51.91F, 87.4F)
            curveTo(60.27F, 87.4F, 67.05F, 80.63F, 67.05F, 72.28F)
            curveTo(67.05F, 63.93F, 60.27F, 57.16F, 51.91F, 57.16F)
            curveTo(43.55F, 57.16F, 36.77F, 63.93F, 36.77F, 72.28F)
            curveTo(36.77F, 80.63F, 43.55F, 87.4F, 51.91F, 87.4F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.f1),
            fillAlpha = 0.65F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(51.91F, 66.23F)
            verticalLineTo(78.33F)

            moveTo(57.97F, 72.28F)
            horizontalLineTo(45.85F)
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
            moveTo(51.91F, 64.87F)
            curveTo(52.66F, 64.87F, 53.27F, 65.48F, 53.27F, 66.23F)
            verticalLineTo(70.92F)
            horizontalLineTo(57.97F)
            curveTo(58.72F, 70.92F, 59.32F, 71.53F, 59.32F, 72.28F)
            curveTo(59.32F, 73.03F, 58.72F, 73.64F, 57.97F, 73.64F)
            horizontalLineTo(53.27F)
            verticalLineTo(78.33F)
            curveTo(53.27F, 79.08F, 52.66F, 79.69F, 51.91F, 79.69F)
            curveTo(51.16F, 79.69F, 50.55F, 79.08F, 50.55F, 78.33F)
            verticalLineTo(73.64F)
            horizontalLineTo(45.85F)
            curveTo(45.1F, 73.64F, 44.49F, 73.03F, 44.49F, 72.28F)
            curveTo(44.49F, 71.53F, 45.1F, 70.92F, 45.85F, 70.92F)
            horizontalLineTo(50.55F)
            verticalLineTo(66.23F)
            curveTo(50.55F, 65.48F, 51.16F, 64.87F, 51.91F, 64.87F)
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
            moveTo(30.36F, 6.13F)
            lineTo(38.74F, 22.46F)
            curveTo(39.66F, 24.27F, 38.94F, 26.49F, 37.13F, 27.42F)
            lineTo(22.78F, 34.78F)
            curveTo(20.97F, 35.71F, 18.74F, 35.0F, 17.82F, 33.19F)
            lineTo(7.46F, 13.0F)
            curveTo(6.53F, 11.19F, 7.26F, 8.97F, 9.07F, 8.04F)
            lineTo(19.53F, 2.68F)
            lineTo(30.36F, 6.13F)
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
            moveTo(19.83F, 30.51F)
            curveTo(20.21F, 31.25F, 21.11F, 31.54F, 21.85F, 31.16F)
            lineTo(34.73F, 24.55F)
            curveTo(35.47F, 24.17F, 35.76F, 23.27F, 35.39F, 22.53F)
            curveTo(35.01F, 21.79F, 34.1F, 21.5F, 33.36F, 21.88F)
            lineTo(20.49F, 28.49F)
            curveTo(19.74F, 28.87F, 19.45F, 29.77F, 19.83F, 30.51F)
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
            moveTo(14.35F, 19.83F)
            curveTo(14.73F, 20.57F, 15.64F, 20.86F, 16.38F, 20.48F)
            lineTo(29.25F, 13.88F)
            curveTo(29.99F, 13.5F, 30.29F, 12.59F, 29.91F, 11.85F)
            curveTo(29.53F, 11.12F, 28.62F, 10.83F, 27.88F, 11.21F)
            lineTo(15.01F, 17.81F)
            curveTo(14.27F, 18.19F, 13.97F, 19.1F, 14.35F, 19.83F)
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
            moveTo(11.61F, 14.49F)
            curveTo(11.99F, 15.23F, 12.9F, 15.52F, 13.64F, 15.14F)
            lineTo(19.27F, 12.25F)
            curveTo(20.01F, 11.87F, 20.31F, 10.97F, 19.93F, 10.23F)
            curveTo(19.55F, 9.49F, 18.64F, 9.2F, 17.9F, 9.58F)
            lineTo(12.27F, 12.47F)
            curveTo(11.53F, 12.85F, 11.24F, 13.76F, 11.61F, 14.49F)
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
            moveTo(17.09F, 25.17F)
            curveTo(17.47F, 25.91F, 18.38F, 26.2F, 19.12F, 25.82F)
            lineTo(31.99F, 19.22F)
            curveTo(32.73F, 18.84F, 33.03F, 17.93F, 32.65F, 17.19F)
            curveTo(32.27F, 16.46F, 31.36F, 16.17F, 30.62F, 16.55F)
            lineTo(17.75F, 23.15F)
            curveTo(17.01F, 23.53F, 16.71F, 24.43F, 17.09F, 25.17F)
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
            moveTo(30.36F, 6.13F)
            lineTo(26.47F, 8.12F)
            curveTo(24.66F, 9.05F, 22.44F, 8.34F, 21.51F, 6.54F)
            lineTo(19.53F, 2.68F)
            lineTo(30.36F, 6.13F)
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
            moveTo(93.22F, 25.36F)
            lineTo(88.06F, 41.2F)
            curveTo(87.49F, 42.95F, 85.65F, 43.92F, 83.95F, 43.36F)
            lineTo(70.52F, 38.99F)
            curveTo(68.82F, 38.44F, 67.9F, 36.57F, 68.47F, 34.82F)
            lineTo(74.84F, 15.24F)
            curveTo(75.41F, 13.49F, 77.25F, 12.52F, 78.95F, 13.08F)
            lineTo(88.75F, 16.26F)
            lineTo(93.22F, 25.36F)
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
            moveTo(93.22F, 25.36F)
            lineTo(89.58F, 24.18F)
            curveTo(87.88F, 23.62F, 86.96F, 21.76F, 87.53F, 20.01F)
            lineTo(88.75F, 16.26F)
            lineTo(93.22F, 25.36F)
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
            moveTo(76.67F, 22.94F)
            curveTo(77.09F, 21.65F, 78.48F, 20.95F, 79.78F, 21.37F)
            lineTo(86.83F, 23.67F)
            curveTo(88.13F, 24.09F, 88.84F, 25.48F, 88.43F, 26.76F)
            lineTo(86.15F, 33.76F)
            curveTo(85.73F, 35.05F, 84.34F, 35.75F, 83.04F, 35.33F)
            lineTo(75.98F, 33.03F)
            curveTo(74.69F, 32.61F, 73.97F, 31.22F, 74.39F, 29.94F)
            lineTo(76.67F, 22.94F)
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
            moveTo(78.27F, 30.85F)
            curveTo(78.15F, 30.82F, 78.07F, 30.75F, 78.02F, 30.65F)
            curveTo(77.97F, 30.54F, 77.96F, 30.44F, 78.0F, 30.33F)
            lineTo(79.81F, 24.75F)
            curveTo(79.85F, 24.63F, 79.92F, 24.55F, 80.02F, 24.5F)
            curveTo(80.13F, 24.45F, 80.23F, 24.44F, 80.35F, 24.48F)
            curveTo(80.46F, 24.52F, 80.55F, 24.59F, 80.6F, 24.69F)
            curveTo(80.65F, 24.79F, 80.66F, 24.9F, 80.62F, 25.01F)
            lineTo(78.8F, 30.59F)
            curveTo(78.77F, 30.7F, 78.7F, 30.78F, 78.6F, 30.83F)
            curveTo(78.49F, 30.88F, 78.38F, 30.89F, 78.27F, 30.85F)

            moveTo(79.79F, 33.7F)
            curveTo(79.68F, 33.66F, 79.6F, 33.59F, 79.54F, 33.49F)
            curveTo(79.49F, 33.39F, 79.49F, 33.28F, 79.52F, 33.17F)
            lineTo(82.72F, 23.34F)
            curveTo(82.76F, 23.23F, 82.83F, 23.15F, 82.93F, 23.1F)
            curveTo(83.03F, 23.04F, 83.14F, 23.04F, 83.26F, 23.08F)
            curveTo(83.37F, 23.11F, 83.45F, 23.18F, 83.5F, 23.28F)
            curveTo(83.56F, 23.38F, 83.56F, 23.49F, 83.53F, 23.6F)
            lineTo(80.33F, 33.43F)
            curveTo(80.29F, 33.55F, 80.22F, 33.63F, 80.12F, 33.68F)
            curveTo(80.02F, 33.73F, 79.91F, 33.74F, 79.79F, 33.7F)

            moveTo(76.75F, 28.01F)
            curveTo(76.63F, 27.97F, 76.55F, 27.9F, 76.5F, 27.8F)
            curveTo(76.45F, 27.7F, 76.44F, 27.59F, 76.47F, 27.48F)
            lineTo(76.91F, 26.15F)
            curveTo(76.94F, 26.04F, 77.01F, 25.96F, 77.12F, 25.9F)
            curveTo(77.22F, 25.85F, 77.33F, 25.85F, 77.44F, 25.88F)
            curveTo(77.56F, 25.92F, 77.64F, 25.99F, 77.69F, 26.09F)
            curveTo(77.74F, 26.19F, 77.75F, 26.3F, 77.71F, 26.41F)
            lineTo(77.28F, 27.74F)
            curveTo(77.24F, 27.85F, 77.17F, 27.94F, 77.07F, 27.99F)
            curveTo(76.97F, 28.04F, 76.86F, 28.05F, 76.75F, 28.01F)

            moveTo(82.7F, 32.3F)
            curveTo(82.59F, 32.26F, 82.5F, 32.19F, 82.45F, 32.09F)
            curveTo(82.4F, 31.99F, 82.39F, 31.88F, 82.43F, 31.77F)
            lineTo(84.24F, 26.19F)
            curveTo(84.28F, 26.07F, 84.35F, 25.99F, 84.45F, 25.94F)
            curveTo(84.56F, 25.89F, 84.66F, 25.88F, 84.78F, 25.92F)
            curveTo(84.89F, 25.96F, 84.98F, 26.03F, 85.03F, 26.13F)
            curveTo(85.08F, 26.23F, 85.09F, 26.34F, 85.05F, 26.45F)
            lineTo(83.24F, 32.03F)
            curveTo(83.2F, 32.14F, 83.13F, 32.22F, 83.03F, 32.27F)
            curveTo(82.92F, 32.33F, 82.82F, 32.33F, 82.7F, 32.3F)

            moveTo(85.61F, 30.89F)
            curveTo(85.49F, 30.85F, 85.41F, 30.78F, 85.36F, 30.68F)
            curveTo(85.31F, 30.58F, 85.3F, 30.47F, 85.34F, 30.36F)
            lineTo(85.77F, 29.03F)
            curveTo(85.81F, 28.92F, 85.88F, 28.84F, 85.98F, 28.79F)
            curveTo(86.08F, 28.74F, 86.19F, 28.73F, 86.3F, 28.77F)
            curveTo(86.42F, 28.8F, 86.5F, 28.87F, 86.55F, 28.97F)
            curveTo(86.6F, 29.08F, 86.61F, 29.18F, 86.57F, 29.3F)
            lineTo(86.14F, 30.62F)
            curveTo(86.11F, 30.74F, 86.04F, 30.82F, 85.93F, 30.87F)
            curveTo(85.83F, 30.92F, 85.72F, 30.93F, 85.61F, 30.89F)
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
            moveTo(81.33F, 29.05F)
            curveTo(81.08F, 28.97F, 80.91F, 28.82F, 80.8F, 28.59F)
            curveTo(80.7F, 28.36F, 80.68F, 28.12F, 80.77F, 27.87F)
            lineTo(81.46F, 25.73F)
            curveTo(81.54F, 25.49F, 81.69F, 25.32F, 81.91F, 25.2F)
            curveTo(82.13F, 25.09F, 82.36F, 25.08F, 82.59F, 25.15F)
            curveTo(82.83F, 25.23F, 83.0F, 25.38F, 83.12F, 25.6F)
            curveTo(83.23F, 25.82F, 83.25F, 26.05F, 83.17F, 26.28F)
            lineTo(82.47F, 28.42F)
            curveTo(82.39F, 28.67F, 82.24F, 28.86F, 82.02F, 28.98F)
            curveTo(81.8F, 29.11F, 81.57F, 29.13F, 81.33F, 29.05F)

            moveTo(80.48F, 31.63F)
            curveTo(80.41F, 31.61F, 80.36F, 31.56F, 80.33F, 31.5F)
            curveTo(80.3F, 31.43F, 80.29F, 31.37F, 80.31F, 31.29F)
            lineTo(80.61F, 30.39F)
            curveTo(80.08F, 30.15F, 79.68F, 29.77F, 79.41F, 29.26F)
            curveTo(79.14F, 28.75F, 79.07F, 28.21F, 79.18F, 27.65F)
            curveTo(79.19F, 27.58F, 79.24F, 27.52F, 79.31F, 27.48F)
            curveTo(79.38F, 27.45F, 79.45F, 27.44F, 79.53F, 27.47F)
            curveTo(79.59F, 27.49F, 79.64F, 27.53F, 79.67F, 27.59F)
            curveTo(79.7F, 27.65F, 79.71F, 27.71F, 79.7F, 27.78F)
            curveTo(79.61F, 28.26F, 79.7F, 28.71F, 79.94F, 29.12F)
            curveTo(80.19F, 29.54F, 80.55F, 29.82F, 81.02F, 29.98F)
            curveTo(81.49F, 30.13F, 81.95F, 30.11F, 82.4F, 29.92F)
            curveTo(82.84F, 29.73F, 83.17F, 29.41F, 83.39F, 28.97F)
            curveTo(83.42F, 28.91F, 83.47F, 28.87F, 83.53F, 28.84F)
            curveTo(83.59F, 28.81F, 83.65F, 28.81F, 83.71F, 28.83F)
            curveTo(83.79F, 28.85F, 83.85F, 28.9F, 83.88F, 28.97F)
            curveTo(83.92F, 29.04F, 83.91F, 29.11F, 83.88F, 29.18F)
            curveTo(83.64F, 29.7F, 83.26F, 30.09F, 82.75F, 30.35F)
            curveTo(82.23F, 30.6F, 81.69F, 30.67F, 81.12F, 30.55F)
            lineTo(80.82F, 31.46F)
            curveTo(80.8F, 31.53F, 80.75F, 31.58F, 80.69F, 31.62F)
            curveTo(80.62F, 31.65F, 80.56F, 31.65F, 80.48F, 31.63F)

            moveTo(81.49F, 28.54F)
            curveTo(81.59F, 28.57F, 81.69F, 28.56F, 81.78F, 28.5F)
            curveTo(81.87F, 28.45F, 81.93F, 28.37F, 81.97F, 28.26F)
            lineTo(82.66F, 26.12F)
            curveTo(82.69F, 26.02F, 82.69F, 25.93F, 82.64F, 25.84F)
            curveTo(82.6F, 25.76F, 82.52F, 25.7F, 82.43F, 25.66F)
            curveTo(82.33F, 25.63F, 82.24F, 25.64F, 82.15F, 25.68F)
            curveTo(82.06F, 25.73F, 82.0F, 25.8F, 81.97F, 25.89F)
            lineTo(81.27F, 28.03F)
            curveTo(81.24F, 28.14F, 81.24F, 28.24F, 81.28F, 28.34F)
            curveTo(81.32F, 28.44F, 81.39F, 28.5F, 81.49F, 28.54F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.EvenOdd,
        ) {
            moveTo(79.84F, 27.8F)
            lineTo(79.84F, 27.8F)
            curveTo(79.76F, 28.25F, 79.83F, 28.66F, 80.06F, 29.05F)
            curveTo(80.29F, 29.44F, 80.63F, 29.71F, 81.06F, 29.85F)
            curveTo(81.5F, 29.99F, 81.92F, 29.97F, 82.34F, 29.79F)
            curveTo(82.76F, 29.62F, 83.06F, 29.33F, 83.26F, 28.91F)
            lineTo(83.26F, 28.91F)
            lineTo(83.27F, 28.91F)
            curveTo(83.31F, 28.82F, 83.38F, 28.76F, 83.47F, 28.72F)
            curveTo(83.56F, 28.68F, 83.66F, 28.67F, 83.75F, 28.7F)
            curveTo(83.87F, 28.73F, 83.96F, 28.81F, 84.01F, 28.91F)
            curveTo(84.06F, 29.02F, 84.06F, 29.13F, 84.0F, 29.24F)
            curveTo(83.75F, 29.79F, 83.35F, 30.2F, 82.81F, 30.47F)
            curveTo(82.3F, 30.72F, 81.77F, 30.8F, 81.21F, 30.71F)
            lineTo(80.95F, 31.5F)
            curveTo(80.92F, 31.61F, 80.85F, 31.69F, 80.75F, 31.74F)
            curveTo(80.65F, 31.79F, 80.55F, 31.8F, 80.44F, 31.76F)
            curveTo(80.34F, 31.73F, 80.25F, 31.66F, 80.2F, 31.56F)
            curveTo(80.16F, 31.46F, 80.15F, 31.35F, 80.18F, 31.25F)
            lineTo(80.44F, 30.46F)
            curveTo(79.94F, 30.2F, 79.55F, 29.82F, 79.29F, 29.32F)
            curveTo(79.01F, 28.78F, 78.93F, 28.22F, 79.05F, 27.63F)
            curveTo(79.07F, 27.51F, 79.14F, 27.42F, 79.24F, 27.36F)
            curveTo(79.35F, 27.31F, 79.46F, 27.3F, 79.57F, 27.34F)
            curveTo(79.67F, 27.37F, 79.74F, 27.43F, 79.79F, 27.52F)
            curveTo(79.84F, 27.61F, 79.85F, 27.7F, 79.84F, 27.79F)
            lineTo(79.84F, 27.8F)

            moveTo(79.18F, 27.65F)
            curveTo(79.07F, 28.21F, 79.14F, 28.75F, 79.41F, 29.26F)
            curveTo(79.68F, 29.77F, 80.08F, 30.15F, 80.61F, 30.39F)
            lineTo(80.31F, 31.29F)
            curveTo(80.29F, 31.37F, 80.3F, 31.43F, 80.33F, 31.5F)
            curveTo(80.36F, 31.56F, 80.41F, 31.61F, 80.48F, 31.63F)
            curveTo(80.56F, 31.65F, 80.62F, 31.65F, 80.69F, 31.62F)
            curveTo(80.75F, 31.58F, 80.8F, 31.53F, 80.82F, 31.46F)
            lineTo(81.12F, 30.55F)
            curveTo(81.69F, 30.67F, 82.23F, 30.6F, 82.75F, 30.35F)
            curveTo(83.26F, 30.09F, 83.64F, 29.7F, 83.88F, 29.18F)
            curveTo(83.91F, 29.11F, 83.92F, 29.04F, 83.88F, 28.97F)
            curveTo(83.85F, 28.9F, 83.79F, 28.85F, 83.71F, 28.83F)
            curveTo(83.65F, 28.81F, 83.59F, 28.81F, 83.53F, 28.84F)
            curveTo(83.47F, 28.87F, 83.42F, 28.91F, 83.39F, 28.97F)
            curveTo(83.17F, 29.41F, 82.84F, 29.73F, 82.4F, 29.92F)
            curveTo(81.95F, 30.11F, 81.49F, 30.13F, 81.02F, 29.98F)
            curveTo(80.55F, 29.82F, 80.19F, 29.54F, 79.94F, 29.12F)
            curveTo(79.7F, 28.71F, 79.61F, 28.26F, 79.7F, 27.78F)
            curveTo(79.71F, 27.71F, 79.7F, 27.65F, 79.67F, 27.59F)
            curveTo(79.64F, 27.53F, 79.59F, 27.49F, 79.53F, 27.47F)
            curveTo(79.45F, 27.44F, 79.38F, 27.45F, 79.31F, 27.48F)
            curveTo(79.24F, 27.52F, 79.19F, 27.58F, 79.18F, 27.65F)

            moveTo(82.52F, 25.9F)
            curveTo(82.49F, 25.85F, 82.45F, 25.81F, 82.38F, 25.79F)
            curveTo(82.32F, 25.77F, 82.26F, 25.78F, 82.21F, 25.8F)
            curveTo(82.16F, 25.83F, 82.12F, 25.87F, 82.1F, 25.93F)
            lineTo(81.4F, 28.07F)
            curveTo(81.38F, 28.15F, 81.38F, 28.22F, 81.41F, 28.29F)
            curveTo(81.43F, 28.35F, 81.47F, 28.39F, 81.53F, 28.41F)
            curveTo(81.6F, 28.43F, 81.65F, 28.42F, 81.71F, 28.39F)
            curveTo(81.77F, 28.35F, 81.81F, 28.3F, 81.83F, 28.21F)
            lineTo(82.53F, 26.08F)
            curveTo(82.55F, 26.01F, 82.55F, 25.96F, 82.52F, 25.9F)

            moveTo(81.28F, 29.18F)
            curveTo(81.0F, 29.09F, 80.8F, 28.91F, 80.68F, 28.64F)
            curveTo(80.56F, 28.38F, 80.54F, 28.11F, 80.64F, 27.82F)
            lineTo(81.33F, 25.68F)
            curveTo(81.42F, 25.41F, 81.59F, 25.21F, 81.84F, 25.08F)
            curveTo(82.1F, 24.95F, 82.36F, 24.93F, 82.63F, 25.02F)
            curveTo(82.91F, 25.11F, 83.11F, 25.28F, 83.24F, 25.54F)
            curveTo(83.37F, 25.79F, 83.39F, 26.05F, 83.3F, 26.33F)
            lineTo(82.6F, 28.46F)
            curveTo(82.51F, 28.75F, 82.34F, 28.96F, 82.09F, 29.1F)
            curveTo(81.83F, 29.24F, 81.56F, 29.27F, 81.28F, 29.18F)

            moveTo(82.02F, 28.98F)
            curveTo(82.24F, 28.86F, 82.39F, 28.67F, 82.47F, 28.42F)
            lineTo(83.17F, 26.28F)
            curveTo(83.25F, 26.05F, 83.23F, 25.82F, 83.12F, 25.6F)
            curveTo(83.0F, 25.38F, 82.83F, 25.23F, 82.59F, 25.15F)
            curveTo(82.36F, 25.08F, 82.13F, 25.09F, 81.91F, 25.2F)
            curveTo(81.69F, 25.32F, 81.54F, 25.49F, 81.46F, 25.73F)
            lineTo(80.77F, 27.87F)
            curveTo(80.68F, 28.12F, 80.7F, 28.36F, 80.8F, 28.59F)
            curveTo(80.91F, 28.82F, 81.08F, 28.97F, 81.33F, 29.05F)
            curveTo(81.57F, 29.13F, 81.8F, 29.11F, 82.02F, 28.98F)

            moveTo(81.78F, 28.5F)
            curveTo(81.69F, 28.56F, 81.59F, 28.57F, 81.49F, 28.54F)
            curveTo(81.39F, 28.5F, 81.32F, 28.44F, 81.28F, 28.34F)
            curveTo(81.24F, 28.24F, 81.24F, 28.14F, 81.27F, 28.03F)
            lineTo(81.97F, 25.89F)
            curveTo(82.0F, 25.8F, 82.06F, 25.73F, 82.15F, 25.68F)
            curveTo(82.24F, 25.64F, 82.33F, 25.63F, 82.43F, 25.66F)
            curveTo(82.52F, 25.7F, 82.6F, 25.76F, 82.64F, 25.84F)
            curveTo(82.69F, 25.93F, 82.69F, 26.02F, 82.66F, 26.12F)
            lineTo(81.97F, 28.26F)
            curveTo(81.93F, 28.37F, 81.87F, 28.45F, 81.78F, 28.5F)
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
            moveTo(60.39F, 29.32F)
            lineTo(62.23F, 43.99F)
            curveTo(62.44F, 45.61F, 61.31F, 47.08F, 59.73F, 47.28F)
            lineTo(47.19F, 48.86F)
            curveTo(45.6F, 49.06F, 44.15F, 47.91F, 43.94F, 46.29F)
            lineTo(41.66F, 28.15F)
            curveTo(41.46F, 26.53F, 42.58F, 25.05F, 44.17F, 24.85F)
            lineTo(53.31F, 23.7F)
            lineTo(60.39F, 29.32F)
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
            moveTo(60.39F, 29.32F)
            lineTo(56.99F, 29.74F)
            curveTo(55.41F, 29.94F, 53.95F, 28.79F, 53.75F, 27.17F)
            lineTo(53.31F, 23.7F)
            lineTo(60.39F, 29.32F)
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
            moveTo(46.07F, 33.64F)
            curveTo(45.92F, 32.44F, 46.78F, 31.35F, 47.99F, 31.2F)
            lineTo(54.58F, 30.37F)
            curveTo(55.79F, 30.22F, 56.89F, 31.06F, 57.04F, 32.26F)
            lineTo(57.86F, 38.74F)
            curveTo(58.01F, 39.93F, 57.15F, 41.02F, 55.93F, 41.18F)
            lineTo(49.35F, 42.0F)
            curveTo(48.14F, 42.16F, 47.03F, 41.31F, 46.88F, 40.12F)
            lineTo(46.07F, 33.64F)
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
            moveTo(50.01F, 36.3F)
            curveTo(50.1F, 36.17F, 50.15F, 36.1F, 50.21F, 36.08F)
            curveTo(50.26F, 36.06F, 50.32F, 36.05F, 50.38F, 36.07F)
            curveTo(50.44F, 36.09F, 50.5F, 36.14F, 50.61F, 36.26F)
            lineTo(52.15F, 37.84F)
            curveTo(52.32F, 38.02F, 52.41F, 38.11F, 52.42F, 38.19F)
            curveTo(52.43F, 38.26F, 52.4F, 38.34F, 52.35F, 38.39F)
            curveTo(52.29F, 38.45F, 52.16F, 38.46F, 51.91F, 38.49F)
            lineTo(48.89F, 38.87F)
            curveTo(48.61F, 38.91F, 48.46F, 38.93F, 48.39F, 38.88F)
            curveTo(48.32F, 38.84F, 48.28F, 38.77F, 48.28F, 38.69F)
            curveTo(48.27F, 38.6F, 48.36F, 38.49F, 48.53F, 38.26F)
            lineTo(50.01F, 36.3F)
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
            moveTo(53.22F, 34.57F)
            curveTo(53.31F, 34.4F, 53.36F, 34.31F, 53.42F, 34.27F)
            curveTo(53.48F, 34.24F, 53.54F, 34.23F, 53.61F, 34.25F)
            curveTo(53.68F, 34.27F, 53.74F, 34.34F, 53.87F, 34.49F)
            lineTo(56.34F, 37.33F)
            curveTo(56.5F, 37.51F, 56.57F, 37.6F, 56.58F, 37.68F)
            curveTo(56.58F, 37.75F, 56.56F, 37.82F, 56.5F, 37.87F)
            curveTo(56.44F, 37.92F, 56.33F, 37.94F, 56.09F, 37.97F)
            lineTo(51.93F, 38.49F)
            curveTo(51.7F, 38.52F, 51.58F, 38.54F, 51.51F, 38.49F)
            curveTo(51.45F, 38.46F, 51.4F, 38.4F, 51.39F, 38.33F)
            curveTo(51.38F, 38.25F, 51.43F, 38.15F, 51.53F, 37.94F)
            lineTo(53.22F, 34.57F)
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
            moveTo(50.43F, 34.32F)
            curveTo(50.48F, 34.73F, 50.19F, 35.1F, 49.77F, 35.15F)
            curveTo(49.36F, 35.2F, 48.99F, 34.92F, 48.93F, 34.51F)
            curveTo(48.88F, 34.11F, 49.18F, 33.73F, 49.59F, 33.68F)
            curveTo(50.0F, 33.63F, 50.38F, 33.92F, 50.43F, 34.32F)
            close()
        }.build()
        return _addNotePlaceholder!!
    }
private var _addNotePlaceholder: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconAddNotePlaceholderPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.AddNotePlaceholder, contentDescription = null)
    }
}