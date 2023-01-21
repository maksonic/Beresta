package ru.maksonic.beresta.feature.botom_panel.api

/**
 * @Author maksonic on 19.01.2023
 */
sealed class BottomPanel {
    enum class State {
        IDLE, SELECTED
    }
    enum class Action {
        NOTHING, CANCEL, SELECT_ALL, REMOVE, REPLACE, PIN, HIDE
    }
}