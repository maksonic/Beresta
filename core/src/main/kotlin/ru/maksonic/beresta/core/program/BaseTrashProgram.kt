/*
package ru.maksonic.beresta.core.program

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.core.Mapper
import java.time.LocalDateTime

*/
/**
 * @Author maksonic on 28.07.2023
 *//*

class BaseTrashProgram<D, T, Msg>(
    private val interactor:
    private val mapper: Mapper<D, T>,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun moveSelectedItemsToTrash(items: List<T>, consumer: (Msg) -> Unit) =
        withContext(ioDispatcher) {
            val notesDomain = mapper.mapListFrom(items)
            notesInteractor.updateAll(notesDomain)
            delay(SNACK_BAR_VISIBILITY_TIME)
            consumer(Msg.Inner.HiddenRemovedNotesSnackBar)
        }

    private suspend fun undoRemovedFromTrash(
        notes: List<NoteUi>,
        consumer: (Msg) -> Unit
    ) {
        val restored = mapper.mapListFrom(notes.map { it.copy(dateMovedToTrashRaw = null) })
        notesInteractor.updateAll(restored)
        consumer(Msg.Inner.HiddenRemovedNotesSnackBar)
    }

}*/
