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
            NoteUi(10, "10 note", "Some fake note", "сегодня - 00:00"),NoteUi(1, "1 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(2, "2 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(3, "3 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4, "4 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(5, "5 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(6, "6 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(7, "7 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(8, "8 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(9, "9 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(10, "10 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(11, "11 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(12, "12 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(13, "13 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(14, "14 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(15, "15 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(31, "1 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(32, "2 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(33, "3 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(34, "4 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(35, "5 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(36, "6 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(37, "7 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(38, "8 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(39, "9 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(310, "10 note", "Some fake note", "сегодня - 00:00"),NoteUi(1, "1 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(32, "2 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(33, "3 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(34, "4 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(35, "5 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(36, "6 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(37, "7 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(38, "8 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(39, "9 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(310, "10 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(311, "11 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(312, "12 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(313, "13 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(314, "14 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(315, "15 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(431, "1 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(432, "2 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(433, "3 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(434, "4 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(435, "5 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(436, "6 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(437, "7 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(438, "8 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(439, "9 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4310, "10 note", "Some fake note", "сегодня - 00:00"),NoteUi(1, "1 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(432, "2 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(433, "3 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(434, "4 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(435, "5 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(436, "6 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(437, "7 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(438, "8 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(439, "9 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4310, "10 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4311, "11 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4312, "12 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4313, "13 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4314, "14 note", "Some fake note", "сегодня - 00:00"),
            NoteUi(4315, "15 note", "Some fake note", "сегодня - 00:00"),
        )
         delay(1000)
        consumer(Feature.Msg.Inner.FetchingSuccess(fakeData))
    }
}