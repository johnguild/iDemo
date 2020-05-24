package com.test.itunesdemo.ui.trackdetails

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.itunesdemo.db.getDatabase
import com.test.itunesdemo.models.TrackShort
import com.test.itunesdemo.repositories.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception


class TrackDetailsActivityViewModel(application: Application, id: Long?): ViewModel(){

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    /**
     * Cancel existing jobs when this instance is destroyed
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    val searchRepository = SearchRepository(getDatabase(application), id)

    val track = searchRepository.track


    /**
     * updates DatabaseTrack's userViewing column value
     * using search repo.
     */
    fun setAsViewing( bool: Boolean ) {
        viewModelScope.launch {
            searchRepository.setTrackViewing(track.value!!.id, bool)
        }
    }
}