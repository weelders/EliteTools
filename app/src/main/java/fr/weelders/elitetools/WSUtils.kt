package fr.weelders.elitetools

import com.google.gson.Gson

fun getSystem(nameSystem: String): ArrayList<Docs> {
    val json = sendGetOkHttpRequest(URL_SYSTEM + nameSystem)
    val result = Gson().fromJson(json, SystemResponse::class.java)
    return result.docs
}