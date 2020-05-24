package com.test.itunesdemo.db

import com.test.itunesdemo.models.Track
import com.test.itunesdemo.models.TrackShort

/**
 * Database Models conversion to App Model functions
 * are in this file.
 */

/**
 * Converts DatabaseTrack to TrackShort model
 *
 * @see com.test.itunesdemo.models.TrackShort
 * @return TrackShort instance
 */
fun DatabaseTrack.toTrackShortModel(): TrackShort {
    return TrackShort(
        id!!,
        wrapperType,
        kind,
        trackName,
        artistName,
        artworkUrl30,
        artworkUrl60,
        artworkUrl100,
        trackPrice,
        trackRentalPrice,
        collectionHdPrice,
        trackHdPrice,
        trackHdRentalPrice,
        primaryGenreName,
        currency
    )
}


/**
 * Converts DatabaseTrack to Track model
 *
 * @see com.test.itunesdemo.models.Track
 * @return Track instance
 */
fun DatabaseTrack.toTrackModel(): Track {
    return Track(
        id!!,
        wrapperType,
        kind,
        collectionId,
        trackId,
        artistId,
        artistName,
        amgArtistId,
        collectionName,
        trackName,
        collectionCensoredName,
        trackCensoredName,
        collectionArtistId,
        collectionArtistViewUrl,
        collectionViewUrl,
        trackViewUrl,
        previewUrl,
        artworkUrl30,
        artworkUrl60,
        artworkUrl100,
        collectionPrice,
        trackPrice,
        trackRentalPrice,
        collectionHdPrice,
        trackHdPrice,
        trackHdRentalPrice,
        releaseDate,
        collectionExplicitness,
        trackExplicitness,
        discCount,
        discNumber,
        trackCount,
        trackNumber,
        trackTimeMillis,
        country,
        currency,
        primaryGenreName,
        contentAdvisoryRating,
        isStreamable,
        description,
        longDescription,
        hasITunesExtras,
        feedUrl,
        genreIds,// array as string
        genres,// array as string
        userViewing
    )
}