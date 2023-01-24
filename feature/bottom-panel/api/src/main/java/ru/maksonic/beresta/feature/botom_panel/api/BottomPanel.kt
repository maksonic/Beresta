package ru.maksonic.beresta.feature.botom_panel.api

/**
 * @Author maksonic on 19.01.2023
 */
sealed class BottomPanel {
    enum class State {
        IDLE, SELECTED
    }

    sealed class Action {
        sealed class Notes : Action() {
            enum class Idle {
                TRASH, FOLDERS, SEARCH, ADD_NOTE, FAVORITES, SORT_BY, SWITCH_VIEW_STATE
            }

            enum class Select {
                NOTHING, CANCEL, SELECT_ALL, REMOVE, REPLACE, PIN, HIDE
            }
        }

    }
}