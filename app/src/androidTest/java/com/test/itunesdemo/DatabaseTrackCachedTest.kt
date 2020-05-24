package com.test.itunesdemo

import android.util.Log
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.test.itunesdemo.db.MyDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTrackCachedTest {

    private lateinit var myDatabase: MyDatabase

    @Before
    fun createDb() {
        myDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            MyDatabase::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun clearAndCloseDb() {
        myDatabase.close()
    }


    @Test
    fun checkIfTrackIsCached() {

        var first = myDatabase.trackDao.get(1)

        var all = myDatabase.trackDao.all()

        var instance = myDatabase.trackDao.getTrack(
            "track",
            "feature-movie",
            0,
            1476858599,
            1437031362,
            "Bradley Cooper",
            "A Star is Born 3 Film Collection",
            "A Star Is Born (2018"
        )

        Log.i("TEST", "TEST")
    }
}