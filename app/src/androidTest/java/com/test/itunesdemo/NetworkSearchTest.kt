package com.test.itunesdemo

import android.util.Log
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.test.itunesdemo.network.MyNetwork
import com.test.itunesdemo.network.paramsToRequestBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.HttpException
import java.io.IOException

@RunWith(AndroidJUnit4ClassRunner::class)
class NetworkSearchTest {


    private val TAG = NetworkSearchTest::class.simpleName
    private lateinit var term: String
    private lateinit var country: String
    private lateinit var media: String
    private var limit: Long = 20

    @Before
    fun initializeParams() {
        term = "star"
        country = "au"
        media = "all"
    }


    @Test
    fun searchTest() {

        val params = HashMap<String, Any>()
        params["term"] = term
        params["country"] = country
        params["media"] = media
        params["limit"] = limit

        try {
            val response = MyNetwork.itunesTest.search( params ).execute()

            Log.i(TAG, response.body().toString())

        } catch (networkError: IOException){
            Log.i(TAG, networkError.toString())
        } catch (networkError: HttpException) {
            Log.i(TAG, networkError.toString())
        } catch (error: Exception){
            Log.i(TAG, error.toString())
        }



    }

}