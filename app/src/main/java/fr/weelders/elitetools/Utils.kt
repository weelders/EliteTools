package fr.weelders.elitetools

import android.content.Context

//Check if the userInput is ok
fun userInputCheck(userInput: String, context: Context): String {
    val usableInput = userInput.toLowerCase().trim()

    if (usableInput.isNullOrBlank()) {
        throw UserInputException(context.getString(R.string.userInputEx_default))
    }
    return usableInput
}

//Take String and return String with each first char in Uppercase
fun makeFistCharCapital(txt: String): String {
    var returnString = ""
    if (txt.isNullOrBlank()) {
        return returnString
    } else {
        val listString = txt.split(" ")
        listString.forEach {
            returnString += it.replaceFirst(it[0], it[0].toUpperCase()) + " "
        }
        return returnString
    }
}