package com.test.itunesdemo.ui.home.items

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.itunesdemo.R
import com.test.itunesdemo.helpers.getDefaultArtworkResourceId
import com.test.itunesdemo.helpers.getDisplayName
import com.test.itunesdemo.helpers.getDisplayPrice
import com.test.itunesdemo.models.TrackShort
import com.test.itunesdemo.visitable.AbstractBetterViewHolder
import com.test.itunesdemo.visitable.TypeFactory
import com.test.itunesdemo.visitable.Visitable
import com.test.itunesdemo.visitable.VisitableListener
import java.util.*

/**
 * Item represents Track instance on listings.
 * Contains all user ui related actions and display.
 */
class ItemTrack(itemView: View, listener: VisitableListener) :
    AbstractBetterViewHolder(itemView) {

    private lateinit var compact: Compact
    private var itemListener: Listener

    private val artworkImg = itemView.findViewById<ImageView>(R.id.artwork_img)
    private val mediaImg = itemView.findViewById<ImageView>(R.id.media_img)
    private val trackNameTxt = itemView.findViewById<TextView>(R.id.track_name_txt)
    private val genreTxt = itemView.findViewById<TextView>(R.id.genre_txt)
    private val priceTxt = itemView.findViewById<TextView>(R.id.price_txt)

    init {

        // throws error when listener was not implemented
        // either on Activity/Fragment where the list item
        // is supposed to be shown
        try {
            itemListener = listener as Listener
        } catch (e: ClassCastException) {
            throw RuntimeException(
                "Passed listener must implement ${ItemTrack::class.simpleName}.Listener"
            )
        }

        // trigger onTrackClicked when item parent is clicked
        itemView.rootView.setOnClickListener {
            itemListener.onTrackClicked(compact.track)
        }


    }

    /**
     * Update ui details/images for Track item on listing
     */
    override fun bind(element: Visitable) {
        try {
            compact = element as Compact
        } catch (e: ClassCastException) {
            throw RuntimeException(
                "Bonded element is not an instance of ${Compact::class.simpleName}"
            )
        }

        Glide.with(itemView.context)
            .load(compact.track.getBestArtwork())
            .apply(
                RequestOptions()
                    .optionalCenterCrop()
                    .placeholder(
                        getDefaultArtworkResourceId(
                            compact.track.wrapperType,
                            compact.track.kind
                        )
                    )
            )
            .into(artworkImg)

        mediaImg.setImageResource(
            getDefaultArtworkResourceId(
                compact.track.wrapperType,
                compact.track.kind
            )
        )

        trackNameTxt.text = getDisplayName(
            compact.track.trackName,
            compact.track.artistName
        )

        genreTxt.text = compact.track.primaryGenreName


        priceTxt.text = getDisplayPrice(
            compact.track.currency,
            compact.track.trackPrice
        )

    }

    /**
     * Contains all user possible actions for
     * the item. Can be viewing,options, etc.
     */
    interface Listener : VisitableListener {
        fun onTrackClicked(track: TrackShort)
    }

    /**
     * Resource layout file for this item.
     */
    companion object {
        @LayoutRes
        const val LAYOUT = R.layout.list_item_track
    }

    /**
     * ViewModel needed for this item.
     */
    open class Compact(
        val track: TrackShort
    ) : Visitable {

        override fun type(typeFactory: TypeFactory): Int {
            return typeFactory.type(this)
        }
    }


}