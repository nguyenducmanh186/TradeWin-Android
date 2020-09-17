package trade.win.base

import android.net.http.SslError
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*

abstract class BaseWebView : BaseFragment(){
    abstract fun getWebView(): WebView

    /**
     * WebViewClient subclass loads all hyperlinks in the existing WebView
     */
    inner class GeoWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            // When user clicks a hyperlink, load in the existing WebView
            view.loadUrl(url)
            return true
        }
    }

    /**
     * WebChromeClient subclass handles UI-related calls
     * Note: think chrome as in decoration, not the Chrome browser
     */
    inner class GeoWebChromeClient : WebChromeClient() {
        override fun onGeolocationPermissionsShowPrompt(
            origin: String,
            callback: GeolocationPermissions.Callback
        ) {
            // Always grant permission since the app itself requires location
            // permission and the user has therefore already granted it
            callback.invoke(origin, true, false)
        }
    }


    fun loadUrl(url: String, webView: WebView) {

        // load pdf from google docs
        // "https://docs.google.com/gview?embedded=true&url="

        if (isConnectedInternet(context!!)) {
            var isLoading = true
//            showProgress()

            webView.settings.javaScriptEnabled = true
            webView.settings.setGeolocationEnabled(true)
            webView.settings.javaScriptCanOpenWindowsAutomatically = true
            webView.webViewClient = GeoWebViewClient()

            webView.settings.setAppCacheEnabled(true)
            webView.settings.databaseEnabled = true
            webView.settings.domStorageEnabled = true
            //webViewTermsOfUse.settings.setGeolocationDatabasePath(filesDir.path)
            webView.webChromeClient = GeoWebChromeClient()

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
                    shouldOverrideUrlLoading(url)
                    return true
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    super.onReceivedError(view, request, error)
                    if (isLoading) {
//                        dismissProgress()
                        isLoading = false
                    }
                }


                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (isLoading) {
                        onPageFinished(url)
//                        dismissProgress()
                        isLoading = false
                    }
                }

            }

            webView.loadUrl(url)
            webView.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()
                    return@OnKeyListener true
                }
                false
            })

        } else {
//            showErrorNetwork()
        }

    }

    open fun onPageFinished(url: String?) {
        Log.i("onPageFinished", url)
    }

    open fun shouldOverrideUrlLoading(url: String?){
        Log.i("shouldOverrideUrl", url)
    }


    override fun onBackPressed(): Boolean {
        val webView = getWebView()
        return if (webView.canGoBack()) {
            webView.goBack()
            true
        } else {
            activity?.onBackPressed()
            false
        }
    }

}