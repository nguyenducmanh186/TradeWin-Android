package trade.win.authenticate

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import trade.win.help.SharedPreferencesHelper

class UserManager (val context: Context) {
    private val accountManager: AccountManager = AccountManager.get(context)

    fun addAccount(phone: String, password: String, token: String){
        val data = Bundle().apply { this.putString(EMAIL, phone) }
            .apply { this.putString(PASSWORD, password) }
            .apply { this.putString(TOKEN, token) }

        val account = Account(phone,ACCOUNT_TYPE )
        accountManager.addAccountExplicitly(account, password, data)
        accountManager.setAuthToken(account, AUTH_TOKEN_TYPE, token )

    }

    private fun getAccount(): Account {
        return accountManager.getAccountsByType(ACCOUNT_TYPE)[0]
    }

    fun logout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            SharedPreferencesHelper(context).clearUserLogin()
            accountManager.removeAccountExplicitly(getAccount())
        }
    }

    fun isLogged(): Boolean {
        val accounts = accountManager.getAccountsByType(ACCOUNT_TYPE)
        if (accounts.isNotEmpty()) {
            return true
        }
        return false
    }


    fun getToken(isOrigin: Boolean = false): String {
        return if (isOrigin) accountManager.getUserData(
            getAccount(),
            TOKEN
        ) else TOKEN_PREFIX + accountManager.getUserData(getAccount(), TOKEN)
    }

    fun getToken(): String {
        return accountManager.getUserData(getAccount(), TOKEN)
    }

    companion object {
        const val AUTH_TOKEN_TYPE = "heinekenbrewerytour.vn.spo"
        const val ACCOUNT_TYPE = "heineken-spo"
        const val EMAIL= "phone"
        const val PASSWORD ="password"
        const val TOKEN = "token"
        const val ACCOUNT_ROLE = "account-role"
        const val TOKEN_PREFIX = "Bearer "
        const val DEVICE_ID ="device"

        @Volatile
        private var instance: UserManager? = null

        fun getInstance(context: Context): UserManager {
            return instance ?: synchronized(this) {
                instance ?: UserManager(context)
            }
        }

        fun init(context: Context) {
            instance ?: synchronized(this) {
                instance ?: UserManager(context)
            }
        }


    }
}