package ru.maksonic.beresta.elm

/**
 * @Author maksonic on 14.11.2022
 */
interface MessageSender<M : ElmMessage> {
    fun sendMsg(msg: M)
}