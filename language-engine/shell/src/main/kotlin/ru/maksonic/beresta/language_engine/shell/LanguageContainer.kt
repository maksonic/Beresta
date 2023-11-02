package ru.maksonic.beresta.language_engine.shell

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.language_engine.shell.languages.Chinese
import ru.maksonic.beresta.language_engine.shell.languages.ChineseTr
import ru.maksonic.beresta.language_engine.shell.languages.English
import ru.maksonic.beresta.language_engine.shell.languages.Russian

/**
 * @Author maksonic on 27.09.2023
 */
@Serializable
data class LanguageContainer(
    @SerialName("russian") val russian: Russian,
    @SerialName("english") val english: English,
    @SerialName("chinese") val chinese: Chinese,
    @SerialName("chinese_tr") val chineseTr: ChineseTr,
)