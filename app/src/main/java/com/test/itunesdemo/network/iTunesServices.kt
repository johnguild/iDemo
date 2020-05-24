package com.test.itunesdemo.network

import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.PartMap
import retrofit2.http.QueryMap


/**
 * A retrofit service for iTunes API
 *
 * @see
 * https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching
 */
interface iTunesServices {


    @POST("search")
    fun search(@QueryMap params: HashMap<String, Any>): Deferred<NetworkSearchResponse>

}