package com.test.itunesdemo.network

import com.google.gson.Gson
import com.test.itunesdemo.db.DatabaseTrack

/**
 * Network Models conversion to Database Models are
 * in this file.
 */


/**
 * Transform NetworkTrack to DatabaseTrack
 *
 * @see com.test.itunesdemo.network.NetworkTrackResponse
 * @see com.test.itunesdemo.db.DatabaseTrack
 *
 * @param id when the track instance has a record on db.
 *
 * @return DatabaseTrack instance
 */
fun NetworkTrackResponse.toDatabaseTrack( id: Long? = null ): DatabaseTrack {
    return DatabaseTrack(
        id,
        wrapperType,
        kind ?: "",
        collectionId ?: 0,
        trackId ?: 0,
        artistId ?: 0,
        artistName,
        amgArtistId ?: 0,
        collectionName ?: "",
        trackName ?: "",
        collectionCensoredName ?: "",
        trackCensoredName ?: "",
        collectionArtistId ?: 0,
        collectionArtistViewUrl ?: "",
        collectionViewUrl ?: "",
        trackViewUrl ?: "",
        previewUrl ?: "",
        artworkUrl30 ?: "",
        artworkUrl60 ?: "",
        artworkUrl100 ?: "",
        collectionPrice ?: 0.00,
        trackPrice ?: 0.00,
        trackRentalPrice ?: 0.00,
        collectionHdPrice ?: 0.00,
        trackHdPrice ?: 0.00,
        trackHdRentalPrice ?: 0.00,
        releaseDate ?: "",
        collectionExplicitness ?: "",
        trackExplicitness ?: "",
        discCount ?: 0,
        discNumber ?: 0,
        trackCount ?: 0,
        trackNumber ?: 0,
        trackTimeMillis ?: 0,
        country,
        currency,
        primaryGenreName ?: "",
        contentAdvisoryRating ?: "",
        isStreamable,
        description ?: "",
        longDescription ?: "",
        hasITunesExtras,
        feedUrl ?: "",
        if(genreIds != null) Gson().toJson(genreIds).toString() else "[]",
        if(genres != null) Gson().toJson(genres).toString() else "[]",
        false
    )
}


