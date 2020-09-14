package fr.weelders.elitetools

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

val client = OkHttpClient()

@Throws(Exception::class)
fun sendGetOkHttpRequest(url: String): String {
    println("url : $url")
    val request = Request.Builder().url(url).build()
    val response = client.newCall(request).execute()
    return if (response.code < 200 || response.code >= 300) {
        throw Exception(
            "Réponse du serveur incorrect : " +
                    response.code
        )
    } else {
        response.body?.string() ?: ""
    }
}

val MEDIA_TYPE_JSON = "application/json; charset=utf-8".toMediaType()

@Throws(Exception::class)
fun sendPostOkHttpRequest(url: String, paramJson: String): String {
    println("url : $url")
    val body = paramJson.toRequestBody(MEDIA_TYPE_JSON)
    val request = Request.Builder().url(url).post(body).build()
    val response = client.newCall(request).execute()
    return if (response.code < 200 || response.code >= 300) {
        throw Exception(
            "Réponse du serveur incorrect : " +
                    response.code
        )
    } else {
        response.body?.string() ?: ""
    }
}