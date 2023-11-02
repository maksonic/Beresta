package ru.maksonic.beresta.feature.hidden_notes_dialog.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.map
import ru.maksonic.beresta.common.core.security.CryptoEngine
import ru.maksonic.beresta.common.data.Datastore
import ru.maksonic.beresta.feature.hidden_notes_dialog.domain.HiddenNotesPinCreator
import java.io.File

/**
 * @Author maksonic on 24.10.2023
 */
class HiddenNotesPinCreatorCore(
    private val context: Context,
    private val cryptoEngine: CryptoEngine,
    private val datastore: Datastore,
    private val ioDispatcher: CoroutineDispatcher
) : HiddenNotesPinCreator {
    private companion object {
        private const val FILE_NAME = "notes_error_logs.txt"
        private const val KEY_NAME = "sd[w[2k&2mc;21%$,clq22el"
    }

    private val pinKey = booleanPreferencesKey(KEY_NAME)

    override val state = datastore.datastore.data.map { it[pinKey] ?: false }

    override suspend fun createPin(byteArray: ByteArray): Unit = with(ioDispatcher) {
        val file = File(context.filesDir, FILE_NAME)

        if (!file.exists()) {
            file.createNewFile()
        }

        val encryptedPin = cryptoEngine.encrypt(byteArray)
        context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use {
            it.write(encryptedPin)
        }

        datastore.datastore.edit { it[pinKey] = true }
    }

    override fun verifyPin(data: ByteArray): Result<Boolean> {
        val encryptedPin = context.openFileInput(FILE_NAME).readBytes()
        val decrypted = cryptoEngine.decrypt(encryptedPin)

        return decrypted.fold(
            onSuccess = { Result.success(data.contentEquals(it)) },
            onFailure = { Result.success(false) }
        )
    }

    override suspend fun deletePin() {
        val file = File(context.filesDir, FILE_NAME)
        if (file.exists()) {
            context.deleteFile(file.name)
        }
        datastore.datastore.edit { it[pinKey] = false }
    }
}