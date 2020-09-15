package trade.win.webview

import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import kotlinx.android.synthetic.main.fragment_web_view.*
import trade.win.R
import trade.win.base.BaseActivity
import trade.win.base.BaseFragment
import trade.win.base.BaseWebView
import trade.win.login.LoginActivity
import trade.win.login.LoginRespone

class WebviewFragment : BaseFragment(){

    lateinit var loginRespone: LoginRespone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.getSerializable(LOGIN_RESPONSE)!= null){
            loginRespone = arguments?.getSerializable(LOGIN_RESPONSE) as LoginRespone
        }
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

        webView.loadUrl("http://trade.win/test-token/?token_parram="+loginRespone.token_parram)

        webView.settings.javaScriptEnabled = true
        webView.settings.setGeolocationEnabled(true)
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
//        webView.webViewClient = BaseWebView.GeoWebViewClient()

        webView.settings.setAppCacheEnabled(true)
        webView.settings.databaseEnabled = true
        webView.settings.domStorageEnabled = true
        //webViewTermsOfUse.settings.setGeolocationDatabasePath(filesDir.path)
//        webView.webChromeClient = BaseWebView.GeoWebChromeClient()

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
                return true
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)

            }


            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

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