package com.test.itunesdemo.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.itunesdemo.R
import com.test.itunesdemo.databinding.ActivityHomeBinding
import com.test.itunesdemo.db.toTrackShortModel
import com.test.itunesdemo.helpers.afterTextChangedDelayed
import com.test.itunesdemo.helpers.hideKeyboard
import com.test.itunesdemo.helpers.placeCursorToEnd
import com.test.itunesdemo.models.Track
import com.test.itunesdemo.models.TrackShort
import com.test.itunesdemo.ui.ViewModelFactory
import com.test.itunesdemo.ui.home.items.ItemTrack
import com.test.itunesdemo.ui.trackdetails.TrackDetailsActivity
import com.test.itunesdemo.visitable.TypeFactoryForRecycler
import com.test.itunesdemo.visitable.Visitable
import com.test.itunesdemo.visitable.VisitableAdapter


class HomeActivity : AppCompatActivity(), ItemTrack.Listener {


    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeActivityViewModel

    private lateinit var tracksAdapter: VisitableAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        // init viewmodel
        viewModel = ViewModelProvider(this, ViewModelFactory(this.application)).get(HomeActivityViewModel::class.java)

        // set lifecycleowner and vm on our view
        binding.lifecycleOwner = this
        binding.vm = viewModel

        // execute search after the value of edittext is changed
        // this is called when user paused typing after 1.5s
        binding.searchTextInput.afterTextChangedDelayed { term ->

            if(term.isEmpty()) return@afterTextChangedDelayed

            hideKeyboard()
            viewModel.search(
                term, "au", "all")
        }


        // setup recycler for track listing
        tracksAdapter = VisitableAdapter(TypeFactoryForRecycler(), this)
        binding.tracksRcv.apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = tracksAdapter
        }

        // go to TrackDetailsActivity if the user closed the app
        // while viewing the TrackDetails.
        viewModel.previouslyViewing.observe(this, Observer {
            it ?: return@Observer

            startActivity(
                Intent(this, TrackDetailsActivity::class.java)
                    .putExtra(TrackDetailsActivity.EXTRA_ID, it.id)
            )
        })

        // get all cached tracks and display on track listing recyclerview
        viewModel.tracks.observe(this, Observer { tracks ->
            tracks ?: return@Observer

            val list = arrayListOf<Visitable>()
            tracks.forEach { t ->
                list.add( ItemTrack.Compact( t ) )
            }

            tracksAdapter.updateList(list)

            // show or hide no result
            binding.noResultTxt.visibility = if(list.isEmpty()) View.VISIBLE else View.GONE
        })

        // display something when network error was encountered
        viewModel.networkError.observe(this, Observer {

            // show or hide error
            binding.errorMsgTxt.text = it
            binding.errorMsgTxt.visibility = if(it != null) View.VISIBLE else View.GONE
        })

        // display something when loading value is changed
        viewModel.networkLoading.observe(this, Observer {

            // show or hide loading animation
            binding.loadingLottie.visibility = if(it == true) View.VISIBLE else View.GONE
        })

    }


    override fun onTrackClicked(track: TrackShort) {
        // updated the selected track as currently user viewing
        viewModel.setTrackViewing(track.id, true)
    }


    override fun onResume() {
        super.onResume()

        // we are trying to autoload search when the user encountered error for the first time.
        // for ex user is searching while offline and tried to on wifi,
        // when the user comes back on the app we'll trigger the search function
        if(viewModel.networkError.value != null
            && binding.searchTextInput.text.toString().isNotEmpty()) {

            binding.searchTextInput.setText(
                binding.searchTextInput.text.toString()
            )

            binding.searchTextInput.placeCursorToEnd()
        }
    }

}