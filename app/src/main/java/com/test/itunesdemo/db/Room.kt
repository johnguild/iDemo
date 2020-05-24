package com.test.itunesdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [
        DatabaseTrack::class
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
    abstract val trackDao: TrackDao
}

private lateinit var INSTANCE: MyDatabase

/**
 * Create if does not exists and return
 * the instance of MyDatabase
 *
 * @return instance of local database
 */
fun getDatabase(context: Context): MyDatabase {
    synchronized(MyDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MyDatabase::class.java,
                "mydatabase"
            )
                .fallbackToDestructiveMigration()
                .build()

        }
        return INSTANCE
    }
}
