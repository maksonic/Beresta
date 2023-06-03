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
val AppImage.TrashedFoldersList: ImageVector
    @Composable get() {

        _trashedFoldersList = ImageVector.Builder(
            name = "TrashedFoldersList",
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
            moveTo(54.06F, 84.01F)
            curveTo(45.94F, 84.01F, 38.53F, 84.48F, 31.99F, 85.22F)
            curveTo(30.83F, 85.15F, 29.77F, 85.11F, 28.91F, 85.11F)
            curveTo(24.63F, 85.11F, 20.78F, 86.02F, 18.06F, 87.46F)
            curveTo(9.19F, 89.42F, 4.0F, 91.82F, 4.0F, 93.59F)
            curveTo(4.0F, 95.22F, 8.47F, 96.02F, 16.14F, 96.42F)
            curveTo(18.76F, 98.58F, 23.5F, 100.02F, 28.91F, 100.02F)
            curveTo(34.01F, 100.02F, 45.44F, 98.74F, 53.41F, 96.79F)
            curveTo(53.65F, 96.79F, 53.83F, 96.79F, 54.06F, 96.79F)
            curveTo(83.48F, 96.79F, 96.67F, 90.73F, 96.67F, 87.2F)
            curveTo(96.67F, 83.67F, 83.48F, 84.01F, 54.06F, 84.01F)
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
            moveTo(75.83F, 0.07F)
            curveTo(75.47F, 0.08F, 75.1F, 0.2F, 74.78F, 0.43F)
            lineTo(66.37F, 6.57F)
            curveTo(66.0F, 6.84F, 65.55F, 6.97F, 65.1F, 6.93F)
            lineTo(64.0F, 6.85F)
            curveTo(63.55F, 6.81F, 63.1F, 6.94F, 62.74F, 7.21F)
            lineTo(57.17F, 11.27F)
            curveTo(56.32F, 11.88F, 56.13F, 13.07F, 56.75F, 13.92F)
            lineTo(65.86F, 26.41F)
            curveTo(66.48F, 27.26F, 67.67F, 27.44F, 68.52F, 26.83F)
            lineTo(85.17F, 14.68F)
            curveTo(86.02F, 14.06F, 85.88F, 12.6F, 85.59F, 12.03F)
            lineTo(77.44F, 0.85F)
            curveTo(77.05F, 0.32F, 76.44F, 0.05F, 75.83F, 0.07F)

            moveTo(36.29F, 14.5F)
            curveTo(35.55F, 14.56F, 34.88F, 15.04F, 34.62F, 15.78F)
            lineTo(29.61F, 30.41F)
            curveTo(29.27F, 31.4F, 29.8F, 32.48F, 30.79F, 32.82F)
            lineTo(50.29F, 39.51F)
            curveTo(51.28F, 39.85F, 52.4F, 38.89F, 52.7F, 38.33F)
            lineTo(57.19F, 25.24F)
            curveTo(57.53F, 24.25F, 57.0F, 23.17F, 56.01F, 22.83F)
            lineTo(46.15F, 19.45F)
            curveTo(45.72F, 19.3F, 45.36F, 19.01F, 45.13F, 18.62F)
            lineTo(44.57F, 17.67F)
            curveTo(44.34F, 17.28F, 43.98F, 16.98F, 43.55F, 16.84F)
            lineTo(37.03F, 14.6F)
            curveTo(36.78F, 14.52F, 36.53F, 14.49F, 36.29F, 14.5F)

            moveTo(60.24F, 29.31F)
            curveTo(59.5F, 29.25F, 58.77F, 29.63F, 58.4F, 30.32F)
            lineTo(51.19F, 44.0F)
            curveTo(50.7F, 44.93F, 51.06F, 46.08F, 51.98F, 46.56F)
            lineTo(70.22F, 56.18F)
            curveTo(71.15F, 56.67F, 72.4F, 55.89F, 72.79F, 55.38F)
            lineTo(79.24F, 43.14F)
            curveTo(79.73F, 42.22F, 79.37F, 41.07F, 78.44F, 40.58F)
            lineTo(69.23F, 35.72F)
            curveTo(68.83F, 35.51F, 68.51F, 35.16F, 68.35F, 34.74F)
            lineTo(67.94F, 33.72F)
            curveTo(67.77F, 33.3F, 67.46F, 32.95F, 67.06F, 32.74F)
            lineTo(60.96F, 29.53F)
            curveTo(60.73F, 29.41F, 60.49F, 29.34F, 60.24F, 29.31F)
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
            moveTo(84.94F, 41.96F)
            curveTo(84.94F, 44.17F, 73.6F, 45.96F, 59.63F, 45.96F)
            curveTo(45.65F, 45.96F, 34.31F, 44.17F, 34.31F, 41.96F)
            curveTo(34.31F, 39.75F, 45.65F, 37.96F, 59.63F, 37.96F)
            curveTo(73.6F, 37.96F, 84.94F, 39.75F, 84.94F, 41.96F)
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
            moveTo(56.71F, 33.53F)
            curveTo(56.76F, 33.43F, 56.83F, 33.32F, 56.91F, 33.24F)
            curveTo(57.5F, 32.64F, 58.48F, 32.51F, 59.23F, 32.91F)
            lineTo(77.45F, 42.51F)
            curveTo(78.49F, 43.06F, 78.91F, 43.77F, 78.08F, 45.34F)
            lineTo(72.79F, 55.38F)
            curveTo(72.22F, 56.12F, 71.04F, 56.61F, 70.22F, 56.18F)
            lineTo(51.9F, 46.52F)
            curveTo(51.05F, 46.07F, 50.65F, 45.03F, 51.1F, 44.21F)
            curveTo(51.12F, 44.16F, 51.15F, 44.09F, 51.17F, 44.03F)
            curveTo(51.22F, 43.92F, 51.29F, 43.82F, 51.34F, 43.71F)
            lineTo(56.71F, 33.53F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(57.25F, 32.91F)
            curveTo(57.3F, 32.81F, 57.35F, 32.73F, 57.42F, 32.66F)
            curveTo(57.95F, 32.12F, 58.83F, 32.0F, 59.52F, 32.36F)
            lineTo(77.74F, 41.97F)
            curveTo(78.25F, 42.23F, 78.55F, 42.52F, 78.66F, 42.88F)
            curveTo(78.77F, 43.26F, 78.69F, 43.77F, 78.29F, 44.54F)
            lineTo(73.07F, 54.45F)
            curveTo(73.02F, 54.53F, 72.98F, 54.61F, 72.92F, 54.67F)
            curveTo(72.65F, 54.98F, 72.26F, 55.23F, 71.84F, 55.35F)
            curveTo(71.43F, 55.48F, 71.01F, 55.48F, 70.68F, 55.3F)
            lineTo(52.36F, 45.65F)
            curveTo(51.59F, 45.24F, 51.24F, 44.3F, 51.63F, 43.59F)
            curveTo(51.66F, 43.54F, 51.68F, 43.48F, 51.71F, 43.43F)
            curveTo(51.71F, 43.42F, 51.72F, 43.4F, 51.72F, 43.39F)
            curveTo(51.75F, 43.33F, 51.78F, 43.28F, 51.81F, 43.22F)
            curveTo(51.83F, 43.18F, 51.86F, 43.14F, 51.88F, 43.09F)
            lineTo(57.25F, 32.91F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(57.77F, 31.92F)
            curveTo(57.82F, 31.82F, 57.88F, 31.73F, 57.94F, 31.67F)
            curveTo(58.47F, 31.13F, 59.36F, 31.01F, 60.04F, 31.37F)
            lineTo(78.27F, 40.97F)
            curveTo(78.77F, 41.24F, 79.08F, 41.52F, 79.19F, 41.89F)
            curveTo(79.29F, 42.26F, 79.22F, 42.78F, 78.81F, 43.55F)
            lineTo(73.59F, 53.45F)
            curveTo(73.55F, 53.54F, 73.5F, 53.61F, 73.45F, 53.68F)
            curveTo(73.17F, 53.98F, 72.78F, 54.24F, 72.37F, 54.36F)
            curveTo(71.95F, 54.49F, 71.54F, 54.48F, 71.21F, 54.31F)
            lineTo(52.89F, 44.65F)
            curveTo(52.11F, 44.24F, 51.77F, 43.31F, 52.16F, 42.6F)
            curveTo(52.18F, 42.55F, 52.21F, 42.49F, 52.23F, 42.44F)
            curveTo(52.23F, 42.42F, 52.24F, 42.41F, 52.24F, 42.4F)
            curveTo(52.27F, 42.34F, 52.3F, 42.28F, 52.33F, 42.22F)
            curveTo(52.36F, 42.19F, 52.38F, 42.15F, 52.4F, 42.1F)
            lineTo(57.77F, 31.92F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(57.42F, 32.58F)
            curveTo(57.47F, 32.48F, 57.53F, 32.39F, 57.59F, 32.33F)
            curveTo(58.12F, 31.79F, 59.01F, 31.67F, 59.69F, 32.03F)
            lineTo(77.92F, 41.64F)
            curveTo(78.42F, 41.9F, 78.73F, 42.18F, 78.84F, 42.55F)
            curveTo(78.95F, 42.93F, 78.87F, 43.44F, 78.46F, 44.21F)
            lineTo(73.24F, 54.11F)
            curveTo(73.2F, 54.2F, 73.15F, 54.28F, 73.1F, 54.34F)
            curveTo(72.82F, 54.65F, 72.43F, 54.9F, 72.02F, 55.02F)
            curveTo(71.6F, 55.15F, 71.19F, 55.14F, 70.86F, 54.97F)
            lineTo(52.54F, 45.31F)
            curveTo(51.77F, 44.91F, 51.42F, 43.97F, 51.81F, 43.26F)
            curveTo(51.83F, 43.21F, 51.86F, 43.15F, 51.88F, 43.1F)
            curveTo(51.89F, 43.08F, 51.89F, 43.07F, 51.9F, 43.06F)
            curveTo(51.92F, 43.0F, 51.95F, 42.95F, 51.99F, 42.89F)
            curveTo(52.01F, 42.85F, 52.03F, 42.81F, 52.06F, 42.76F)
            lineTo(57.42F, 32.58F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(57.07F, 33.24F)
            curveTo(57.13F, 33.14F, 57.18F, 33.06F, 57.24F, 32.99F)
            curveTo(57.77F, 32.45F, 58.66F, 32.33F, 59.35F, 32.69F)
            lineTo(77.57F, 42.3F)
            curveTo(78.07F, 42.56F, 78.38F, 42.85F, 78.49F, 43.22F)
            curveTo(78.6F, 43.59F, 78.52F, 44.1F, 78.12F, 44.87F)
            lineTo(72.89F, 54.78F)
            curveTo(72.85F, 54.86F, 72.8F, 54.94F, 72.75F, 55.0F)
            curveTo(72.47F, 55.31F, 72.08F, 55.56F, 71.67F, 55.69F)
            curveTo(71.25F, 55.81F, 70.84F, 55.81F, 70.51F, 55.63F)
            lineTo(52.19F, 45.98F)
            curveTo(51.42F, 45.57F, 51.07F, 44.63F, 51.46F, 43.92F)
            curveTo(51.49F, 43.87F, 51.51F, 43.81F, 51.53F, 43.76F)
            curveTo(51.54F, 43.75F, 51.54F, 43.73F, 51.55F, 43.72F)
            curveTo(51.57F, 43.66F, 51.6F, 43.61F, 51.64F, 43.55F)
            curveTo(51.66F, 43.51F, 51.68F, 43.47F, 51.71F, 43.42F)
            lineTo(57.07F, 33.24F)
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
            moveTo(56.71F, 33.53F)
            curveTo(56.76F, 33.43F, 56.83F, 33.32F, 56.91F, 33.24F)
            curveTo(57.5F, 32.64F, 58.48F, 32.51F, 59.23F, 32.91F)
            lineTo(77.45F, 42.51F)
            curveTo(78.49F, 43.06F, 78.91F, 43.77F, 78.08F, 45.34F)
            lineTo(72.86F, 55.24F)
            curveTo(72.81F, 55.34F, 72.76F, 55.43F, 72.69F, 55.5F)
            curveTo(72.09F, 56.17F, 71.0F, 56.59F, 70.22F, 56.18F)
            lineTo(51.9F, 46.52F)
            curveTo(51.05F, 46.07F, 50.65F, 45.03F, 51.1F, 44.21F)
            curveTo(51.12F, 44.16F, 51.15F, 44.09F, 51.17F, 44.03F)
            curveTo(51.22F, 43.92F, 51.29F, 43.82F, 51.34F, 43.71F)
            lineTo(56.71F, 33.53F)
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
            moveTo(62.36F, 49.4F)
            curveTo(65.13F, 50.86F, 68.55F, 49.8F, 70.01F, 47.04F)
            curveTo(71.47F, 44.27F, 70.4F, 40.85F, 67.64F, 39.39F)
            curveTo(64.87F, 37.93F, 61.44F, 38.99F, 59.98F, 41.75F)
            curveTo(58.53F, 44.52F, 59.59F, 47.94F, 62.36F, 49.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFFF7E2)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(63.02F, 40.38F)
            curveTo(62.98F, 40.38F, 62.95F, 40.39F, 62.92F, 40.4F)
            curveTo(62.76F, 40.45F, 62.66F, 40.62F, 62.71F, 40.79F)
            lineTo(63.04F, 41.83F)
            lineTo(61.99F, 42.15F)
            curveTo(61.83F, 42.2F, 61.73F, 42.38F, 61.79F, 42.55F)
            curveTo(61.84F, 42.71F, 62.01F, 42.81F, 62.18F, 42.76F)
            lineTo(63.22F, 42.43F)
            lineTo(63.54F, 43.47F)
            curveTo(63.59F, 43.64F, 63.77F, 43.73F, 63.94F, 43.68F)
            curveTo(64.11F, 43.63F, 64.2F, 43.45F, 64.15F, 43.29F)
            lineTo(63.82F, 42.25F)
            lineTo(64.87F, 41.92F)
            curveTo(65.03F, 41.87F, 65.13F, 41.7F, 65.07F, 41.53F)
            curveTo(65.02F, 41.36F, 64.85F, 41.27F, 64.68F, 41.32F)
            lineTo(63.64F, 41.64F)
            lineTo(63.32F, 40.6F)
            curveTo(63.27F, 40.47F, 63.15F, 40.38F, 63.02F, 40.38F)

            moveTo(67.32F, 42.65F)
            curveTo(67.29F, 42.65F, 67.26F, 42.65F, 67.22F, 42.66F)
            curveTo(67.06F, 42.72F, 66.97F, 42.89F, 67.02F, 43.06F)
            lineTo(67.34F, 44.1F)
            lineTo(66.3F, 44.42F)
            curveTo(66.13F, 44.47F, 66.04F, 44.65F, 66.09F, 44.82F)
            curveTo(66.14F, 44.98F, 66.32F, 45.08F, 66.48F, 45.03F)
            lineTo(67.53F, 44.7F)
            lineTo(67.85F, 45.74F)
            curveTo(67.9F, 45.91F, 68.08F, 46.0F, 68.24F, 45.95F)
            curveTo(68.41F, 45.9F, 68.5F, 45.72F, 68.45F, 45.55F)
            lineTo(68.13F, 44.52F)
            lineTo(69.17F, 44.19F)
            curveTo(69.34F, 44.14F, 69.43F, 43.96F, 69.38F, 43.8F)
            curveTo(69.33F, 43.63F, 69.15F, 43.54F, 68.98F, 43.59F)
            lineTo(67.94F, 43.91F)
            lineTo(67.62F, 42.87F)
            curveTo(67.58F, 42.74F, 67.45F, 42.65F, 67.32F, 42.65F)

            moveTo(61.93F, 44.9F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 61.71F, 44.97F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 61.64F, 45.5F)
            curveTo(62.91F, 47.15F, 64.05F, 47.71F, 66.21F, 47.91F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 66.62F, 47.57F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 66.28F, 47.17F)
            curveTo(64.21F, 46.97F, 63.43F, 46.6F, 62.23F, 45.04F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 61.93F, 44.9F)
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
            moveTo(34.31F, 41.96F)
            curveTo(34.31F, 41.96F, 42.31F, 87.26F, 42.97F, 90.59F)
            curveTo(43.64F, 93.92F, 50.14F, 94.0F, 59.63F, 93.92F)
            curveTo(69.11F, 94.0F, 75.61F, 93.92F, 76.28F, 90.59F)
            curveTo(76.94F, 87.26F, 84.94F, 41.96F, 84.94F, 41.96F)
            curveTo(80.57F, 44.7F, 74.98F, 45.56F, 59.63F, 45.96F)
            curveTo(44.27F, 45.56F, 38.68F, 44.7F, 34.31F, 41.96F)
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
            moveTo(58.89F, 16.85F)
            curveTo(58.82F, 16.75F, 58.76F, 16.65F, 58.72F, 16.54F)
            curveTo(58.44F, 15.75F, 58.75F, 14.81F, 59.44F, 14.31F)
            lineTo(76.08F, 2.17F)
            curveTo(77.03F, 1.48F, 77.85F, 1.42F, 78.9F, 2.85F)
            lineTo(85.59F, 12.03F)
            curveTo(86.0F, 12.86F, 85.92F, 14.13F, 85.17F, 14.68F)
            lineTo(68.44F, 26.88F)
            curveTo(67.67F, 27.45F, 66.55F, 27.35F, 66.01F, 26.59F)
            curveTo(65.98F, 26.54F, 65.93F, 26.49F, 65.88F, 26.44F)
            curveTo(65.8F, 26.35F, 65.74F, 26.25F, 65.67F, 26.15F)
            lineTo(58.89F, 16.85F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(58.57F, 16.09F)
            curveTo(58.5F, 16.0F, 58.45F, 15.92F, 58.42F, 15.83F)
            curveTo(58.17F, 15.12F, 58.45F, 14.27F, 59.08F, 13.81F)
            lineTo(75.72F, 1.67F)
            curveTo(76.18F, 1.34F, 76.57F, 1.19F, 76.95F, 1.25F)
            curveTo(77.33F, 1.32F, 77.76F, 1.61F, 78.28F, 2.32F)
            lineTo(84.87F, 11.36F)
            curveTo(84.93F, 11.44F, 84.97F, 11.52F, 85.01F, 11.59F)
            curveTo(85.16F, 11.97F, 85.22F, 12.44F, 85.15F, 12.86F)
            curveTo(85.08F, 13.29F, 84.89F, 13.66F, 84.59F, 13.88F)
            lineTo(67.86F, 26.08F)
            curveTo(67.15F, 26.6F, 66.16F, 26.5F, 65.69F, 25.83F)
            curveTo(65.66F, 25.79F, 65.61F, 25.74F, 65.58F, 25.7F)
            curveTo(65.57F, 25.69F, 65.56F, 25.68F, 65.55F, 25.67F)
            curveTo(65.5F, 25.62F, 65.47F, 25.57F, 65.43F, 25.51F)
            curveTo(65.41F, 25.47F, 65.38F, 25.44F, 65.35F, 25.39F)
            lineTo(58.57F, 16.09F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(57.91F, 15.18F)
            curveTo(57.84F, 15.09F, 57.79F, 15.01F, 57.76F, 14.92F)
            curveTo(57.51F, 14.21F, 57.79F, 13.36F, 58.42F, 12.9F)
            lineTo(75.06F, 0.77F)
            curveTo(75.52F, 0.43F, 75.91F, 0.28F, 76.29F, 0.34F)
            curveTo(76.67F, 0.41F, 77.1F, 0.71F, 77.61F, 1.41F)
            lineTo(84.21F, 10.45F)
            curveTo(84.27F, 10.53F, 84.31F, 10.61F, 84.34F, 10.69F)
            curveTo(84.5F, 11.07F, 84.55F, 11.53F, 84.49F, 11.96F)
            curveTo(84.42F, 12.38F, 84.23F, 12.75F, 83.93F, 12.97F)
            lineTo(67.19F, 25.17F)
            curveTo(66.49F, 25.69F, 65.5F, 25.59F, 65.03F, 24.92F)
            curveTo(65.0F, 24.88F, 64.95F, 24.83F, 64.91F, 24.79F)
            curveTo(64.9F, 24.78F, 64.9F, 24.77F, 64.89F, 24.76F)
            curveTo(64.84F, 24.71F, 64.81F, 24.66F, 64.77F, 24.6F)
            curveTo(64.75F, 24.57F, 64.72F, 24.53F, 64.69F, 24.48F)
            lineTo(57.91F, 15.18F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(58.35F, 15.79F)
            curveTo(58.28F, 15.7F, 58.23F, 15.61F, 58.2F, 15.53F)
            curveTo(57.95F, 14.82F, 58.23F, 13.96F, 58.86F, 13.51F)
            lineTo(75.5F, 1.37F)
            curveTo(75.96F, 1.04F, 76.35F, 0.88F, 76.73F, 0.95F)
            curveTo(77.11F, 1.02F, 77.54F, 1.31F, 78.05F, 2.01F)
            lineTo(84.65F, 11.06F)
            curveTo(84.71F, 11.14F, 84.75F, 11.21F, 84.78F, 11.29F)
            curveTo(84.94F, 11.67F, 84.99F, 12.13F, 84.93F, 12.56F)
            curveTo(84.86F, 12.99F, 84.67F, 13.36F, 84.37F, 13.58F)
            lineTo(67.64F, 25.78F)
            curveTo(66.93F, 26.29F, 65.94F, 26.19F, 65.47F, 25.53F)
            curveTo(65.44F, 25.49F, 65.39F, 25.44F, 65.36F, 25.4F)
            curveTo(65.35F, 25.38F, 65.34F, 25.37F, 65.33F, 25.36F)
            curveTo(65.28F, 25.31F, 65.25F, 25.26F, 65.21F, 25.21F)
            curveTo(65.19F, 25.17F, 65.16F, 25.13F, 65.13F, 25.09F)
            lineTo(58.35F, 15.79F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(58.79F, 16.39F)
            curveTo(58.72F, 16.3F, 58.67F, 16.22F, 58.64F, 16.13F)
            curveTo(58.39F, 15.42F, 58.67F, 14.57F, 59.3F, 14.11F)
            lineTo(75.94F, 1.98F)
            curveTo(76.4F, 1.64F, 76.79F, 1.49F, 77.17F, 1.55F)
            curveTo(77.55F, 1.62F, 77.98F, 1.92F, 78.5F, 2.62F)
            lineTo(85.09F, 11.66F)
            curveTo(85.15F, 11.74F, 85.19F, 11.82F, 85.23F, 11.9F)
            curveTo(85.38F, 12.28F, 85.44F, 12.74F, 85.37F, 13.17F)
            curveTo(85.3F, 13.59F, 85.11F, 13.96F, 84.81F, 14.18F)
            lineTo(68.08F, 26.38F)
            curveTo(67.37F, 26.9F, 66.38F, 26.8F, 65.91F, 26.13F)
            curveTo(65.88F, 26.09F, 65.83F, 26.04F, 65.8F, 26.0F)
            curveTo(65.79F, 25.99F, 65.78F, 25.98F, 65.77F, 25.97F)
            curveTo(65.72F, 25.92F, 65.69F, 25.87F, 65.65F, 25.81F)
            curveTo(65.63F, 25.78F, 65.6F, 25.74F, 65.57F, 25.69F)
            lineTo(58.79F, 16.39F)
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
            moveTo(33.44F, 19.22F)
            curveTo(33.48F, 19.1F, 33.53F, 18.99F, 33.6F, 18.9F)
            curveTo(34.09F, 18.21F, 35.04F, 17.93F, 35.84F, 18.21F)
            lineTo(55.33F, 24.89F)
            curveTo(56.44F, 25.27F, 56.96F, 25.91F, 56.38F, 27.59F)
            lineTo(52.7F, 38.33F)
            curveTo(52.26F, 39.14F, 51.17F, 39.81F, 50.29F, 39.51F)
            lineTo(30.7F, 32.79F)
            curveTo(29.79F, 32.48F, 29.23F, 31.51F, 29.55F, 30.63F)
            curveTo(29.56F, 30.58F, 29.58F, 30.51F, 29.59F, 30.44F)
            curveTo(29.62F, 30.33F, 29.67F, 30.22F, 29.71F, 30.1F)
            lineTo(33.44F, 19.22F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(33.88F, 18.52F)
            curveTo(33.92F, 18.41F, 33.96F, 18.32F, 34.01F, 18.25F)
            curveTo(34.45F, 17.63F, 35.31F, 17.38F, 36.04F, 17.63F)
            lineTo(55.53F, 24.31F)
            curveTo(56.07F, 24.49F, 56.41F, 24.72F, 56.58F, 25.07F)
            curveTo(56.74F, 25.42F, 56.75F, 25.94F, 56.47F, 26.77F)
            lineTo(52.83F, 37.36F)
            curveTo(52.8F, 37.45F, 52.77F, 37.53F, 52.72F, 37.6F)
            curveTo(52.5F, 37.95F, 52.15F, 38.26F, 51.76F, 38.44F)
            curveTo(51.37F, 38.63F, 50.96F, 38.69F, 50.61F, 38.57F)
            lineTo(31.02F, 31.85F)
            curveTo(30.19F, 31.57F, 29.71F, 30.7F, 29.98F, 29.94F)
            curveTo(30.0F, 29.88F, 30.02F, 29.82F, 30.03F, 29.77F)
            curveTo(30.03F, 29.75F, 30.03F, 29.74F, 30.04F, 29.73F)
            curveTo(30.05F, 29.66F, 30.07F, 29.61F, 30.1F, 29.54F)
            curveTo(30.11F, 29.5F, 30.13F, 29.46F, 30.15F, 29.41F)
            lineTo(33.88F, 18.52F)
            close()
        }.path(
            fill = SolidColor(Theme.color.placeholderColors.c0),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(34.25F, 17.45F)
            curveTo(34.28F, 17.35F, 34.32F, 17.26F, 34.37F, 17.18F)
            curveTo(34.81F, 16.57F, 35.67F, 16.31F, 36.41F, 16.56F)
            lineTo(55.89F, 23.24F)
            curveTo(56.43F, 23.43F, 56.78F, 23.66F, 56.94F, 24.01F)
            curveTo(57.11F, 24.36F, 57.11F, 24.88F, 56.83F, 25.71F)
            lineTo(53.2F, 36.29F)
            curveTo(53.17F, 36.39F, 53.13F, 36.47F, 53.09F, 36.54F)
            curveTo(52.87F, 36.88F, 52.52F, 37.19F, 52.13F, 37.38F)
            curveTo(51.74F, 37.57F, 51.33F, 37.63F, 50.97F, 37.51F)
            lineTo(31.38F, 30.79F)
            curveTo(30.56F, 30.51F, 30.07F, 29.64F, 30.35F, 28.87F)
            curveTo(30.36F, 28.82F, 30.38F, 28.76F, 30.39F, 28.71F)
            curveTo(30.39F, 28.69F, 30.4F, 28.68F, 30.4F, 28.66F)
            curveTo(30.42F, 28.6F, 30.44F, 28.54F, 30.46F, 28.48F)
            curveTo(30.48F, 28.44F, 30.5F, 28.4F, 30.51F, 28.34F)
            lineTo(34.25F, 17.45F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(34.0F, 18.16F)
            curveTo(34.04F, 18.06F, 34.08F, 17.97F, 34.13F, 17.89F)
            curveTo(34.57F, 17.28F, 35.43F, 17.02F, 36.16F, 17.27F)
            lineTo(55.65F, 23.95F)
            curveTo(56.19F, 24.14F, 56.54F, 24.37F, 56.7F, 24.72F)
            curveTo(56.86F, 25.07F, 56.87F, 25.59F, 56.59F, 26.41F)
            lineTo(52.96F, 37.0F)
            curveTo(52.92F, 37.09F, 52.89F, 37.18F, 52.85F, 37.25F)
            curveTo(52.62F, 37.59F, 52.27F, 37.9F, 51.88F, 38.09F)
            curveTo(51.5F, 38.28F, 51.09F, 38.34F, 50.73F, 38.22F)
            lineTo(31.14F, 31.5F)
            curveTo(30.32F, 31.22F, 29.83F, 30.35F, 30.1F, 29.58F)
            curveTo(30.12F, 29.53F, 30.14F, 29.47F, 30.15F, 29.41F)
            curveTo(30.15F, 29.4F, 30.16F, 29.39F, 30.16F, 29.37F)
            curveTo(30.17F, 29.31F, 30.2F, 29.25F, 30.22F, 29.19F)
            curveTo(30.24F, 29.15F, 30.25F, 29.1F, 30.27F, 29.05F)
            lineTo(34.0F, 18.16F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.374272F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(33.76F, 18.87F)
            curveTo(33.8F, 18.77F, 33.84F, 18.67F, 33.89F, 18.6F)
            curveTo(34.33F, 17.99F, 35.19F, 17.73F, 35.92F, 17.98F)
            lineTo(55.41F, 24.66F)
            curveTo(55.95F, 24.85F, 56.29F, 25.08F, 56.46F, 25.43F)
            curveTo(56.62F, 25.78F, 56.63F, 26.3F, 56.34F, 27.12F)
            lineTo(52.71F, 37.71F)
            curveTo(52.68F, 37.8F, 52.65F, 37.88F, 52.6F, 37.95F)
            curveTo(52.38F, 38.3F, 52.03F, 38.61F, 51.64F, 38.8F)
            curveTo(51.25F, 38.99F, 50.84F, 39.04F, 50.49F, 38.92F)
            lineTo(30.9F, 32.21F)
            curveTo(30.07F, 31.93F, 29.59F, 31.05F, 29.86F, 30.29F)
            curveTo(29.88F, 30.24F, 29.89F, 30.17F, 29.91F, 30.12F)
            curveTo(29.91F, 30.11F, 29.91F, 30.09F, 29.92F, 30.08F)
            curveTo(29.93F, 30.01F, 29.95F, 29.96F, 29.98F, 29.89F)
            curveTo(29.99F, 29.86F, 30.01F, 29.81F, 30.03F, 29.76F)
            lineTo(33.76F, 18.87F)
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
            moveTo(47.18F, 52.28F)
            curveTo(52.54F, 55.03F, 51.69F, 67.34F, 45.29F, 79.77F)
            curveTo(38.89F, 92.2F, 29.37F, 100.04F, 24.02F, 97.29F)
            curveTo(18.67F, 94.53F, 19.52F, 82.22F, 25.91F, 69.8F)
            curveTo(32.31F, 57.37F, 41.83F, 49.52F, 47.18F, 52.28F)
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
            moveTo(77.29F, 1.7F)
            curveTo(76.89F, 1.69F, 76.5F, 1.87F, 76.08F, 2.17F)
            lineTo(59.44F, 14.31F)
            curveTo(58.75F, 14.81F, 58.44F, 15.75F, 58.72F, 16.54F)
            curveTo(58.76F, 16.65F, 58.82F, 16.75F, 58.89F, 16.85F)
            lineTo(65.67F, 26.15F)
            curveTo(65.74F, 26.25F, 65.8F, 26.35F, 65.88F, 26.44F)
            curveTo(65.93F, 26.49F, 65.98F, 26.54F, 66.01F, 26.59F)
            curveTo(66.55F, 27.35F, 67.67F, 27.45F, 68.44F, 26.88F)
            lineTo(85.17F, 14.68F)
            curveTo(85.88F, 14.16F, 85.99F, 13.0F, 85.65F, 12.17F)
            curveTo(85.61F, 12.07F, 85.56F, 11.98F, 85.49F, 11.9F)
            lineTo(78.9F, 2.86F)
            curveTo(78.31F, 2.05F, 77.79F, 1.71F, 77.29F, 1.7F)

            moveTo(35.22F, 18.1F)
            curveTo(34.59F, 18.1F, 33.96F, 18.38F, 33.6F, 18.9F)
            curveTo(33.53F, 18.99F, 33.48F, 19.1F, 33.44F, 19.22F)
            lineTo(29.71F, 30.11F)
            curveTo(29.67F, 30.22F, 29.62F, 30.33F, 29.6F, 30.44F)
            curveTo(29.58F, 30.51F, 29.56F, 30.58F, 29.54F, 30.63F)
            curveTo(29.23F, 31.51F, 29.79F, 32.48F, 30.7F, 32.79F)
            lineTo(50.29F, 39.51F)
            curveTo(51.12F, 39.79F, 52.14F, 39.21F, 52.62F, 38.46F)
            curveTo(52.68F, 38.37F, 52.72F, 38.28F, 52.75F, 38.18F)
            lineTo(56.38F, 27.59F)
            curveTo(56.96F, 25.91F, 56.44F, 25.27F, 55.33F, 24.89F)
            lineTo(35.84F, 18.21F)
            curveTo(35.64F, 18.14F, 35.43F, 18.11F, 35.22F, 18.1F)
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
            moveTo(72.36F, 8.53F)
            curveTo(71.18F, 8.51F, 69.99F, 8.87F, 68.96F, 9.62F)
            curveTo(66.43F, 11.46F, 65.87F, 15.0F, 67.71F, 17.53F)
            curveTo(69.56F, 20.05F, 73.1F, 20.61F, 75.63F, 18.76F)
            curveTo(78.16F, 16.92F, 78.71F, 13.38F, 76.87F, 10.85F)
            curveTo(75.78F, 9.35F, 74.09F, 8.55F, 72.36F, 8.53F)

            moveTo(43.19F, 23.01F)
            curveTo(40.88F, 23.06F, 38.74F, 24.52F, 37.95F, 26.83F)
            curveTo(36.94F, 29.79F, 38.51F, 33.01F, 41.47F, 34.03F)
            curveTo(44.44F, 35.04F, 47.66F, 33.47F, 48.67F, 30.51F)
            curveTo(49.68F, 27.55F, 48.11F, 24.33F, 45.14F, 23.32F)
            curveTo(44.5F, 23.1F, 43.84F, 23.0F, 43.19F, 23.01F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFFF7E2)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(73.62F, 9.99F)
            curveTo(73.47F, 9.99F, 73.35F, 10.1F, 73.32F, 10.25F)
            lineTo(73.15F, 11.33F)
            lineTo(72.08F, 11.16F)
            curveTo(71.91F, 11.14F, 71.75F, 11.25F, 71.72F, 11.42F)
            curveTo(71.69F, 11.6F, 71.81F, 11.76F, 71.98F, 11.79F)
            lineTo(73.06F, 11.95F)
            lineTo(72.89F, 13.03F)
            curveTo(72.86F, 13.2F, 72.98F, 13.37F, 73.15F, 13.39F)
            curveTo(73.32F, 13.42F, 73.48F, 13.3F, 73.51F, 13.13F)
            lineTo(73.68F, 12.05F)
            lineTo(74.76F, 12.22F)
            curveTo(74.93F, 12.25F, 75.09F, 12.13F, 75.12F, 11.96F)
            curveTo(75.14F, 11.78F, 75.03F, 11.62F, 74.85F, 11.6F)
            lineTo(73.78F, 11.43F)
            lineTo(73.95F, 10.35F)
            curveTo(73.97F, 10.18F, 73.86F, 10.02F, 73.68F, 9.99F)
            curveTo(73.66F, 9.99F, 73.64F, 9.99F, 73.62F, 9.99F)

            moveTo(69.69F, 12.85F)
            curveTo(69.54F, 12.86F, 69.42F, 12.97F, 69.39F, 13.12F)
            lineTo(69.22F, 14.2F)
            lineTo(68.15F, 14.03F)
            curveTo(67.98F, 14.0F, 67.81F, 14.12F, 67.79F, 14.29F)
            curveTo(67.76F, 14.46F, 67.88F, 14.63F, 68.05F, 14.65F)
            lineTo(69.13F, 14.82F)
            lineTo(68.96F, 15.9F)
            curveTo(68.93F, 16.07F, 69.05F, 16.23F, 69.22F, 16.26F)
            curveTo(69.39F, 16.29F, 69.55F, 16.17F, 69.58F, 16.0F)
            lineTo(69.75F, 14.92F)
            lineTo(70.83F, 15.09F)
            curveTo(71.0F, 15.11F, 71.16F, 15.0F, 71.19F, 14.82F)
            curveTo(71.21F, 14.65F, 71.1F, 14.49F, 70.92F, 14.46F)
            lineTo(69.85F, 14.29F)
            lineTo(70.02F, 13.22F)
            curveTo(70.04F, 13.04F, 69.93F, 12.88F, 69.75F, 12.86F)
            curveTo(69.73F, 12.85F, 69.71F, 12.85F, 69.69F, 12.85F)

            moveTo(75.63F, 14.08F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 75.35F, 14.26F)
            curveTo(74.26F, 16.03F, 73.58F, 16.56F, 71.66F, 16.95F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 71.37F, 17.39F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 71.81F, 17.69F)
            curveTo(73.84F, 17.28F, 74.85F, 16.5F, 75.98F, 14.65F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 75.86F, 14.13F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 75.63F, 14.08F)

            moveTo(40.76F, 25.01F)
            curveTo(40.72F, 25.01F, 40.68F, 25.02F, 40.64F, 25.04F)
            curveTo(40.48F, 25.12F, 40.42F, 25.3F, 40.5F, 25.46F)
            lineTo(40.97F, 26.44F)
            lineTo(40.0F, 26.92F)
            curveTo(39.84F, 27.0F, 39.77F, 27.19F, 39.85F, 27.34F)
            curveTo(39.93F, 27.5F, 40.12F, 27.56F, 40.27F, 27.49F)
            lineTo(41.25F, 27.01F)
            lineTo(41.73F, 27.98F)
            curveTo(41.81F, 28.14F, 42.0F, 28.21F, 42.15F, 28.13F)
            curveTo(42.31F, 28.05F, 42.38F, 27.86F, 42.3F, 27.71F)
            lineTo(41.82F, 26.73F)
            lineTo(42.8F, 26.25F)
            curveTo(42.96F, 26.17F, 43.02F, 25.98F, 42.94F, 25.83F)
            curveTo(42.87F, 25.67F, 42.68F, 25.61F, 42.52F, 25.68F)
            lineTo(41.54F, 26.16F)
            lineTo(41.06F, 25.18F)
            curveTo(41.01F, 25.07F, 40.88F, 25.0F, 40.76F, 25.01F)

            moveTo(45.37F, 26.59F)
            curveTo(45.32F, 26.59F, 45.28F, 26.6F, 45.24F, 26.62F)
            curveTo(45.09F, 26.69F, 45.02F, 26.88F, 45.1F, 27.04F)
            lineTo(45.58F, 28.02F)
            lineTo(44.6F, 28.5F)
            curveTo(44.44F, 28.57F, 44.38F, 28.76F, 44.45F, 28.92F)
            curveTo(44.53F, 29.08F, 44.72F, 29.14F, 44.88F, 29.06F)
            lineTo(45.86F, 28.58F)
            lineTo(46.33F, 29.56F)
            curveTo(46.41F, 29.72F, 46.6F, 29.78F, 46.76F, 29.71F)
            curveTo(46.91F, 29.63F, 46.98F, 29.44F, 46.9F, 29.29F)
            lineTo(46.42F, 28.31F)
            lineTo(47.4F, 27.83F)
            curveTo(47.56F, 27.75F, 47.62F, 27.56F, 47.55F, 27.4F)
            curveTo(47.47F, 27.25F, 47.28F, 27.18F, 47.13F, 27.26F)
            lineTo(46.14F, 27.74F)
            lineTo(45.67F, 26.76F)
            curveTo(45.61F, 26.64F, 45.49F, 26.58F, 45.37F, 26.59F)

            moveTo(40.41F, 29.63F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 40.15F, 29.75F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 40.16F, 30.28F)
            curveTo(41.67F, 31.71F, 42.88F, 32.09F, 45.05F, 31.96F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 45.4F, 31.56F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 45.0F, 31.21F)
            curveTo(42.92F, 31.34F, 42.1F, 31.09F, 40.68F, 29.74F)
            arcTo(0.37F, 0.37F, 0.0F, false, false, 40.41F, 29.63F)
            close()
        }.build()
        return _trashedFoldersList!!
    }
private var _trashedFoldersList: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconTrashedFoldersListPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.TrashedFoldersList, contentDescription = null)
    }
}