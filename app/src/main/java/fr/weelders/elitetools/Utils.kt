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

class MyBounceInterpolator(val amplitude:Double, val frequency:Double): android.view.animation.Interpolator {
    val mAmplitude = amplitude
    val mFrequency = frequency


    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(
            Math.E,
            -time / mAmplitude
        ) * Math.cos(mFrequency * time) + 1).toFloat()
    }
}
