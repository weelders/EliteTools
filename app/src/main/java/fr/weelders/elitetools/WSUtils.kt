package fr.weelders.elitetools

import com.google.gson.Gson

fun getSystem(nameSystem: String): Any {
    val json = sendGetOkHttpRequest("$URL_SYSTEM?name=$nameSystem")
    try {
        return Gson().fromJson(json, Array<ComplexeStations>::class.java).toList()
    } catch (e: Exception) {
        return json
    }
}

fun getSystemName(): Array<String>? {
    val json = sendGetOkHttpRequest(URL_SYSTEM_NAMES)
    val result = Gson().fromJson(json, Array<String>::class.java)
    return result
}

fun getDistanceByNames(name1: String, name2: String): Any {
    val json = sendGetOkHttpRequest("$URL_DISTANCE?name1=$name1&name2=$name2")
    try {
        return Gson().fromJson(json, Double::class.java)
    } catch (e: Exception) {
        return json
    }
}

fun getShips(name: String, distance: Int, ship: String): Any {
    val json = sendGetOkHttpRequest("$URL_SHIPS?name=$name&distance=$distance&ship=$ship")
    try {
        return Gson().fromJson(json, Array<ComplexeStations>::class.java).toList()
    } catch (e: Exception) {
        return json
    }
}

fun getGalnetNews(): GalnetNews? {
    val json = sendGetOkHttpRequest(URL_GALNET)
    return Gson().fromJson(json,GalnetNews::class.java)
}