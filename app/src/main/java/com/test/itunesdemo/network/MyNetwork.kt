package com.test.itunesdemo.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.util.HashMap




/**
 * Main entry point for network access.
 * usage
 *
 * MyNetwork.itunes.search(...)
 *
 * @see com.test.itunesdemo.network.iTunesServices.search
 * @see com.test.itunesdemo.network.iTunesTestServices.search
 */
object MyNetwork {


    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }.build()

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    // main itunes api services available
    val itunes = retrofit.create(iTunesServices::class.java)

    // services for unit testing api
    val itunesTest = retrofit.create(iTunesTestServices::class.java)

}