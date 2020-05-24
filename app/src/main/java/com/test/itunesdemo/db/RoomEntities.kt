package com.test.itunesdemo.db

import androidx.room.*

/**
 * Database entities go in this file. These are responsible for reading and writing from the
 * database.
 */


/**
 * Track Object, a database version of an object
 * from itunes api results
 */
@Entity
data class DatabaseTrack constructor(
    @PrimaryKey val id: Long?,
    val wrapperType: String,
    val kind: String,
    val collectionId: Long,
    val trackId: Long,
    val artistId: Long,
    val artistName: String,
    val amgArtistId: Long,
    val collectionName: String,
    val trackName: String,
    val collectionCensoredName: String,
    val trackCensoredName: String,
    val collectionArtistId: Long,
    val collectionArtistViewUrl: String,
    val collectionViewUrl: String,
    val trackViewUrl: String,
    val previewUrl: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val collectionPrice: Double,
    val trackPrice: Double,
    val trackRentalPrice: Double,
    val collectionHdPrice: Double,
    val trackHdPrice: Double,
    val trackHdRentalPrice: Double,
    val releaseDate: String,
    val collectionExplicitness: String,
    val trackExplicitness: String,
    val discCount: Long,
    val discNumber: Long,
    val trackCount: Long,
    val trackNumber: Long,
    val trackTimeMillis: Long,
    val country: String,
    val currency: String,
    val primaryGenreName: String,
    val contentAdvisoryRating: String,
    val isStreamable: Boolean?,
    val description: String,
    val longDescription: String,
    val hasITunesExtras: Boolean?,
    val feedUrl: String,
    val genreIds: String,// array as string
    val genres: String,// array as string
    val userViewing: Boolean

)
