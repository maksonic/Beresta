package ru.maksonic.beresta.screen.main.core.programs

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import ru.maksonic.beresta.elm.core.ElmProgram
import ru.maksonic.beresta.feature.folders_chips.api.domain.FoldersInteractor
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUi
import ru.maksonic.beresta.feature.folders_chips.api.ui.FolderUiMapper
import ru.maksonic.beresta.feature.folders_chips.api.ui.StickyItemsTitleFormatter
import ru.maksonic.beresta.language_engine.shell.LanguageEngineApi
import ru.maksonic.beresta.screen.main.core.Cmd
import ru.maksonic.beresta.screen.main.core.Msg

/**
 * @Author maksonic on 04.07.2023
 */
class ChipsDataProgram(
    private val interactor: FoldersInteractor,
    private val foldersMapper: FolderUiMapper,
    private val ioDispatcher: CoroutineDispatcher,
    private val appLanguageEngineApi: LanguageEngineApi,
    private val stickyItemsTitleFormatter: StickyItemsTitleFormatter,
) : ElmProgram<Msg, Cmd> {
    override suspend fun executeProgram(cmd: Cmd, consumer: (Msg) -> Unit) {
        when (cmd) {
            is Cmd.FetchChipsData -> fetchChipsData(consumer)
            else -> {}
        }
    }

    private suspend fun fetchChipsData(consumer: (Msg) -> Unit) = withContext(ioDispatcher) {
        runCatching {
            combine(interactor.fetchList(), appLanguageEngineApi.current) { data, lang ->
                val chips = foldersMapper.mapListTo(data)
                    .map { it.copy(title = stickyItemsTitleFormatter.format(it, lang)) }

                consumer(Msg.Inner.FetchedChipsData(FolderUi.Collection(chips)))
            }.collect()
        }.onFailure {
            consumer(Msg.Inner.FetchedChipsData(FolderUi.Collection(emptyList())))
        }
    }
}