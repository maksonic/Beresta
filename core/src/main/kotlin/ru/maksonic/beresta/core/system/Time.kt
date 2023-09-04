package ru.maksonic.beresta.core.system

import java.time.LocalDateTime
import java.time.ZoneId

/**
 * @Author maksonic on 31.08.2023
 */

fun LocalDateTime.pinCoolDownTime(delay: Long) =
    this.plusSeconds(delay).atZone(ZoneId.systemDefault()).toEpochSecond()

val LocalDateTime.defaultEpochSecond get() = this.atZone(ZoneId.systemDefault()).toEpochSecond()
