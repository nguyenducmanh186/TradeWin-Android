package trade.win.help

import java.util.*

object RandomHelper {
    private val ALLOWED_CHARACTERS = "123456789qwertyuipasdfghjklzxcvbnmQWERTYUIPASDFGHJKLZXCVBNM"
    fun getRandomString(): String {
        val sizeOfRandomString = 5
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        return sb.toString()
    }
}