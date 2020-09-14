package fr.weelders.elitetools

import android.content.Context


fun userInputCheck(userInput: String, context: Context): String {
    val usableInput = userInput.toLowerCase().trim()

    if (usableInput.isNullOrBlank()) {
        throw UserInputException(context.getString(R.string.userInputEx_default))
    }
    return usableInput
}