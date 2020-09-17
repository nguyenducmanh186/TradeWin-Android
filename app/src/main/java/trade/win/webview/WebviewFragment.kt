package trade.win.webview

import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import kotlinx.android.synthetic.main.fragment_web_view.*
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

class WebviewFragment : BaseFragment(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()
        val token =SharedPreferencesHelper(context!!).getToken()
        val encode = URLEncoder.encode(token)
//        Log.i("LLLLLLLLLL", "encode: "+encode)
        webView.loadUrl("http://trade.win/test-token/?token_parram="+encode)

        val decode = URLDecoder.decode(encode)
//        Log.i("LLLLLLLLLL", "decode: "+ decode)

        showWarning("endcode: "+ encode)
        showWarning("token: "+ token)



        webView.settings.javaScriptEnabled = true
        webView.settings.setGeolocationEnabled(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
//        webView.webViewClient = BaseWebView.GeoWebViewClient()

        webView.settings.setAppCacheEnabled(true)
        webView.settings.databaseEnabled = true
        webView.settings.domStorageEnabled = true
        //webViewTermsOfUse.settings.setGeolocationDatabasePath(filesDir.path)
//        webView.webChromeClient = BaseWebView.GeoWebChromeClient()

        var count = 0


        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                Log.i("LLLLLLL", "shouldOverrideUrlLoading")
                return true
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                dismissProgress()
            }


            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                count++
                Log.i("LLLLLLL", "onPageFinished")
                if (count == 2){
                    dismissProgress()
                }


            }

        }
    }


    companion object{
        const val LOGIN_RESPONSE = "login"
        fun newIntance(loginRespone: LoginRespone?): WebviewFragment{
            val fragment = WebviewFragment()
            val bundle = Bundle()
            bundle.putSerializable(LOGIN_RESPONSE, loginRespone)
            fragment.arguments = bundle
            return fragment
        }
    }
}