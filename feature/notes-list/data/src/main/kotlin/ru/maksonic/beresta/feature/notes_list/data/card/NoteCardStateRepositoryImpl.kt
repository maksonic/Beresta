package ru.maksonic.beresta.feature.notes_list.data.card

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardDomainState
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardElevationDomain
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardShapeDomain
import ru.maksonic.beresta.feature.notes_list.domain.card.NoteCardStateRepository

/**
 * @Author maksonic on 13.10.2023
 */
class NoteCardStateRepositoryImpl(private val datastore: Datastore) : NoteCardStateRepository {

    private companion object {
        private const val BASE_KEY = "prefs_note_card_ui_"
        private const val SHAPE_KEY = BASE_KEY + "shape_key"
        private const val ELEVATION_KEY = BASE_KEY + "elevation_key"
        private const val TITLE_LINES_KEY = BASE_KEY + "max_title_lines_key"
        private const val MESSAGE_LINES_KEY = BASE_KEY + "max_message_lines_key"
        private const val COLOR_MARKER_KEY = BASE_KEY + "color_marker_key"
        private const val WALLPAPER_KEY = BASE_KEY + "wallpaper_key"
        private const val INITIAL_TITLE_LINES_COUNT = 1
        private const val INITIAL_MESSAGE_LINES_COUNT = 2
    }

    private val shapeKey = stringPreferencesKey(SHAPE_KEY)
    private val elevationKey = stringPreferencesKey(ELEVATION_KEY)
    private val maxTitleLinesKey = intPreferencesKey(TITLE_LINES_KEY)
    private val maxMessageLinesKey = intPreferencesKey(MESSAGE_LINES_KEY)
    private val colorMarkerKey = booleanPreferencesKey(COLOR_MARKER_KEY)
    private val wallpaperKey = booleanPreferencesKey(WALLPAPER_KEY)

    private val initialShape = NoteCardShapeDomain.ROUNDED.name
    private val initialElevation = NoteCardElevationDomain.DISABLED.name

    override fun fetchNoteCardState(): Flow<NoteCardDomainState> =
        datastore.datastore.data.map { card ->
            NoteCardDomainState(
                shape = NoteCardShapeDomain.valueOf(card[shapeKey] ?: initialShape),
                elevation = NoteCardElevationDomain.valueOf(card[elevationKey] ?: initialElevation),
                maxTitleLines = card[maxTitleLinesKey] ?: INITIAL_TITLE_LINES_COUNT,
                maxMessageLines = card[maxMessageLinesKey] ?: INITIAL_MESSAGE_LINES_COUNT,
                isVisibleColorMarker = card[colorMarkerKey] ?: true,
                isVisibleWallpaper = card[wallpaperKey] ?: true
            )
        }

    override suspend fun setCardShape(shape: NoteCardShapeDomain) {
        datastore.datastore.edit { prefs -> prefs[shapeKey] = shape.name }
    }

    override suspend fun setCardElevation(elevation: NoteCardElevationDomain) {
        datastore.datastore.edit { prefs -> prefs[elevationKey] = elevation.name }
    }

    override suspend fun setCardWallpaperVisibility(isVisible: Boolean) {
        datastore.datastore.edit { prefs -> prefs[wallpaperKey] = isVisible }
    }

    override suspend fun setCardTitleMaxLines(value: Int) {
        datastore.datastore.edit { prefs -> prefs[maxTitleLinesKey] = value }
    }

    override suspend fun setCardMessageMaxLines(value: Int) {
        datastore.datastore.edit { prefs -> prefs[maxMessageLinesKey] = value }
    }

    override suspend fun setCardColorMarkerVisibility(isVisible: Boolean) {
        datastore.datastore.edit { prefs -> prefs[colorMarkerKey] = isVisible }
    }

    override suspend fun setByDefaultCardMaxLines() {
        datastore.datastore.edit { prefs ->
            prefs[maxTitleLinesKey] = INITIAL_TITLE_LINES_COUNT
            prefs[maxMessageLinesKey] = INITIAL_MESSAGE_LINES_COUNT
        }
    }
}