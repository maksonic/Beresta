package ru.maksonic.beresta.language_engine.shell

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import ru.maksonic.beresta.language_engine.shell.languages.Chinese
import ru.maksonic.beresta.language_engine.shell.languages.ChineseTr
import ru.maksonic.beresta.language_engine.shell.languages.English
import ru.maksonic.beresta.language_engine.shell.languages.Russian

/**
 * @Author maksonic on 22.04.2023
 */
@Serializable
data class LanguageStore(
    @SerializedName("russian") val russian: Russian,
    @SerializedName("english") val english: English,
    @SerializedName("chinese") val chinese: Chinese,
    @SerializedName("chinese_tr") val chineseTr: ChineseTr,
)