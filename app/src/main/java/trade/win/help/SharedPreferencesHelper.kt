package trade.win.help

import android.content.Context
import com.google.gson.GsonBuilder
import trade.win.login.LoginRespone

class SharedPreferencesHelper(val context: Context) {
    private val SAVE_INIT = "save-init"
    private val TOKEN = "user-info"
    private val REMEMBER_LOGIN = "remember-login"
    private val LOGIN_DATA = "login-data"


    fun inputToken(version: String) {
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

    fun saveRememberLogin(value: Boolean) {
        val preferences =
            context.getSharedPreferences(SAVE_INIT, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(REMEMBER_LOGIN, value)
        editor.apply()
    }

    fun isRememberLogin(): Boolean {
        var version = false
        val preferences =
            context.getSharedPreferences(SAVE_INIT, Context.MODE_PRIVATE)
        if (preferences.contains(REMEMBER_LOGIN)) {
            version = preferences.getBoolean(REMEMBER_LOGIN, false)
        }
        return version
    }

    fun saveLoginData(loginRespone: LoginRespone) {
        val preferences =
            context.getSharedPreferences(SAVE_INIT, Context.MODE_PRIVATE)
        val gson = GsonBuilder().setPrettyPrinting().create()
        val loginData = gson.toJson(loginRespone)
        val editor = preferences.edit()
        editor.putString(LOGIN_DATA, loginData)
        editor.apply()
    }

    fun getLoginData(): LoginRespone? {
        var version: LoginRespone? = null
        val preferences =
            context.getSharedPreferences(SAVE_INIT, Context.MODE_PRIVATE)
        if (preferences.contains(LOGIN_DATA)) {
            val stringData = preferences.getString(LOGIN_DATA, "")
            version = GsonBuilder().setLenient().create().fromJson(stringData, LoginRespone::class.java)
        }
        return version
    }

    fun clearUserLogin() {
        val preferences =
            context.getSharedPreferences(SAVE_INIT, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(REMEMBER_LOGIN)
        editor.remove(TOKEN)
        editor.remove(LOGIN_DATA)
        editor.commit()
    }
}