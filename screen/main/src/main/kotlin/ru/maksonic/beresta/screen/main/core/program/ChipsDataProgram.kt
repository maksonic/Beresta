package ru.maksonic.beresta.screen.main.core.program

import ru.maksonic.beresta.feature.folders_list.domain.usecase.FetchFoldersUseCase
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUi
import ru.maksonic.beresta.feature.folders_list.ui.api.FolderUiMapper
import ru.maksonic.beresta.platform.elm.core.ElmProgram
import ru.maksonic.beresta.screen.main.core.Cmd
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 04.07.2023
 */
class ChipsDataProgram(
    private val fetchFoldersUseCase: FetchFoldersUseCase,
    private val mapper: FolderUiMapper,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchChipsList -> fetchChipsData(consumer)
            else -> {}
        }
    }

    private suspend fun fetchChipsData(consumer: (Msg) -> Unit) = runCatching {
        fetchFoldersUseCase().collect { data ->
            val chips = mapper.mapListTo(data)

            consumer(Msg.Inner.FetchedChipsSuccess(FolderUi.Collection(chips)))
        }
    }.onFailure {
        consumer(Msg.Inner.FetchedChipsFail(it.localizedMessage ?: "Failure"))
    }
}