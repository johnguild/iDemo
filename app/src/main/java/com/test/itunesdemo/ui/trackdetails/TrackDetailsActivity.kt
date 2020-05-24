package com.test.itunesdemo.ui.trackdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.itunesdemo.R
import com.test.itunesdemo.databinding.ActivityTrackDetailsBinding
import com.test.itunesdemo.helpers.getDefaultArtworkResourceId
import com.test.itunesdemo.helpers.getDisplayName
import com.test.itunesdemo.helpers.getDisplayPrice
import com.test.itunesdemo.ui.ViewModelFactory
import com.test.itunesdemo.visitable.VisitableAdapter


class TrackDetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityTrackDetailsBinding
    private lateinit var viewModel: TrackDetailsActivityViewModel

    private var isLeaving: Boolean = false

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_track_details)

        // retrieves id of DatabaseTrack we are viewing
        val id = intent.extras?.getLong(EXTRA_ID) ?: 0

        // init viewmodel
        viewModel = ViewModelProvider(this, ViewModelFactory(this.application, id)).get(TrackDetailsActivityViewModel::class.java)

        // set lifecycleowner and vm on our view
        binding.lifecycleOwner = this
        binding.vm = viewModel

        // retrieve viewing full track data from Database
        viewModel.track.observe(this, Observer { track ->
            track ?: return@Observer

            Glide.with(applicationContext)
                .load(track.getBestArtwork())
                .apply(
                    RequestOptions()
                        .optionalCenterCrop()
                        .placeholder(
                            getDefaultArtworkResourceId(
                                track.wrapperType,
                                track.kind
                            )
                        )
                )
                .into(binding.artworkImg)

            // update the userViewing to true if not setted yet.
            if(!track.userViewing && !isLeaving) {
                viewModel.setAsViewing( true )
                return@Observer
            }

            // if userViewing was changed to false exit this Activity.
            if(!track.userViewing) {
                finish()
            }

        })

    }


    override fun onBackPressed() {
//        super.onBackPressed()

        // update userViewing to false instead.
        // the Activity observes it and exits when set to false.
        isLeaving = true
        viewModel.setAsViewing(false)
    }



}