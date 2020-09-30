package trade.win.webview

import android.app.Activity
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import kotlinx.android.synthetic.main.fragment_web_view.*
import kotlinx.android.synthetic.main.layout_header.*
import trade.win.App
import trade.win.R
import trade.win.authenticate.UserManager
import trade.win.base.BaseActivity
import trade.win.base.BaseFragment
import trade.win.base.BaseWebView
import trade.win.help.SharedPreferencesHelper
import trade.win.login.LoginActivity
import trade.win.login.LoginRespone
import java.net.URLDecoder
import java.net.URLEncoder

class WebviewFragment : BaseWebView() {
    private val baseURL = "https://trade.win/check-token-app/?token_parram="
    private var url = "home"
    private var header = "header"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString(URL, "home")!!
        header = arguments?.getString(TITLE)!!
    }
    override fun getWebView(): WebView {
        return webView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderToolbar.text = header
        initAction()
        initWebview()

    }

    private fun initAction() {
        btnBack.setOnClickListener {
            (context as Activity).onBackPressed()
        }
    }

    private fun initWebview() {
        val token = SharedPreferencesHelper(App.applicationContext()).getToken()
        val encode = URLEncoder.encode(token)

        Log.i("HHHHHHHHHHH", "encode: "+encode)

        Log.i("HHHHHHHHHHH", "token: "+token)

        loadUrl(baseURL + encode +"&url=$url", webView)
    }

    companion object {
        const val URL= "url"
        const val TITLE= "title"
        const val HOME = "home"
        const val ADD_SIGNAL = "add-signal"
        const val BALANCES = "balances"
        const val MEMBER_SHIP = "member-ship"
        const val STAKING = "staking"

    }


}