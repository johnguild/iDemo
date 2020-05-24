package com.test.itunesdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TrackDao {

    /**
     * Adds new row on DatabaseTrack table
     *
     * @see com.test.itunesdemo.db.DatabaseTrack
     * @param DatabaseTrack instance
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: DatabaseTrack)


    /**
     * Adds multiple rows on DatabaseTrack table
     *
     * @see com.test.itunesdemo.db.DatabaseTrack
     * @param List of DatabaseTrack
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(trackList: List<DatabaseTrack>)


    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @see com.test.itunesdemo.db.DatabaseTrack
     * @param DatabaseTrack new value to write
     */
    @Update
    fun update(tack: DatabaseTrack)

    /**
     * Selects and returns the row that matches the id, which is our key.
     *
     * @see com.test.itunesdemo.db.DatabaseTrack
     * @param key id of DatabaseTrack
     */
    @Query("SELECT * from DatabaseTrack WHERE id = :key")
    fun get(key: Long): DatabaseTrack?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM DatabaseTrack")
    fun clear()

    /**
     * Retrieves the total count from the table
     */
    @Query("SELECT COUNT(trackId) FROM DatabaseTrack")
    fun getCount(): Int


    /**
     * Updates userViewing params that matchs the given id
     *
     * @param id of DatabaseTrack
     * @param bool new value of userViewing
     */
    @Query("UPDATE DatabaseTrack SET userViewing = :bool WHERE id = :id")
    fun updateUserViewing(id: Long, bool: Boolean)


    /**
     * Get a DatabaseTrack instance that matches the parameters given.
     * iTunes api doesnt provide unique id on results and we auto generate in on insert,
     * the selected params where assumed to be the columns which are unlikely to
     * changed in this way we can make condition to determine if the DatabaseTrack
     * already exists on table or not.
     *
     * @param wrapperType
     * @param kind
     * @param artistId
     * @param collectionId
     * @param trackId
     * @param artistName
     * @param collectionName
     * @param trackName
     *
     * @return DatabaseTrack instance
     */
    @Query("SELECT * FROM DatabaseTrack WHERE wrapperType LIKE '%' || :wrapperType || '%' " +
            "AND kind LIKE '%' || :kind || '%' " +
            "AND artistId = :artistId " +
            "AND collectionId = :collectionId " +
            "AND trackId = :trackId " +
            "AND artistName LIKE '%' || :artistName || '%' " +
            "AND collectionName LIKE '%' || :collectionName || '%' " +
            "AND trackName LIKE '%' || :trackName || '%' " +
            " LIMIT 1")
    fun getTrack(wrapperType: String,
                 kind: String,
                 artistId: Long,
                 collectionId: Long,
                 trackId: Long,
                 artistName: String,
                 collectionName: String,
                 trackName: String): DatabaseTrack?

    /**
     * Retrieves all instance inside the table
     *
     * @return List of DatabaseTrack
     */
    @Query("SELECT * FROM DatabaseTrack")
    fun all(): List<DatabaseTrack>


    /**
     * Same with get(key), this retrieves an instance base on id,
     * but wrapped on an observable LiveData
     *
     * @return LiveData of DatabaseTrack
     */
    @Query("SELECT * FROM DatabaseTrack WHERE id = :id  LIMIT 1")
    fun getTrackLive(id: Long?): LiveData<DatabaseTrack?>

    /**
     * Retrieves a DatabaseTrack which userViewing value is true
     *
     * @return LiveData of DatabaseTrack
     */
    @Query("SELECT * FROM DatabaseTrack WHERE userViewing = 1  LIMIT 1")
    fun getViewing(): LiveData<DatabaseTrack?>

    /**
     * Same with all(), but wrap the list on observable LiveData
     *
     * @return LiveData of DatabaseTrack Listing
     */
    @Query("SELECT * FROM DatabaseTrack")
    fun getAllLive(): LiveData<List<DatabaseTrack>>

}