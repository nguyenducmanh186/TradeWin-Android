package trade.win

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import trade.win.authenticate.UserManager
import trade.win.help.SharedPreferencesHelper
import trade.win.login.LoginActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isRemember = SharedPreferencesHelper(this).isRememberLogin()
        Log.i("MMMMMMMM", isRemember.toString())
        if (isRemember){
            if (UserManager.getInstance(this).isLogged()){
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        } else {
            if (UserManager.getInstance(this).isLogged()){
                UserManager.getInstance(this).logout()
            }
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}