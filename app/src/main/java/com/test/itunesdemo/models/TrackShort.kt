package com.test.itunesdemo.models

/**
 * Short version of Track to use on listing
 * contains only necessary data for the list item.
 *
 * @see com.test.itunesdemo.ui.home.items.ItemTrack
 */
data class TrackShort (
    val id: Long,
    val wrapperType: String,
    val kind: String,
    val trackName: String,
    val artistName: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val artworkUrl100: String,
    val trackPrice: Double,
    val trackRentalPrice: Double,
    val collectionHdPrice: Double,
    val trackHdPrice: Double,
    val trackHdRentalPrice: Double,
    val primaryGenreName: String,
    val currency: String
)  {

    /**
     * Retrieves the best quality artwork available
     */
    fun getBestArtwork(): String {
        return when {
            artworkUrl100.isNotEmpty() -> artworkUrl100
            artworkUrl60.isNotEmpty() -> artworkUrl60
            else -> artworkUrl30
        }
    }
}
