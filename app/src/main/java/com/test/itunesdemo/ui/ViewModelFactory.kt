package com.test.itunesdemo.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.itunesdemo.ui.home.HomeActivityViewModel
import com.test.itunesdemo.ui.trackdetails.TrackDetailsActivityViewModel

/**
 * ViewModel provider factory to instantiate our ViewModels.
 */
class ViewModelFactory(val app: Application, val id: Long? = 0) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeActivityViewModel::class.java) ->
                HomeActivityViewModel(app) as T
            modelClass.isAssignableFrom(TrackDetailsActivityViewModel::class.java) ->
                TrackDetailsActivityViewModel(app, id) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}