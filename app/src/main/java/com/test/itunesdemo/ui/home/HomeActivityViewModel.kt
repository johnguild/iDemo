package com.test.itunesdemo.ui.home

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


class HomeActivityViewModel(application: Application): ViewModel(){

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

    val searchRepository = SearchRepository(getDatabase(application))

    val tracks = searchRepository.tracks

    val previouslyViewing = searchRepository.viewingTrack


    /**
     * Value changes while or after doing network related
     * actions such as server request.
     */
    private val _networkLoading = MutableLiveData<Boolean?>()
    val networkLoading = _networkLoading

    /**
     * Value changes when network issues encountered while
     * doing network related actions such as server request.
     */
    private val _networkError = MutableLiveData<String?>()
    val networkError = _networkError

    /**
     * Search for track list data using search repo
     *
     * @param term
     * @param country
     * @param media
     */
    fun search( term: String, country: String, media: String ) {

        viewModelScope.launch {
            _networkLoading.value = true
            _networkError.value = null

            try {
                searchRepository.search(
                    term, country, media
                )
            } catch (networkError: IOException){
//                _networkError.value = networkError.toString()
                _networkError.value = "Network Error."
            } catch (networkError: HttpException) {
//                _networkError.value = networkError.toString()
                _networkError.value = "Something went wrong while loading content."
            } catch (error: Exception){
//                _networkError.value = error.toString()
                _networkError.value = "Oops! Error encountered."
            } finally {
                _networkLoading.value = null
            }

        }
    }

    /**
     * updates DatabaseTrack's userViewing column value
     * using search repo.
     */
    fun setTrackViewing( id: Long, bool: Boolean ) {
        viewModelScope.launch {
            searchRepository.setTrackViewing(id, bool)
        }
    }


}