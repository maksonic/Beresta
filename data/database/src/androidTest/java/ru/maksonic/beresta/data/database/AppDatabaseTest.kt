package ru.maksonic.beresta.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith


/**
 * @Author maksonic on 06.04.2023
 */
@RunWith(AndroidJUnit4::class)
internal class AppDatabaseTest : TestCase() {
    private lateinit var db: AppDatabase.AppDatabase
    private lateinit var dao: NoteFolderDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.AppDatabase::class.java).build()
        dao = db.noteFolderDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testCheckInsertAndGetFolderItem() = runBlocking {
        val folder = NoteFolderCache(101, "TestFolder")
        dao.insertItem(folder)
        val folderList = dao.fetchCacheList()

        folderList.collect { fetchedList ->
            assertThat(fetchedList.contains(folder))
        }
    }
}