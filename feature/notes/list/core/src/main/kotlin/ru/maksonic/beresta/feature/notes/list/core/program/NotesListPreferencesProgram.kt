package ru.maksonic.beresta.feature.notes.list.core.program

import kotlinx.coroutines.flow.collectLatest
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes.list.api.ui.NotesListApi
import ru.maksonic.beresta.feature.notes.list.api.ui.SortedNotes
import ru.maksonic.beresta.feature.notes.list.core.Cmd
import ru.maksonic.beresta.feature.notes.list.core.Msg
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi

/**
 * @Author maksonic on 06.06.2023
 */
class NotesListPreferencesProgram(
    private val appLanguageEngineApi: LanguageEngineApi,
    private val sortNotesDatastore: NotesListApi.SortedNotesState
) : ElmProgram<Msg, Cmd> {

    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchCurrentLangForNotesDatestamp -> readLanguageFromDatastore(consumer)
            is Cmd.FetchNotesDatastorePrefs -> fetchNotesPrefsFromDatastore(consumer)
            is Cmd.UpdateNotesSortValueToDatastore -> setSortNotesValue(cmd.sort)
            is Cmd.UpdateSortCheckboxValueInDatastore -> setSortPinnedNotesValue(cmd.isSortPinned)
            is Cmd.UpdateGridCountInDatastore -> setNotesGridCount(cmd.count)
            else -> {}
        }
    }

    private suspend fun readLanguageFromDatastore(consumer: (Msg) -> Unit) {
        appLanguageEngineApi.current.collectLatest { savedAppLanguage ->
            consumer(Msg.Inner.FetchedCurrentAppLang(savedAppLanguage))
        }
    }

    private suspend fun fetchNotesPrefsFromDatastore(consumer: (Msg) -> Unit) {
        sortNotesDatastore.current.collectLatest { prefs ->
            consumer(
                Msg.Inner.FetchedNotesPrefs(
                    sort = prefs.first, isSortPinned = prefs.second, gridCount = prefs.third
                )
            )
        }
    }

    private suspend fun setSortNotesValue(value: SortedNotes) = sortNotesDatastore.setSortBy(value)

    private suspend fun setSortPinnedNotesValue(value: Boolean) =
        sortNotesDatastore.setSortPinned(value)

    private suspend fun setNotesGridCount(value: Int) = sortNotesDatastore.setGridView(value)
}