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
 * @Author maksonic on 01.06.2023
 */
val AppImage.TrashedNote: ImageVector
    @Composable get() {

        _trashedNotes = ImageVector.Builder(
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
            fill = SolidColor(Color(0xFF515151)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(55.7F, 29.41F)
            curveTo(41.06F, 29.41F, 29.19F, 31.29F, 29.19F, 33.6F)
            curveTo(29.19F, 35.91F, 41.06F, 37.79F, 55.7F, 37.79F)
            curveTo(70.34F, 37.79F, 82.21F, 35.91F, 82.21F, 33.6F)
            curveTo(82.21F, 31.29F, 70.34F, 29.41F, 55.7F, 29.41F)
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
            moveTo(62.08F, 4.7F)
            lineTo(77.7F, 29.23F)
            curveTo(79.43F, 31.94F, 78.63F, 35.54F, 75.91F, 37.26F)
            lineTo(54.48F, 50.92F)
            curveTo(51.76F, 52.65F, 48.16F, 51.85F, 46.44F, 49.14F)
            lineTo(27.12F, 18.82F)
            curveTo(25.4F, 16.11F, 26.2F, 12.51F, 28.91F, 10.78F)
            lineTo(44.54F, 0.83F)
            lineTo(62.08F, 4.7F)
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
            moveTo(40.85F, 11.18F)
            curveTo(39.57F, 11.2F, 38.29F, 11.57F, 37.14F, 12.3F)
            curveTo(33.79F, 14.44F, 32.8F, 18.88F, 34.93F, 22.23F)
            curveTo(37.06F, 25.58F, 41.51F, 26.56F, 44.86F, 24.42F)
            curveTo(48.21F, 22.29F, 49.2F, 17.84F, 47.07F, 14.5F)
            curveTo(45.67F, 12.3F, 43.27F, 11.12F, 40.85F, 11.18F)

            moveTo(60.09F, 12.81F)
            curveTo(59.63F, 12.8F, 59.17F, 12.92F, 58.75F, 13.19F)
            lineTo(51.54F, 17.78F)
            curveTo(50.43F, 18.49F, 50.1F, 19.96F, 50.81F, 21.06F)
            curveTo(51.51F, 22.17F, 52.98F, 22.49F, 54.09F, 21.79F)
            lineTo(61.3F, 17.2F)
            curveTo(62.41F, 16.49F, 62.73F, 15.02F, 62.03F, 13.91F)
            curveTo(61.59F, 13.22F, 60.85F, 12.84F, 60.09F, 12.81F)

            moveTo(64.12F, 18.32F)
            curveTo(63.76F, 18.31F, 63.38F, 18.4F, 63.05F, 18.62F)
            lineTo(57.84F, 21.93F)
            curveTo(56.96F, 22.5F, 56.7F, 23.67F, 57.26F, 24.56F)
            lineTo(60.58F, 29.77F)
            curveTo(61.14F, 30.65F, 62.32F, 30.91F, 63.2F, 30.35F)
            lineTo(68.41F, 27.03F)
            curveTo(69.3F, 26.47F, 69.56F, 25.29F, 68.99F, 24.41F)
            lineTo(65.68F, 19.2F)
            curveTo(65.32F, 18.64F, 64.73F, 18.33F, 64.12F, 18.32F)

            moveTo(54.17F, 27.85F)
            curveTo(53.71F, 27.84F, 53.25F, 27.96F, 52.83F, 28.22F)
            lineTo(44.82F, 33.33F)
            curveTo(43.71F, 34.03F, 43.38F, 35.5F, 44.09F, 36.61F)
            curveTo(44.79F, 37.71F, 46.26F, 38.04F, 47.37F, 37.34F)
            lineTo(55.38F, 32.23F)
            curveTo(56.49F, 31.53F, 56.82F, 30.06F, 56.11F, 28.95F)
            curveTo(55.67F, 28.26F, 54.93F, 27.87F, 54.17F, 27.85F)

            moveTo(70.5F, 28.72F)
            curveTo(70.04F, 28.71F, 69.58F, 28.83F, 69.16F, 29.09F)
            lineTo(49.92F, 41.34F)
            curveTo(48.82F, 42.05F, 48.49F, 43.52F, 49.2F, 44.62F)
            curveTo(49.9F, 45.73F, 51.37F, 46.06F, 52.47F, 45.35F)
            lineTo(71.71F, 33.1F)
            curveTo(72.82F, 32.39F, 73.15F, 30.93F, 72.44F, 29.82F)
            curveTo(72.0F, 29.13F, 71.26F, 28.74F, 70.5F, 28.72F)
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
            moveTo(62.08F, 4.7F)
            lineTo(56.27F, 8.4F)
            curveTo(53.56F, 10.13F, 49.96F, 9.33F, 48.24F, 6.62F)
            lineTo(44.54F, 0.83F)
            lineTo(62.08F, 4.7F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF5F7FAF)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(35.67F, 18.22F)
            lineTo(37.41F, 18.61F)
            lineTo(39.15F, 18.99F)
            moveTo(37.8F, 16.86F)
            lineTo(37.41F, 18.61F)
            lineTo(37.03F, 20.35F)
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
            moveTo(43.02F, 13.14F)
            curveTo(42.83F, 13.14F, 42.66F, 13.27F, 42.62F, 13.46F)
            lineTo(42.32F, 14.81F)
            lineTo(40.97F, 14.51F)
            curveTo(40.75F, 14.46F, 40.54F, 14.6F, 40.49F, 14.82F)
            curveTo(40.44F, 15.03F, 40.58F, 15.25F, 40.8F, 15.29F)
            lineTo(42.15F, 15.59F)
            lineTo(41.85F, 16.95F)
            curveTo(41.8F, 17.16F, 41.93F, 17.37F, 42.15F, 17.42F)
            curveTo(42.37F, 17.47F, 42.58F, 17.33F, 42.63F, 17.12F)
            lineTo(42.93F, 15.77F)
            lineTo(44.28F, 16.07F)
            curveTo(44.49F, 16.11F, 44.71F, 15.98F, 44.76F, 15.76F)
            curveTo(44.8F, 15.55F, 44.67F, 15.33F, 44.45F, 15.28F)
            lineTo(43.1F, 14.98F)
            lineTo(43.4F, 13.63F)
            curveTo(43.45F, 13.42F, 43.31F, 13.2F, 43.1F, 13.15F)
            curveTo(43.07F, 13.15F, 43.04F, 13.15F, 43.02F, 13.14F)

            moveTo(37.81F, 16.46F)
            curveTo(37.62F, 16.46F, 37.45F, 16.59F, 37.41F, 16.78F)
            lineTo(37.11F, 18.13F)
            lineTo(35.76F, 17.83F)
            curveTo(35.54F, 17.78F, 35.33F, 17.92F, 35.28F, 18.13F)
            curveTo(35.23F, 18.35F, 35.37F, 18.56F, 35.59F, 18.61F)
            lineTo(36.94F, 18.91F)
            lineTo(36.64F, 20.26F)
            curveTo(36.59F, 20.48F, 36.72F, 20.69F, 36.94F, 20.74F)
            curveTo(37.16F, 20.79F, 37.37F, 20.65F, 37.42F, 20.44F)
            lineTo(37.72F, 19.08F)
            lineTo(39.07F, 19.38F)
            curveTo(39.28F, 19.43F, 39.5F, 19.3F, 39.54F, 19.08F)
            curveTo(39.59F, 18.86F, 39.46F, 18.65F, 39.24F, 18.6F)
            lineTo(37.89F, 18.3F)
            lineTo(38.19F, 16.95F)
            curveTo(38.24F, 16.74F, 38.1F, 16.52F, 37.89F, 16.47F)
            curveTo(37.86F, 16.47F, 37.83F, 16.46F, 37.81F, 16.46F)

            moveTo(45.23F, 18.49F)
            curveTo(45.21F, 18.49F, 45.19F, 18.5F, 45.17F, 18.5F)
            curveTo(45.04F, 18.52F, 44.93F, 18.59F, 44.86F, 18.7F)
            curveTo(43.34F, 20.86F, 42.44F, 21.48F, 39.97F, 21.82F)
            curveTo(39.85F, 21.83F, 39.74F, 21.9F, 39.66F, 22.0F)
            curveTo(39.58F, 22.1F, 39.55F, 22.23F, 39.57F, 22.35F)
            curveTo(39.59F, 22.48F, 39.65F, 22.59F, 39.75F, 22.67F)
            curveTo(39.85F, 22.74F, 39.98F, 22.77F, 40.1F, 22.76F)
            curveTo(42.71F, 22.4F, 44.05F, 21.49F, 45.64F, 19.24F)
            curveTo(45.67F, 19.19F, 45.7F, 19.13F, 45.71F, 19.07F)
            curveTo(45.73F, 19.01F, 45.73F, 18.95F, 45.72F, 18.89F)
            curveTo(45.71F, 18.83F, 45.69F, 18.77F, 45.65F, 18.71F)
            curveTo(45.62F, 18.66F, 45.57F, 18.62F, 45.52F, 18.58F)
            curveTo(45.46F, 18.53F, 45.38F, 18.5F, 45.29F, 18.49F)
            curveTo(45.27F, 18.49F, 45.25F, 18.49F, 45.23F, 18.49F)
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
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(34.54F, 40.6F)
            curveTo(34.17F, 40.66F, 33.85F, 40.86F, 33.64F, 41.16F)
            curveTo(33.42F, 41.46F, 33.33F, 41.84F, 33.4F, 42.2F)
            lineTo(39.68F, 79.18F)
            curveTo(39.74F, 79.54F, 39.94F, 79.87F, 40.24F, 80.08F)
            curveTo(40.55F, 80.3F, 40.92F, 80.38F, 41.29F, 80.32F)
            curveTo(41.65F, 80.26F, 41.97F, 80.05F, 42.19F, 79.75F)
            curveTo(42.4F, 79.45F, 42.49F, 79.07F, 42.43F, 78.71F)
            lineTo(36.15F, 41.74F)
            curveTo(36.09F, 41.37F, 35.88F, 41.05F, 35.58F, 40.83F)
            curveTo(35.28F, 40.62F, 34.9F, 40.53F, 34.54F, 40.6F)

            moveTo(76.16F, 40.6F)
            curveTo(75.8F, 40.53F, 75.43F, 40.62F, 75.12F, 40.83F)
            curveTo(74.82F, 41.05F, 74.62F, 41.37F, 74.55F, 41.74F)
            lineTo(68.28F, 78.71F)
            curveTo(68.22F, 79.07F, 68.3F, 79.45F, 68.51F, 79.75F)
            curveTo(68.73F, 80.05F, 69.05F, 80.26F, 69.42F, 80.32F)
            curveTo(69.78F, 80.38F, 70.16F, 80.3F, 70.46F, 80.08F)
            curveTo(70.76F, 79.87F, 70.97F, 79.54F, 71.03F, 79.18F)
            lineTo(77.31F, 42.2F)
            curveTo(77.37F, 41.84F, 77.28F, 41.46F, 77.07F, 41.16F)
            curveTo(76.85F, 40.86F, 76.53F, 40.66F, 76.16F, 40.6F)

            moveTo(55.7F, 41.97F)
            curveTo(55.52F, 41.97F, 55.34F, 42.01F, 55.17F, 42.08F)
            curveTo(55.0F, 42.15F, 54.84F, 42.25F, 54.71F, 42.38F)
            curveTo(54.58F, 42.51F, 54.48F, 42.66F, 54.41F, 42.83F)
            curveTo(54.34F, 43.0F, 54.3F, 43.18F, 54.3F, 43.37F)
            verticalLineTo(81.04F)
            curveTo(54.3F, 81.22F, 54.34F, 81.4F, 54.41F, 81.57F)
            curveTo(54.48F, 81.74F, 54.58F, 81.89F, 54.71F, 82.02F)
            curveTo(54.84F, 82.15F, 55.0F, 82.26F, 55.17F, 82.33F)
            curveTo(55.34F, 82.4F, 55.52F, 82.43F, 55.7F, 82.43F)
            curveTo(55.88F, 82.43F, 56.07F, 82.4F, 56.24F, 82.33F)
            curveTo(56.4F, 82.25F, 56.56F, 82.15F, 56.69F, 82.02F)
            curveTo(56.82F, 81.89F, 56.92F, 81.74F, 56.99F, 81.57F)
            curveTo(57.06F, 81.4F, 57.1F, 81.22F, 57.1F, 81.04F)
            verticalLineTo(43.37F)
            curveTo(57.1F, 43.0F, 56.95F, 42.64F, 56.69F, 42.38F)
            curveTo(56.43F, 42.12F, 56.07F, 41.97F, 55.7F, 41.97F)

            moveTo(66.27F, 41.97F)
            curveTo(65.9F, 41.95F, 65.54F, 42.07F, 65.26F, 42.31F)
            curveTo(64.97F, 42.55F, 64.8F, 42.89F, 64.77F, 43.26F)
            lineTo(61.98F, 80.23F)
            curveTo(61.97F, 80.42F, 61.99F, 80.6F, 62.05F, 80.77F)
            curveTo(62.11F, 80.95F, 62.2F, 81.11F, 62.32F, 81.25F)
            curveTo(62.44F, 81.39F, 62.58F, 81.5F, 62.74F, 81.58F)
            curveTo(62.91F, 81.67F, 63.09F, 81.72F, 63.27F, 81.73F)
            curveTo(63.45F, 81.74F, 63.64F, 81.72F, 63.81F, 81.66F)
            curveTo(63.98F, 81.61F, 64.15F, 81.52F, 64.28F, 81.4F)
            curveTo(64.42F, 81.28F, 64.54F, 81.13F, 64.62F, 80.97F)
            curveTo(64.7F, 80.8F, 64.75F, 80.63F, 64.77F, 80.44F)
            lineTo(67.56F, 43.47F)
            curveTo(67.57F, 43.29F, 67.55F, 43.1F, 67.49F, 42.93F)
            curveTo(67.43F, 42.76F, 67.34F, 42.59F, 67.22F, 42.46F)
            curveTo(67.1F, 42.32F, 66.96F, 42.2F, 66.79F, 42.12F)
            curveTo(66.63F, 42.04F, 66.45F, 41.99F, 66.27F, 41.97F)

            moveTo(44.41F, 41.98F)
            curveTo(44.04F, 42.01F, 43.7F, 42.19F, 43.46F, 42.48F)
            curveTo(43.23F, 42.76F, 43.12F, 43.13F, 43.15F, 43.5F)
            lineTo(46.64F, 80.47F)
            curveTo(46.67F, 80.84F, 46.85F, 81.18F, 47.14F, 81.41F)
            curveTo(47.42F, 81.65F, 47.79F, 81.76F, 48.16F, 81.73F)
            curveTo(48.53F, 81.69F, 48.87F, 81.51F, 49.1F, 81.23F)
            curveTo(49.34F, 80.94F, 49.45F, 80.58F, 49.42F, 80.21F)
            lineTo(45.93F, 43.23F)
            curveTo(45.89F, 42.87F, 45.71F, 42.53F, 45.43F, 42.29F)
            curveTo(45.14F, 42.06F, 44.78F, 41.94F, 44.41F, 41.98F)
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
            moveTo(40.38F, 43.83F)
            curveTo(34.62F, 43.65F, 26.26F, 51.36F, 20.39F, 62.75F)
            curveTo(13.69F, 75.77F, 12.81F, 88.66F, 18.41F, 91.54F)
            curveTo(24.01F, 94.43F, 33.99F, 86.21F, 40.69F, 73.19F)
            curveTo(47.39F, 60.18F, 48.28F, 47.29F, 42.67F, 44.4F)
            curveTo(41.97F, 44.04F, 41.2F, 43.85F, 40.38F, 43.83F)
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
            moveTo(38.54F, 45.77F)
            curveTo(37.87F, 45.78F, 37.13F, 45.93F, 36.36F, 46.21F)
            curveTo(34.82F, 46.77F, 33.14F, 47.82F, 31.42F, 49.27F)
            curveTo(27.97F, 52.17F, 24.45F, 56.58F, 21.85F, 61.63F)
            curveTo(19.25F, 66.68F, 17.39F, 72.17F, 16.71F, 76.72F)
            curveTo(16.37F, 78.99F, 16.34F, 80.99F, 16.65F, 82.6F)
            curveTo(16.97F, 84.21F, 17.67F, 85.37F, 18.73F, 85.92F)
            curveTo(19.8F, 86.47F, 21.19F, 86.38F, 22.77F, 85.75F)
            curveTo(24.35F, 85.12F, 26.06F, 83.98F, 27.83F, 82.44F)
            curveTo(31.36F, 79.36F, 34.97F, 74.77F, 37.57F, 69.72F)
            curveTo(40.17F, 64.66F, 41.94F, 59.36F, 42.54F, 54.99F)
            curveTo(42.83F, 52.81F, 42.83F, 50.9F, 42.48F, 49.36F)
            curveTo(42.13F, 47.82F, 41.42F, 46.7F, 40.35F, 46.16F)
            curveTo(39.82F, 45.88F, 39.21F, 45.75F, 38.54F, 45.77F)

            moveTo(38.54F, 47.16F)
            curveTo(39.02F, 47.15F, 39.41F, 47.24F, 39.71F, 47.4F)
            curveTo(40.32F, 47.71F, 40.82F, 48.38F, 41.12F, 49.67F)
            curveTo(41.41F, 50.96F, 41.43F, 52.73F, 41.15F, 54.8F)
            curveTo(40.59F, 58.95F, 38.86F, 64.15F, 36.33F, 69.08F)
            curveTo(33.81F, 73.97F, 30.28F, 78.45F, 26.91F, 81.39F)
            curveTo(25.23F, 82.86F, 23.61F, 83.91F, 22.25F, 84.45F)
            curveTo(20.88F, 85.0F, 19.94F, 84.98F, 19.37F, 84.68F)
            horizontalLineTo(19.37F)
            curveTo(18.8F, 84.38F, 18.29F, 83.7F, 18.02F, 82.33F)
            curveTo(17.75F, 80.96F, 17.77F, 79.09F, 18.09F, 76.92F)
            curveTo(18.74F, 72.58F, 20.55F, 67.19F, 23.09F, 62.27F)
            curveTo(25.6F, 57.38F, 29.05F, 53.09F, 32.32F, 50.34F)
            curveTo(33.95F, 48.97F, 35.52F, 48.0F, 36.84F, 47.52F)
            curveTo(37.5F, 47.28F, 38.07F, 47.17F, 38.54F, 47.16F)
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
            moveTo(32.64F, 59.58F)
            curveTo(32.48F, 59.49F, 32.31F, 59.44F, 32.12F, 59.42F)
            curveTo(31.94F, 59.4F, 31.76F, 59.42F, 31.58F, 59.47F)
            curveTo(31.41F, 59.52F, 31.24F, 59.61F, 31.1F, 59.72F)
            curveTo(30.96F, 59.84F, 30.84F, 59.98F, 30.75F, 60.14F)
            lineTo(28.7F, 63.95F)
            lineTo(28.2F, 64.88F)
            lineTo(25.87F, 69.21F)
            curveTo(25.78F, 69.37F, 25.73F, 69.55F, 25.71F, 69.73F)
            curveTo(25.69F, 69.92F, 25.71F, 70.1F, 25.76F, 70.28F)
            curveTo(25.82F, 70.45F, 25.9F, 70.61F, 26.02F, 70.76F)
            curveTo(26.13F, 70.9F, 26.28F, 71.02F, 26.44F, 71.1F)
            curveTo(26.6F, 71.19F, 26.78F, 71.24F, 26.96F, 71.26F)
            curveTo(27.14F, 71.28F, 27.33F, 71.26F, 27.5F, 71.21F)
            curveTo(27.68F, 71.16F, 27.84F, 71.07F, 27.98F, 70.96F)
            curveTo(28.12F, 70.84F, 28.24F, 70.7F, 28.33F, 70.54F)
            lineTo(30.66F, 66.21F)
            lineTo(31.16F, 65.27F)
            lineTo(33.21F, 61.47F)
            curveTo(33.3F, 61.31F, 33.35F, 61.13F, 33.37F, 60.95F)
            curveTo(33.39F, 60.76F, 33.37F, 60.58F, 33.32F, 60.4F)
            curveTo(33.27F, 60.23F, 33.18F, 60.07F, 33.06F, 59.92F)
            curveTo(32.95F, 59.78F, 32.81F, 59.66F, 32.64F, 59.58F)
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
            moveTo(30.82F, 58.74F)
            curveTo(29.99F, 58.7F, 29.24F, 58.96F, 28.57F, 59.34F)
            curveTo(27.23F, 60.09F, 26.1F, 61.35F, 25.22F, 62.77F)
            curveTo(24.33F, 64.2F, 23.69F, 65.79F, 23.72F, 67.4F)
            curveTo(23.74F, 68.21F, 23.94F, 69.06F, 24.47F, 69.77F)
            curveTo(24.99F, 70.48F, 25.81F, 70.99F, 26.76F, 71.23F)
            curveTo(26.94F, 71.27F, 27.12F, 71.28F, 27.3F, 71.26F)
            curveTo(27.49F, 71.23F, 27.66F, 71.17F, 27.82F, 71.07F)
            curveTo(27.97F, 70.98F, 28.11F, 70.85F, 28.22F, 70.71F)
            curveTo(28.33F, 70.56F, 28.41F, 70.39F, 28.45F, 70.21F)
            curveTo(28.54F, 69.85F, 28.49F, 69.47F, 28.3F, 69.16F)
            curveTo(28.11F, 68.84F, 27.8F, 68.61F, 27.44F, 68.52F)
            curveTo(26.99F, 68.41F, 26.83F, 68.27F, 26.71F, 68.11F)
            curveTo(26.6F, 67.96F, 26.52F, 67.73F, 26.51F, 67.35F)
            curveTo(26.5F, 66.6F, 26.9F, 65.35F, 27.59F, 64.24F)
            curveTo(28.27F, 63.14F, 29.23F, 62.17F, 29.94F, 61.77F)
            curveTo(30.3F, 61.57F, 30.56F, 61.52F, 30.66F, 61.53F)
            curveTo(30.77F, 61.53F, 30.75F, 61.51F, 30.89F, 61.68F)
            curveTo(31.12F, 61.97F, 31.46F, 62.15F, 31.83F, 62.19F)
            curveTo(32.2F, 62.23F, 32.56F, 62.13F, 32.85F, 61.89F)
            curveTo(33.14F, 61.66F, 33.33F, 61.33F, 33.37F, 60.96F)
            curveTo(33.41F, 60.59F, 33.3F, 60.22F, 33.07F, 59.93F)
            curveTo(32.51F, 59.23F, 31.65F, 58.79F, 30.82F, 58.74F)
            close()
        }.build()
        return _trashedNotes!!
    }
private var _trashedNotes: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconTrashedFolderPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.TrashedNote, contentDescription = null)
    }
}