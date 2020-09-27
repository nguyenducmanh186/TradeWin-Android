package trade.win.help

import android.text.TextUtils
import android.util.Patterns

object ValidateEmail {

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}