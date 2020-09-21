package fr.weelders.elitetools

import com.google.gson.Gson

fun getSystem(nameSystem: String): ArrayList<Docs> {
    val json = sendGetOkHttpRequest(URL_SYSTEM + nameSystem)
    val result = Gson().fromJson(json, SystemResponse::class.java)
    return result.docs
}

fun getSystemName(): Array<String>? {
    val json = sendGetOkHttpRequest(URL_SYSTEM_NAMES)
    val result = Gson().fromJson(json,Array<String>::class.java)
    return result
}