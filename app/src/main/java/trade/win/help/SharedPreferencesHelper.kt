package trade.win.help

import android.content.Context

class SharedPreferencesHelper(val context: Context) {
    private val SAVE_INIT = "save-init"
    private val TOKEN = "user-info"


    fun inputToken(version: String){
        val preferences =
            context.getSharedPreferences(SAVE_INIT, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(TOKEN, version)
        editor.apply()
    }

    fun getToken(): String {
        var version = ""
        val preferences =
            context.getSharedPreferences(SAVE_INIT, Context.MODE_PRIVATE)
        if (preferences.contains(TOKEN)) {
            version = preferences.getString(TOKEN, "").toString()
        }
        return version
    }
}