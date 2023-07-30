package ru.maksonic.beresta.feature.notes.core

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
class NoteCardStateFeatureCore(private val datastore: Datastore) : NotesApi.Feature.NoteCardState {

    private companion object {
        private const val SHAPE = "prefs_note_card_ui_shape_key"
        private const val ELEVATION = "prefs_note_card_ui_elevation_key"
        private const val TITLE_LINES = "prefs_note_card_ui_max_title_lines_key"
        private const val MESSAGE_LINES = "prefs_note_card_ui_max_message_lines_key"
        private const val INITIAL_TITLE_LINES_COUNT = 1
        private const val INITIAL_MESSAGE_LINES_COUNT = 2
    }

    private val shapeKey = stringPreferencesKey(SHAPE)
    private val elevationKey = stringPreferencesKey(ELEVATION)
    private val maxTitleLinesKey = intPreferencesKey(TITLE_LINES)
    private val maxMessageLinesKey = intPreferencesKey(MESSAGE_LINES)

    private val initialShape = NoteCardShape.ROUNDED.name
    private val initialElevation = NoteCardElevation.DISABLED.name

    override val current: Flow<NoteCardUiState> = datastore.datastore.data.map { card ->
        val shape = NoteCardShape.valueOf(card[shapeKey] ?: initialShape)
        val elevation = NoteCardElevation.valueOf(card[elevationKey] ?: initialElevation)
        val maxTitleLines = card[maxTitleLinesKey] ?: INITIAL_TITLE_LINES_COUNT
        val maxMessageLines = card[maxMessageLinesKey] ?: INITIAL_MESSAGE_LINES_COUNT

        return@map NoteCardUiState(
            shape = shape,
            elevation = elevation,
            maxTitleLines = maxTitleLines,
            maxMessageLines = maxMessageLines
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

    override suspend fun setByDefaultCardMaxLines() {
        datastore.datastore.edit { prefs ->
            prefs[maxTitleLinesKey] = INITIAL_TITLE_LINES_COUNT
            prefs[maxMessageLinesKey] = INITIAL_MESSAGE_LINES_COUNT
        }
    }
}