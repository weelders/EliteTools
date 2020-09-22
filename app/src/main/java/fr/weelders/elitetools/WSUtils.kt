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

fun getDistanceByNames(name1:String,name2:String): Double? {
    val json = sendGetOkHttpRequest("$URL_DISTANCE?name1=$name1&name2=$name2")
    val result = Gson().fromJson(json,Double::class.java)
    return result
}