package trade.win

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.contains
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import trade.win.account.AccountFragment
import trade.win.login.LoginActivity
import trade.win.login.LoginRespone
import trade.win.notification.NotificationFragment
import trade.win.webview.WebviewFragment


class MainActivity : AppCompatActivity() {
    private var loginRespone : LoginRespone ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        if (intent.extras!= null){
//            if (intent.extras!![LoginActivity.LOGIN_REPONSE]!= null){
//                loginRespone = intent.extras!![LoginActivity.LOGIN_REPONSE] as LoginRespone
//            }
//        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}