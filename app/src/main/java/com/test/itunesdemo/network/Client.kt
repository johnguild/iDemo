package com.test.itunesdemo.network

import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File
import java.util.*
import java.util.Map

/**
 * Functions to convert any data type to RequestBody
 *
 * mainly used on POST body request
 *
 * @see okhttp3.RequestBody
 * @see retrofit2.http.POST
 */


fun toRequestBody(value: Long?): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), java.lang.Long.toString(value!!))
}

fun toRequestBody(value: Float?): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), java.lang.Float.toString(value!!))
}

fun toRequestBody(value: Double?): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), java.lang.Double.toString(value!!))
}

fun toRequestBody(value: Int): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), Integer.toString(value))
}

fun toRequestBody(string: String): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), string)
}

fun toRequestBody(jsonObject: JSONObject): RequestBody {
    return RequestBody.create(
        MediaType.parse("application/json; charset=utf-8"),
        jsonObject.toString()
    )
}

fun imageToRequestBody(file: File): RequestBody {
    return RequestBody.create(MediaType.parse("image/*"), file)
}

fun videoToRequestBody(file: File): RequestBody {
    return RequestBody.create(MediaType.parse("video/*"), file)
}


private fun getFileNameFormat(label: String, file: File): String {
    return label + "\"; filename=\"" + file.name + "\" "
}


/**
 * Transform a map of key value pair to
 * key RequestBody for POST requests body.
 */
fun paramsToRequestBody(params: HashMap<String, Any>): HashMap<String, RequestBody> {
    val fieldMap = HashMap<String, RequestBody>()
    val it = params.entries.iterator()
    while (it.hasNext()) {
        val pair = it.next() as Map.Entry<*, *>

        val key = pair.key.toString()
        if (pair.value is String) {
            fieldMap[key] = toRequestBody(pair.value.toString())
        } else if (pair.value is Int) {
            val `val` = pair.value as Int
            fieldMap[key] = toRequestBody(`val`)
        } else if (pair.value is Double) {
            val `val` = pair.value as Double
            fieldMap[key] = toRequestBody(`val`)
        } else if (pair.value is Long) {
            val `val` = pair.value as Long
            fieldMap[key] = toRequestBody(`val`)
        } else if (pair.value is JSONObject) {
            val `object` = pair.value as JSONObject
            fieldMap[key] = toRequestBody(`object`)
        } else if (pair.value is File) {
            val f = pair.value as File
            if (isVideo(f)) {
                fieldMap[getFileNameFormat(key, f)] = videoToRequestBody(f)
            } else {
                fieldMap[getFileNameFormat(key, f)] = imageToRequestBody(f)
            }

        }
        it.remove() // avoids a ConcurrentModificationException
    }
    return fieldMap
}


private fun isVideo(file: File): Boolean {
    val name = file.name
    return (name.contains(".mp4") || name.contains(".mp3") || name.contains(".mkv")
            || name.contains("wav") || name.contains("webm"))
}