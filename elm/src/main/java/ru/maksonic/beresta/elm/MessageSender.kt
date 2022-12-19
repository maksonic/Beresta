package ru.maksonic.beresta.elm

/**
 * @Author maksonic on 15.12.2022
 */
interface MessageSender<M : ElmMessage> {
    fun sendMsg(msg: M)
}