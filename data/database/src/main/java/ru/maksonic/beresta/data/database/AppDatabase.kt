package ru.maksonic.beresta.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.maksonic.beresta.data.common.DateOfItemCreationConverter
/**
 * @Author maksonic on 19.12.2022
 */

object AppDatabase {
    private const val DB_VERSION = 1

    @Database(
        entities = [NoteCache::class, NoteFolderCache::class],
        version = DB_VERSION, exportSchema = false
    )
    @TypeConverters(DateOfItemCreationConverter::class)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun noteDao(): NoteDao
        abstract fun noteFolderDao(): NoteFolderDao
    }
}
