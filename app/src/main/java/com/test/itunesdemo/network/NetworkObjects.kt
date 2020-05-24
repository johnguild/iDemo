package com.test.itunesdemo.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Mockup of all network objects are in this file.
 *
 * Match the objects attributes and will be automatically
 * converted to this data classes.
 */


/**
 * Search response wrapper
 *
 * @see com.test.itunesdemo.network.iTunesServices.search
 */
@JsonClass(generateAdapter = true)
data class NetworkSearchResponse(
    val resultCount: Int,
    val results: List<NetworkTrackResponse>
)

/**
 * Track instance model,
 * contains all attributes on all media type.
 */
@JsonClass(generateAdapter = true)
data class NetworkTrackResponse(
    val wrapperType: String,
    val kind: String?,
    val collectionId: Long?,
    val trackId: Long?,
    val artistId: Long?,
    val artistName: String,
    val amgArtistId: Long?,
    val collectionName: String?,
    val trackName: String?,
    val collectionCensoredName: String?,
    val trackCensoredName: String?,
    val collectionArtistId: Long?,
    val collectionArtistViewUrl: String?,
    val collectionViewUrl: String?,
    val trackViewUrl: String?,
    val previewUrl: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val artworkUrl100: String?,
    val collectionPrice: Double?,
    val trackPrice: Double?,
    val trackRentalPrice: Double?,
    val collectionHdPrice: Double?,
    val trackHdPrice: Double?,
    val trackHdRentalPrice: Double?,
    val releaseDate: String?,
    val collectionExplicitness: String?,
    val trackExplicitness: String?,
    val discCount: Long?,
    val discNumber: Long?,
    val trackCount: Long?,
    val trackNumber: Long?,
    val trackTimeMillis: Long?,
    val country: String,
    val currency: String,
    val primaryGenreName: String?,
    val contentAdvisoryRating: String?,
    val isStreamable: Boolean?,
    val description: String?,
    val longDescription: String?,
    val hasITunesExtras: Boolean?,
    val feedUrl: String?,
    val genreIds: List<String>?,
    val genres: List<String>?
)
