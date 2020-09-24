package trade.win.account

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.layout_header.*
import trade.win.App
import trade.win.R
import trade.win.authenticate.UserManager
import trade.win.base.BaseFragment
import trade.win.help.SharedPreferencesHelper
import trade.win.login.LoginActivity

class AccountFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderToolbar.text = getString(R.string.header_account_manager)
        btnLogout.visibility = View.VISIBLE
        initAction()
        initData()
    }

    private fun initData() {
        if (SharedPreferencesHelper(App.applicationContext()).getLoginData()!= null){
            val loginData = SharedPreferencesHelper(App.applicationContext()).getLoginData()!!
            txtHello.text = "Hello: "+ UserManager.getInstance(App.applicationContext()).getEmail()
            txtAllSignal.text = loginData.allsignal.toString()
            txtRunning.text = loginData.signalrunning.toString()
            txtDoneSell.text = loginData.signaldonesell.toString()
            txtStoploss.text = loginData.signalstoploss.toString()

            txtBonus.text = loginData.totalref ?: ""
            txtDonate.text = loginData.totaldonate ?: ""

            txtVNToken.text = loginData.vnt.toString() + " VN"
            txtTWTokenAvailable.text = loginData.twtoken_cash.toString() + " TW"
            txtTWTokenLocked.text = loginData.twtoken.toString() + " TW"
        }
    }

    private fun initAction() {

        btnBack.setOnClickListener {
            (context as Activity).onBackPressed()
        }
        btnLogout.setOnClickListener {
            UserManager.getInstance(App.applicationContext()).logout()
            startActivity(Intent(App.applicationContext(), LoginActivity::class.java))
            (context as Activity).finish()
        }
    }
}