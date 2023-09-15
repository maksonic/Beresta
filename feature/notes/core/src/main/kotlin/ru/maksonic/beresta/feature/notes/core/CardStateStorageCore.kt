package ru.maksonic.beresta.feature.notes.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.data.common.Datastore
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardElevation
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardShape
import ru.maksonic.beresta.feature.notes.api.ui.NoteCardUiState
import ru.maksonic.beresta.feature.notes.api.NotesApi

/**
 * @Author maksonic on 08.07.2023
 */
class CardStateStorageCore(private val datastore: Datastore) : NotesApi.CardStateStorage {

    private companion object {
        private const val BASE_KEY = "prefs_note_card_ui_"
        private const val SHAPE_KEY = BASE_KEY + "shape_key"
        private const val ELEVATION_KEY = BASE_KEY + "elevation_key"
        private const val TITLE_LINES_KEY = BASE_KEY + "max_title_lines_key"
        private const val MESSAGE_LINES_KEY = BASE_KEY + "max_message_lines_key"
        private const val COLOR_MARKER_KEY = BASE_KEY + "color_marker_key"
        private const val INITIAL_TITLE_LINES_COUNT = 1
        private const val INITIAL_MESSAGE_LINES_COUNT = 2
    }

    private val shapeKey = stringPreferencesKey(SHAPE_KEY)
    private val elevationKey = stringPreferencesKey(ELEVATION_KEY)
    private val maxTitleLinesKey = intPreferencesKey(TITLE_LINES_KEY)
    private val maxMessageLinesKey = intPreferencesKey(MESSAGE_LINES_KEY)
    private val colorMarkerKey = booleanPreferencesKey(COLOR_MARKER_KEY)

    private val initialShape = NoteCardShape.ROUNDED.name
    private val initialElevation = NoteCardElevation.DISABLED.name

    override val current: Flow<NoteCardUiState> = datastore.datastore.data.map { card ->
        val shape = NoteCardShape.valueOf(card[shapeKey] ?: initialShape)
        val elevation = NoteCardElevation.valueOf(card[elevationKey] ?: initialElevation)
        val maxTitleLines = card[maxTitleLinesKey] ?: INITIAL_TITLE_LINES_COUNT
        val maxMessageLines = card[maxMessageLinesKey] ?: INITIAL_MESSAGE_LINES_COUNT
        val isVisibleColorMarker = card[colorMarkerKey] ?: true

        return@map NoteCardUiState(
            shape = shape,
            elevation = elevation,
            maxTitleLines = maxTitleLines,
            maxMessageLines = maxMessageLines,
            isVisibleColorMarker = isVisibleColorMarker
        )
    }

    override suspend fun setCardShape(shape: NoteCardShape) {
        datastore.datastore.edit { prefs -> prefs[shapeKey] = shape.name }
    }

    override suspend fun setCardElevation(elevation: NoteCardElevation) {
        datastore.datastore.edit { prefs -> prefs[elevationKey] = elevation.name }
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