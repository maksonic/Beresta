package ru.maksonic.beresta.feature.notes_list.ui.core

import kotlinx.coroutines.delay
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes_list.api.NoteUi

/**
 * @Author maksonic on 25.12.2022
 */
class NotesListProgram : ElmProgram<Feature.Msg, Feature.Cmd> {

    override suspend fun executeProgram(cmd: Feature.Cmd, consumer: (Feature.Msg) -> Unit) {
        when (cmd) {
            is Feature.Cmd.FetchData -> fetchNotes(consumer)
        }
    }

    private suspend fun fetchNotes(consumer: (Feature.Msg) -> Unit) {
        val fakeData = listOf(
            NoteUi(1, "1 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(2, "2 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(3, "3 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4, "4 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(5, "5 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(6, "6 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(7, "7 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(8, "8 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(9, "9 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(10, "10 note", "Some fake note", "сегодня - 00:00"),
        )
         delay(5000)
        consumer(Feature.Msg.Inner.FetchingSuccess(fakeData))
    }
}