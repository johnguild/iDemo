package com.test.itunesdemo.repositories

import androidx.lifecycle.Transformations
import com.test.itunesdemo.db.MyDatabase
import com.test.itunesdemo.db.toTrackModel
import com.test.itunesdemo.db.toTrackShortModel
import com.test.itunesdemo.network.MyNetwork
import com.test.itunesdemo.network.toDatabaseTrack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository for fetching track data.
 *
 * This class will determine where to fetch data from.
 * From itunes api if network connection is available and
 * use cached data when failing to retrieve from server.
 *
 * This file should contain only functions or variable
 * for retrieving, adding, updating and data deletion.
 */
class SearchRepository(private val database: MyDatabase, val id: Long? = 0) {


    /**
     * Retrieves track list data from itunes api
     * and cache it to local database if successful.
     * Already existing records will be updated with new value
     * from server.
     */
    suspend fun search(term: String, country: String, media: String) {

        val params = HashMap<String, Any>()
        params["term"] = term
        params["country"] = country
        params["media"] = media
//        params["limit"] = limit ?: 20

        withContext(Dispatchers.IO) {

            val response = MyNetwork.itunes.search(params).await()

            withContext(Dispatchers.Default) {

                database.trackDao.clear()

                // since itunes api objects does not provide unique id
                // manually check our cached objects by matching the
                // columns that are unlikely to be changed.
                // wrapperType, kind, artistId, collectionId, trackId, artistName, collectionName, trackName
                // NOTE: this approach is just a work around to determine the uniqueness of objects.
                response.results.forEach { networkTrack ->

                    var cached = database.trackDao.getTrack(
                        networkTrack.wrapperType,
                        networkTrack.kind ?: "",
                        networkTrack.artistId ?: 0,
                        networkTrack.collectionId ?: 0,
                        networkTrack.trackId ?: 0,
                        networkTrack.artistName,
                        networkTrack.collectionName ?: "",
                        networkTrack.trackName ?: ""
                    )

                    if( cached == null ) {
                        database.trackDao.insert( networkTrack.toDatabaseTrack() )
                    } else {
                        database.trackDao.insert( networkTrack.toDatabaseTrack( cached.id ) )
                    }
                }

            }

        }
    }

    /**
     * Updates userViewing column on DatabaseTrack
     */
    suspend fun setTrackViewing(id: Long, bool: Boolean) {

        withContext(Dispatchers.Default) {

            database.trackDao.updateUserViewing(id, bool)
        }
    }


    /**
     * Checks database for all records on DatabaseTrack
     *
     * @see com.test.itunesdemo.models.TrackShort
     * @return LiveData list of TrackShort
     */
    val tracks = Transformations.map(
            database.trackDao.getAllLive()
        ) {
            it.map { track ->
                track.toTrackShortModel()
            }
        }


    /**
     * Checks database for DatabaseTrack base on id
     *
     * @see com.test.itunesdemo.models.Track
     * @return LiveData of Track
     */
    val track = Transformations.map(
            database.trackDao.getTrackLive( id )
        ){
            it?.toTrackModel()
        }

    /**
     * Checks database for recent user viewed DatabaseTrack
     *
     * @see com.test.itunesdemo.models.TrackShort
     * @return LiveData of TrackShort
     */
    val viewingTrack = Transformations.map(
            database.trackDao.getViewing()
        ) {
            it?.toTrackShortModel()
        }

}
