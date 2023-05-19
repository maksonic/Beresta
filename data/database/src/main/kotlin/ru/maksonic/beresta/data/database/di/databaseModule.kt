package ru.maksonic.beresta.data.database.di

import androidx.room.Room
import org.koin.dsl.module
import ru.maksonic.beresta.data.database.AppDatabase

/**
 * @Author maksonic on 19.12.2022
 */
val databaseModule = module {
    single {
        val databaseName = "beresta-db"
        Room.databaseBuilder(get(), AppDatabase.AppDatabase::class.java, databaseName).build()
    }
    single { get<AppDatabase.AppDatabase>().noteDao() }
    single { get<AppDatabase.AppDatabase>().noteFolderDao() }
}