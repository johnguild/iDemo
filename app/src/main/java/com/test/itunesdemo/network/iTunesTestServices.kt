package com.test.itunesdemo.network

import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.PartMap
import retrofit2.http.QueryMap


/**
 * A Test service for itunes api
 */
interface iTunesTestServices {


    @POST("search")
    fun search(@QueryMap params: HashMap<String, Any>): Call<NetworkSearchResponse>

}