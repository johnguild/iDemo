package com.test.itunesdemo.helpers

import com.test.itunesdemo.R
import java.util.*

/**
 * Helper functions that can be used on Any Object
 *
 * Mostly to reduce boilerplate on Track/TrackShort
 * needed functions
 */

/**
 * Retrieves the placeholder image of Track base
 * on the wrapperType and kind.
 *
 * Medias are movie, podcast, music, musicVideo, audiobook, shortFilm, tvShow, software, ebook, and default all
 * @see
 * https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching
 */
fun Any.getDefaultArtworkResourceId(wrapperType: String, kind: String): Int {

    return when {
        wrapperType == "audiobook" -> R.drawable.audiobook_logo
        kind == "song" -> R.drawable.music_logo
        kind == "feature-movie" -> R.drawable.movie_logo
        kind == "podcast" -> R.drawable.podcast_logo
        kind == "music-video" -> R.drawable.music_video_logo
        kind == "tv-episode" -> R.drawable.tv_logo
        kind == "software" -> R.drawable.software_logo
        kind == "ebook" -> R.drawable.ebook_logo
        else -> R.drawable.itunes_logo
    }

}

/**
 * Retrieves the proper display name
 * The requirement is to display TrackName BUT
 * some media type does not have TrackName like
 * audiobook, show artistName instead.
 */
fun Any.getDisplayName( trackName: String, artistName: String ): String {
    return if(trackName.isEmpty()) artistName else trackName
}

/**
 * Retrieves the proper pricing to display and format it with
 * currency symbol
 *
 * We can add more condition if we want to display
 * HD price, collection price, etc here.
 */
fun Any.getDisplayPrice( currency: String, trackPrice: Double ): String {
    val symbol = Currency.getInstance(currency).symbol.replace("A", "")
    return "$symbol $trackPrice"
}