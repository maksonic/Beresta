package ru.maksonic.beresta.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.maksonic.beresta.data.common.LocalDateTimeConverter
import ru.maksonic.beresta.data.database.folders.FolderCache
import ru.maksonic.beresta.data.database.folders.NoteFolderDao
import ru.maksonic.beresta.data.database.notes.NoteCache
import ru.maksonic.beresta.data.database.notes.NoteDao

/**
 * @Author maksonic on 19.12.2022
 */

object AppDatabase {
    private const val DB_VERSION = 1

    @Database(
        entities = [NoteCache::class, FolderCache::class],
        version = DB_VERSION, exportSchema = false
    )
    @TypeConverters(LocalDateTimeConverter::class)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun noteDao(): NoteDao
        abstract fun noteFolderDao(): NoteFolderDao
    }
}