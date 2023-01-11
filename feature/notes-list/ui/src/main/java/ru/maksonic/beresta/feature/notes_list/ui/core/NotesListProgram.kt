package ru.maksonic.beresta.feature.notes_list.ui.core

import android.text.format.Time
import kotlinx.coroutines.delay
import ru.maksonic.beresta.elm.ElmProgram
import ru.maksonic.beresta.feature.notes_list.api.NoteUi
import java.util.GregorianCalendar
import kotlin.random.Random

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
        val date = GregorianCalendar.getInstance().time
        val fakeData = buildList {
            repeat(100) { add(NoteUi(
                id = Random.nextLong(),
                title = "Title note $it",
                message = "Message note $it of random generation",
                dateCreation = "$date"
            ))}
        }
        delay(1000)
        consumer(Feature.Msg.Inner.FetchingSuccess(fakeData))
    }
}