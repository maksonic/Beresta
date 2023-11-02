package ru.maksonic.beresta.feature.wallpaper_picker.domain

/**
 * @Author maksonic on 29.10.2023
 */
class WallpaperType {

    companion object {
        fun idToType(id: Int) = when (id) {
            Value.COLOR.id -> Value.COLOR
            Value.GRADIENT.id -> Value.GRADIENT
            Value.TEXTURE.id -> Value.TEXTURE
            Value.IMAGE.id -> Value.IMAGE
            else -> Value.NONE
        }
    }

    enum class Value(val id: Int) {
        NONE(0), COLOR(1), GRADIENT(2), TEXTURE(3), IMAGE(4)
    }
}
