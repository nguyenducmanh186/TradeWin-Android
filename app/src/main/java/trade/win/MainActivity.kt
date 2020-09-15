package trade.win

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import trade.win.login.LoginActivity
import trade.win.login.LoginRespone
import trade.win.webview.WebviewFragment


class MainActivity : AppCompatActivity() {
    private var loginRespone : LoginRespone ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (intent.extras!= null){
            if (intent.extras!![LoginActivity.LOGIN_REPONSE]!= null){
                loginRespone = intent.extras!![LoginActivity.LOGIN_REPONSE] as LoginRespone
            }
        }
        bottomNavi.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }

    private val mOnNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var fragment: Fragment
                when (item.getItemId()) {
                    R.id.nav_notification -> {
                        return true
                    }
                    R.id.nav_reports -> {
                        loadFragment(WebviewFragment.newIntance(loginRespone))
                        return true
                    }
                    R.id.nav_account -> {
                        return true
                    }

                }
                return false
            }
        }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}