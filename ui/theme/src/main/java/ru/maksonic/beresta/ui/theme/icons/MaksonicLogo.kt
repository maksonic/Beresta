package ru.maksonic.beresta.ui.theme.icons

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Author maksonic on 31.01.2023
 */
val AppIcon.MaksonicLogo: ImageVector
    get() {
        if (_maksonicLogo != null) {
            return _maksonicLogo!!
        }
        _maksonicLogo = ImageVector.Builder(
            name = "MaksonicLogo",
            defaultWidth = 200.0.dp,
            defaultHeight = 200.0.dp,
            viewportWidth = 200.0F,
            viewportHeight = 200.0F,
        ).path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 4.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(114.83F, 46.43F)
            lineToRelative(39.6F, -39.6F)
            lineToRelative(39.6F, 39.6F)
            lineToRelative(-39.6F, 39.6F)
            close()
        }.path(
            fill = SolidColor(Color(0x00000000)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 4.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Round,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(34.4F, 166.4F)
            moveToRelative(28.0F, -0.0F)
            arcToRelative(28.0F, 28.0F, 0.0F, true, true, -56.0F, -0.0F)
            arcToRelative(28.0F, 28.0F, 0.0F, true, true, 56.0F, -0.0F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF073042)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(54.4F, 46.4F)
            horizontalLineTo(14.4F)
            verticalLineTo(166.4F)
            horizontalLineTo(54.4F)
            verticalLineTo(46.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF3DDB84)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(48.54F, 32.26F)
            lineTo(20.26F, 60.54F)
            lineTo(140.54F, 180.82F)
            lineTo(168.82F, 152.54F)
            lineTo(48.54F, 32.26F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF3DDB84)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(174.4F, 46.4F)
            horizontalLineTo(134.4F)
            verticalLineTo(166.4F)
            horizontalLineTo(174.4F)
            verticalLineTo(46.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF5722)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(34.4F, 186.4F)
            curveTo(45.45F, 186.4F, 54.4F, 177.45F, 54.4F, 166.4F)
            curveTo(54.4F, 155.35F, 45.45F, 146.4F, 34.4F, 146.4F)
            curveTo(23.35F, 146.4F, 14.4F, 155.35F, 14.4F, 166.4F)
            curveTo(14.4F, 177.45F, 23.35F, 186.4F, 34.4F, 186.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF5722)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(154.4F, 186.4F)
            curveTo(165.45F, 186.4F, 174.4F, 177.45F, 174.4F, 166.4F)
            curveTo(174.4F, 155.35F, 165.45F, 146.4F, 154.4F, 146.4F)
            curveTo(143.35F, 146.4F, 134.4F, 155.35F, 134.4F, 166.4F)
            curveTo(134.4F, 177.45F, 143.35F, 186.4F, 154.4F, 186.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF5722)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(154.4F, 66.4F)
            curveTo(165.45F, 66.4F, 174.4F, 57.45F, 174.4F, 46.4F)
            curveTo(174.4F, 35.35F, 165.45F, 26.4F, 154.4F, 26.4F)
            curveTo(143.35F, 26.4F, 134.4F, 35.35F, 134.4F, 46.4F)
            curveTo(134.4F, 57.45F, 143.35F, 66.4F, 154.4F, 66.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF3DDB84)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(34.4F, 66.4F)
            curveTo(45.45F, 66.4F, 54.4F, 57.45F, 54.4F, 46.4F)
            curveTo(54.4F, 35.35F, 45.45F, 26.4F, 34.4F, 26.4F)
            curveTo(23.35F, 26.4F, 14.4F, 35.35F, 14.4F, 46.4F)
            curveTo(14.4F, 57.45F, 23.35F, 66.4F, 34.4F, 66.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF4385F5)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(168.54F, 60.54F)
            lineTo(140.25F, 32.26F)
            lineTo(80.32F, 92.19F)
            lineTo(108.6F, 120.48F)
            lineTo(168.54F, 60.54F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF4385F5)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(94.4F, 126.4F)
            curveTo(105.45F, 126.4F, 114.4F, 117.45F, 114.4F, 106.4F)
            curveTo(114.4F, 95.35F, 105.45F, 86.4F, 94.4F, 86.4F)
            curveTo(83.35F, 86.4F, 74.4F, 95.35F, 74.4F, 106.4F)
            curveTo(74.4F, 117.45F, 83.35F, 126.4F, 94.4F, 126.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF4385F5)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(174.4F, 46.4F)
            horizontalLineTo(134.4F)
            verticalLineTo(166.4F)
            horizontalLineTo(174.4F)
            verticalLineTo(46.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFF4385F5)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(154.4F, 186.4F)
            curveTo(165.45F, 186.4F, 174.4F, 177.45F, 174.4F, 166.4F)
            curveTo(174.4F, 155.35F, 165.45F, 146.4F, 154.4F, 146.4F)
            curveTo(143.35F, 146.4F, 134.4F, 155.35F, 134.4F, 166.4F)
            curveTo(134.4F, 177.45F, 143.35F, 186.4F, 154.4F, 186.4F)
            close()
        }.path(
            fill = SolidColor(Color(0xFFFF5722)),
            fillAlpha = 1.0F,
            strokeAlpha = 1.0F,
            strokeLineWidth = 0.0F,
            strokeLineCap = StrokeCap.Butt,
            strokeLineJoin = StrokeJoin.Miter,
            strokeLineMiter = 4.0F,
            pathFillType = PathFillType.NonZero,
        ) {
            moveTo(154.4F, 66.4F)
            curveTo(165.45F, 66.4F, 174.4F, 57.45F, 174.4F, 46.4F)
            curveTo(174.4F, 35.35F, 165.45F, 26.4F, 154.4F, 26.4F)
            curveTo(143.35F, 26.4F, 134.4F, 35.35F, 134.4F, 46.4F)
            curveTo(134.4F, 57.45F, 143.35F, 66.4F, 154.4F, 66.4F)
            close()
        }.build()

        return _maksonicLogo!!
    }
private var _maksonicLogo: ImageVector? = null

@Preview
@Composable
@Suppress("UnusedPrivateMember")
private fun IconMaksonicLogoPreview() {
    Image(imageVector = AppIcon.MaksonicLogo, contentDescription = null)
}