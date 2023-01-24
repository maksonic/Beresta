package ru.maksonic.beresta.feature.trash_list.ui.core

import ru.maksonic.beresta.elm.ElmProgram

/**
 * @Author maksonic on 23.01.2023
 */
class TrashProgram: ElmProgram<Feature.Msg, Feature.Cmd> {

    override suspend fun executeProgram(cmd: Feature.Cmd, consumer: (Feature.Msg) -> Unit) {
        when (cmd) {
            is Feature.Cmd.FetchRemovedNotes -> {}
        }
    }
}