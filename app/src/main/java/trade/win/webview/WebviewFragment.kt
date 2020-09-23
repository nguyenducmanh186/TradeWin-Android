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
    private val baseURL = "http://trade.win/test-token/?token_parram="

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun getWebView(): WebView {
        return webView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeaderToolbar.text = getString(R.string.header_trade_win)
        initAction()
        initWebview()
        pullToReFreshLayout()

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

        loadUrl(baseURL + encode, webView)
    }

    private fun pullToReFreshLayout() {
        swipeWebView.setColorSchemeResources(
            R.color.colorPrimary,
            android.R.color.holo_green_dark,
            android.R.color.holo_orange_dark,
            android.R.color.holo_blue_dark
        )

        swipeWebView.setOnRefreshListener {
            initWebview()
            Handler().postDelayed ({
                swipeWebView.isRefreshing = false
            }, 3000)
        }


    }

}