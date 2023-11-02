package ru.maksonic.beresta.database.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ru.maksonic.beresta.database.AppDatabase

/**
 * @Author maksonic on 19.12.2022
 */
val databaseModule = module {
    single {
        val databaseName = "beresta-db"
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase.AppDatabase::class.java,
            databaseName
        ).createFromAsset(databaseName).build()
    }
    single { get<AppDatabase.AppDatabase>().noteDao() }
    single { get<AppDatabase.AppDatabase>().noteFolderDao() }
}