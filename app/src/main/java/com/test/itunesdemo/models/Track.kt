package com.test.itunesdemo.models

import android.text.Html
import com.test.itunesdemo.R
import com.test.itunesdemo.helpers.getDisplayName
import com.test.itunesdemo.helpers.getDisplayPrice

/**
 * Model representing details Track instance
 * mainly to use on TrackDetails view
 *
 * @see com.test.itunesdemo.ui.trackdetails.TrackDetailsActivity
 */
data class Track (
    val id: Long,
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
) {

    /**
     * Retrieves the best quality artwork available
     *
     * @return String of artwork url
     */
    fun getBestArtwork(): String {
        return when {
            artworkUrl100.isNotEmpty() -> artworkUrl100
            artworkUrl60.isNotEmpty() -> artworkUrl60
            else -> artworkUrl30
        }
    }

    /**
     * Retrieves the proper title
     *
     * @see com.test.itunesdemo.helpers.getDisplayName
     * @return String of formatted title
     */
    fun getShownTitle(): String {
        return getDisplayName(trackName, artistName)
    }

    /**
     * Retrieves the proper pricing
     *
     * @see com.test.itunesdemo.helpers.getDisplayPrice
     * @return String of formatted price
     */
    fun getShownPrice(): String {
        return getDisplayPrice(currency, trackPrice)
    }

    /**
     * Retrieves from description or longdescription
     * if available, default to (No Description Available)
     *
     * @return String of description
     */
    fun getShownDescription(): String {
        var desc = when {
            description.isNotEmpty() -> description
            longDescription.isNotEmpty() -> longDescription
            else -> "(No Description Available)"
        }

        return Html.fromHtml(desc).toString()
    }
}
