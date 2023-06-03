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
val AppImage.EmptyTrash: ImageVector
    @Composable get() {
        _emptyTrash = ImageVector.Builder(
            name = "EmptyTrash",
            defaultWidth = 100.0.dp,
            defaultHeight = 100.0.dp,
            viewportWidth = 100.0F,
            viewportHeight = 100.0F,
        ).path(
            fill = SolidColor(Theme.color.placeholderColors.c1),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(2.71F, 81.48F)
            arcToRelative(11.0F, 48.0F, 81.04F, true, false, 94.83F, -14.96F)
            arcToRelative(11.0F, 48.0F, 81.04F, true, false, -94.83F, 14.96F)
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
            moveTo(55.17F, 8.0F)
            curveTo(37.82F, 8.0F, 23.75F, 10.22F, 23.75F, 12.96F)
            curveTo(23.75F, 15.7F, 37.82F, 17.92F, 55.17F, 17.92F)
            curveTo(72.52F, 17.92F, 86.58F, 15.7F, 86.58F, 12.96F)
            curveTo(86.58F, 10.22F, 72.52F, 8.0F, 55.17F, 8.0F)
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
            moveTo(23.75F, 12.96F)
            curveTo(23.75F, 12.96F, 33.67F, 69.18F, 34.5F, 73.31F)
            curveTo(35.33F, 77.44F, 43.39F, 77.54F, 55.17F, 77.44F)
            curveTo(66.94F, 77.54F, 75.01F, 77.44F, 75.83F, 73.31F)
            curveTo(76.66F, 69.18F, 86.58F, 12.96F, 86.58F, 12.96F)
            curveTo(81.16F, 16.36F, 74.23F, 17.43F, 55.17F, 17.92F)
            curveTo(36.11F, 17.43F, 29.17F, 16.36F, 23.75F, 12.96F)
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
            moveTo(30.09F, 21.25F)
            curveTo(29.88F, 21.29F, 29.67F, 21.36F, 29.49F, 21.48F)
            curveTo(29.3F, 21.6F, 29.14F, 21.75F, 29.02F, 21.92F)
            curveTo(28.89F, 22.1F, 28.8F, 22.3F, 28.75F, 22.51F)
            curveTo(28.71F, 22.72F, 28.7F, 22.94F, 28.74F, 23.16F)
            lineTo(36.18F, 66.97F)
            curveTo(36.21F, 67.19F, 36.29F, 67.39F, 36.41F, 67.58F)
            curveTo(36.52F, 67.76F, 36.67F, 67.92F, 36.85F, 68.05F)
            curveTo(37.03F, 68.17F, 37.23F, 68.26F, 37.44F, 68.31F)
            curveTo(37.65F, 68.36F, 37.87F, 68.36F, 38.08F, 68.33F)
            curveTo(38.3F, 68.29F, 38.5F, 68.21F, 38.69F, 68.1F)
            curveTo(38.87F, 67.98F, 39.03F, 67.83F, 39.16F, 67.65F)
            curveTo(39.28F, 67.48F, 39.37F, 67.28F, 39.42F, 67.06F)
            curveTo(39.47F, 66.85F, 39.47F, 66.63F, 39.44F, 66.42F)
            lineTo(32.0F, 22.6F)
            curveTo(31.96F, 22.39F, 31.88F, 22.18F, 31.77F, 22.0F)
            curveTo(31.65F, 21.82F, 31.5F, 21.66F, 31.32F, 21.53F)
            curveTo(31.15F, 21.41F, 30.95F, 21.32F, 30.73F, 21.27F)
            curveTo(30.52F, 21.22F, 30.3F, 21.21F, 30.09F, 21.25F)

            moveTo(79.42F, 21.25F)
            curveTo(78.99F, 21.18F, 78.54F, 21.28F, 78.18F, 21.53F)
            curveTo(77.83F, 21.79F, 77.59F, 22.17F, 77.51F, 22.6F)
            lineTo(70.07F, 66.42F)
            curveTo(70.04F, 66.63F, 70.04F, 66.85F, 70.09F, 67.06F)
            curveTo(70.14F, 67.28F, 70.23F, 67.48F, 70.35F, 67.65F)
            curveTo(70.48F, 67.83F, 70.64F, 67.98F, 70.82F, 68.1F)
            curveTo(71.01F, 68.21F, 71.21F, 68.29F, 71.43F, 68.33F)
            curveTo(71.86F, 68.4F, 72.3F, 68.3F, 72.66F, 68.04F)
            curveTo(73.01F, 67.79F, 73.26F, 67.41F, 73.33F, 66.97F)
            lineTo(80.77F, 23.16F)
            curveTo(80.81F, 22.94F, 80.8F, 22.72F, 80.75F, 22.51F)
            curveTo(80.71F, 22.3F, 80.62F, 22.1F, 80.49F, 21.92F)
            curveTo(80.36F, 21.75F, 80.21F, 21.6F, 80.02F, 21.48F)
            curveTo(79.84F, 21.36F, 79.63F, 21.29F, 79.42F, 21.25F)

            moveTo(55.17F, 22.88F)
            curveTo(54.73F, 22.88F, 54.31F, 23.06F, 54.0F, 23.37F)
            curveTo(53.69F, 23.68F, 53.51F, 24.1F, 53.51F, 24.53F)
            verticalLineTo(69.18F)
            curveTo(53.51F, 69.39F, 53.56F, 69.61F, 53.64F, 69.81F)
            curveTo(53.72F, 70.01F, 53.84F, 70.19F, 54.0F, 70.35F)
            curveTo(54.15F, 70.5F, 54.33F, 70.62F, 54.53F, 70.7F)
            curveTo(54.73F, 70.79F, 54.95F, 70.83F, 55.17F, 70.83F)
            curveTo(55.38F, 70.83F, 55.6F, 70.79F, 55.8F, 70.7F)
            curveTo(56.0F, 70.62F, 56.18F, 70.5F, 56.34F, 70.35F)
            curveTo(56.49F, 70.19F, 56.61F, 70.01F, 56.69F, 69.81F)
            curveTo(56.78F, 69.61F, 56.82F, 69.39F, 56.82F, 69.18F)
            verticalLineTo(24.53F)
            curveTo(56.82F, 24.1F, 56.65F, 23.68F, 56.34F, 23.37F)
            curveTo(56.03F, 23.06F, 55.6F, 22.88F, 55.17F, 22.88F)

            moveTo(67.69F, 22.89F)
            curveTo(67.26F, 22.85F, 66.82F, 23.0F, 66.49F, 23.28F)
            curveTo(66.16F, 23.57F, 65.95F, 23.97F, 65.92F, 24.41F)
            lineTo(62.61F, 68.22F)
            curveTo(62.58F, 68.66F, 62.72F, 69.09F, 63.01F, 69.43F)
            curveTo(63.29F, 69.76F, 63.7F, 69.97F, 64.14F, 70.0F)
            curveTo(64.35F, 70.02F, 64.57F, 69.99F, 64.78F, 69.92F)
            curveTo(64.98F, 69.85F, 65.17F, 69.75F, 65.34F, 69.6F)
            curveTo(65.5F, 69.46F, 65.64F, 69.29F, 65.74F, 69.1F)
            curveTo(65.84F, 68.9F, 65.89F, 68.69F, 65.91F, 68.47F)
            lineTo(69.22F, 24.66F)
            curveTo(69.25F, 24.22F, 69.11F, 23.79F, 68.82F, 23.46F)
            curveTo(68.54F, 23.12F, 68.13F, 22.92F, 67.69F, 22.89F)

            moveTo(41.79F, 22.89F)
            curveTo(41.35F, 22.93F, 40.95F, 23.14F, 40.67F, 23.48F)
            curveTo(40.39F, 23.82F, 40.25F, 24.25F, 40.29F, 24.69F)
            lineTo(44.43F, 68.5F)
            curveTo(44.47F, 68.94F, 44.68F, 69.34F, 45.02F, 69.62F)
            curveTo(45.36F, 69.9F, 45.79F, 70.04F, 46.23F, 69.99F)
            curveTo(46.67F, 69.95F, 47.07F, 69.74F, 47.35F, 69.4F)
            curveTo(47.63F, 69.06F, 47.76F, 68.63F, 47.72F, 68.19F)
            lineTo(43.59F, 24.38F)
            curveTo(43.55F, 23.94F, 43.33F, 23.54F, 42.99F, 23.26F)
            curveTo(42.66F, 22.98F, 42.22F, 22.85F, 41.79F, 22.89F)
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
            moveTo(37.01F, 25.08F)
            curveTo(30.19F, 24.87F, 20.27F, 34.01F, 13.33F, 47.51F)
            curveTo(5.39F, 62.93F, 4.33F, 78.21F, 10.97F, 81.63F)
            curveTo(17.62F, 85.04F, 29.44F, 75.31F, 37.38F, 59.88F)
            curveTo(45.32F, 44.46F, 46.37F, 29.18F, 39.73F, 25.76F)
            curveTo(38.9F, 25.33F, 37.98F, 25.11F, 37.01F, 25.08F)
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
            moveTo(38.07F, 24.11F)
            curveTo(44.71F, 27.53F, 43.66F, 42.8F, 35.72F, 58.23F)
            curveTo(27.78F, 73.66F, 15.96F, 83.39F, 9.32F, 79.97F)
            curveTo(2.68F, 76.56F, 3.73F, 61.28F, 11.67F, 45.85F)
            curveTo(19.61F, 30.43F, 31.43F, 20.69F, 38.07F, 24.11F)
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
            moveTo(34.83F, 27.38F)
            curveTo(34.03F, 27.39F, 33.17F, 27.57F, 32.25F, 27.91F)
            curveTo(30.42F, 28.57F, 28.44F, 29.82F, 26.39F, 31.53F)
            curveTo(22.31F, 34.96F, 18.13F, 40.19F, 15.05F, 46.17F)
            curveTo(11.97F, 52.16F, 9.77F, 58.67F, 8.97F, 64.06F)
            curveTo(8.56F, 66.76F, 8.52F, 69.12F, 8.9F, 71.03F)
            curveTo(9.27F, 72.94F, 10.09F, 74.32F, 11.35F, 74.96F)
            curveTo(12.63F, 75.62F, 14.26F, 75.51F, 16.15F, 74.76F)
            curveTo(18.01F, 74.01F, 20.04F, 72.66F, 22.14F, 70.84F)
            curveTo(26.32F, 67.19F, 30.6F, 61.75F, 33.68F, 55.76F)
            curveTo(36.76F, 49.78F, 38.86F, 43.49F, 39.56F, 38.31F)
            curveTo(39.92F, 35.72F, 39.92F, 33.46F, 39.5F, 31.64F)
            curveTo(39.08F, 29.82F, 38.24F, 28.49F, 36.98F, 27.84F)
            curveTo(36.34F, 27.51F, 35.62F, 27.36F, 34.83F, 27.38F)

            moveTo(34.84F, 29.03F)
            curveTo(35.4F, 29.02F, 35.86F, 29.12F, 36.22F, 29.31F)
            curveTo(36.94F, 29.68F, 37.54F, 30.48F, 37.89F, 32.01F)
            curveTo(38.24F, 33.54F, 38.26F, 35.63F, 37.93F, 38.09F)
            curveTo(37.26F, 43.0F, 35.22F, 49.17F, 32.21F, 55.01F)
            curveTo(29.23F, 60.8F, 25.04F, 66.11F, 21.05F, 69.59F)
            curveTo(19.06F, 71.33F, 17.14F, 72.58F, 15.53F, 73.23F)
            curveTo(13.91F, 73.88F, 12.79F, 73.84F, 12.11F, 73.49F)
            curveTo(11.43F, 73.14F, 10.84F, 72.33F, 10.52F, 70.71F)
            curveTo(10.2F, 69.09F, 10.22F, 66.88F, 10.6F, 64.3F)
            curveTo(11.36F, 59.16F, 13.51F, 52.77F, 16.52F, 46.93F)
            curveTo(19.5F, 41.14F, 23.58F, 36.05F, 27.46F, 32.8F)
            curveTo(29.4F, 31.17F, 31.25F, 30.03F, 32.81F, 29.46F)
            curveTo(33.6F, 29.17F, 34.27F, 29.04F, 34.84F, 29.03F)
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
            moveTo(27.84F, 43.75F)
            curveTo(27.65F, 43.64F, 27.44F, 43.58F, 27.23F, 43.56F)
            curveTo(27.01F, 43.53F, 26.79F, 43.56F, 26.59F, 43.62F)
            curveTo(26.38F, 43.68F, 26.18F, 43.78F, 26.02F, 43.92F)
            curveTo(25.85F, 44.06F, 25.71F, 44.23F, 25.6F, 44.42F)
            lineTo(19.82F, 55.17F)
            curveTo(19.61F, 55.55F, 19.56F, 56.0F, 19.69F, 56.42F)
            curveTo(19.82F, 56.84F, 20.1F, 57.2F, 20.49F, 57.4F)
            curveTo(20.88F, 57.61F, 21.33F, 57.66F, 21.75F, 57.53F)
            curveTo(22.17F, 57.4F, 22.52F, 57.12F, 22.73F, 56.73F)
            lineTo(28.52F, 45.99F)
            curveTo(28.62F, 45.79F, 28.68F, 45.59F, 28.71F, 45.37F)
            curveTo(28.73F, 45.15F, 28.71F, 44.93F, 28.64F, 44.73F)
            curveTo(28.58F, 44.52F, 28.48F, 44.33F, 28.34F, 44.16F)
            curveTo(28.2F, 43.99F, 28.04F, 43.85F, 27.84F, 43.75F)
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
            moveTo(25.68F, 42.76F)
            curveTo(24.7F, 42.7F, 23.81F, 43.02F, 23.02F, 43.46F)
            curveTo(21.43F, 44.36F, 20.09F, 45.84F, 19.04F, 47.53F)
            curveTo(17.99F, 49.22F, 17.23F, 51.1F, 17.27F, 53.02F)
            curveTo(17.29F, 53.98F, 17.54F, 54.98F, 18.16F, 55.83F)
            curveTo(18.78F, 56.67F, 19.75F, 57.27F, 20.87F, 57.55F)
            curveTo(21.08F, 57.61F, 21.3F, 57.62F, 21.52F, 57.58F)
            curveTo(21.73F, 57.55F, 21.94F, 57.48F, 22.12F, 57.37F)
            curveTo(22.31F, 57.25F, 22.47F, 57.11F, 22.6F, 56.93F)
            curveTo(22.73F, 56.76F, 22.83F, 56.56F, 22.88F, 56.35F)
            curveTo(22.93F, 56.14F, 22.94F, 55.92F, 22.91F, 55.7F)
            curveTo(22.88F, 55.49F, 22.8F, 55.28F, 22.69F, 55.1F)
            curveTo(22.58F, 54.91F, 22.43F, 54.75F, 22.26F, 54.62F)
            curveTo(22.08F, 54.49F, 21.88F, 54.4F, 21.67F, 54.34F)
            curveTo(21.14F, 54.21F, 20.95F, 54.04F, 20.82F, 53.86F)
            curveTo(20.68F, 53.68F, 20.58F, 53.41F, 20.57F, 52.96F)
            curveTo(20.56F, 52.06F, 21.04F, 50.59F, 21.85F, 49.28F)
            curveTo(22.66F, 47.97F, 23.8F, 46.82F, 24.64F, 46.34F)
            curveTo(25.06F, 46.11F, 25.38F, 46.05F, 25.5F, 46.06F)
            curveTo(25.62F, 46.07F, 25.61F, 46.03F, 25.77F, 46.24F)
            curveTo(26.04F, 46.58F, 26.44F, 46.8F, 26.88F, 46.84F)
            curveTo(27.31F, 46.89F, 27.75F, 46.77F, 28.09F, 46.49F)
            curveTo(28.26F, 46.36F, 28.4F, 46.19F, 28.51F, 46.0F)
            curveTo(28.61F, 45.81F, 28.68F, 45.6F, 28.7F, 45.38F)
            curveTo(28.73F, 45.17F, 28.71F, 44.95F, 28.65F, 44.74F)
            curveTo(28.59F, 44.53F, 28.49F, 44.34F, 28.35F, 44.17F)
            curveTo(27.69F, 43.34F, 26.67F, 42.81F, 25.68F, 42.76F)
            close()
        }.build()
        return _emptyTrash!!
    }
private var _emptyTrash: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconEmptyTrashPreview() {
    BerestaTheme {
        Image(imageVector = AppImage.EmptyTrash, contentDescription = null)
    }
}