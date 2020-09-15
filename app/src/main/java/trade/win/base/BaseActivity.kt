package trade.win.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import es.dmoral.toasty.Toasty
import trade.win.R
import trade.win.help.CustomProgressDialog
import java.io.FileNotFoundException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class BaseActivity : AppCompatActivity() {
    open fun showProgress() {
        CustomProgressDialog.newInstance().progressDialogStart(this)
    }

    open fun dismissProgress() {
        CustomProgressDialog.newInstance().progressDialogStop()
    }

    open fun showError(error: String) {
        dismissProgress()
        Toasty.error(this, error).show()
    }

    open fun showOnFailureException(t: Throwable) {
        dismissProgress()
        if (t is TimeoutException || t is SocketTimeoutException) {
            showError(getString(R.string.error_time_out))
        } else if (t is JsonIOException || t is JsonSyntaxException) {
            showError(getString(R.string.error_json_format))
        } else if (t is UnknownHostException || t is ConnectException) {
            showError(getString(R.string.check_network))
        }else {
            showError(t.message.toString())
        }
    }

    open fun isConnectedInternet(context: Context): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val infos = connectivityManager.allNetworkInfo
            if (infos != null) {
                for (i in infos.indices) {
                    if (infos[i].state == NetworkInfo.State.CONNECTED)
                        return true
                }
            }
        }
        return false
    }


    open fun isConnectedInternet(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivityManager != null) {
            val infos = connectivityManager.allNetworkInfo
            if (infos != null) {
                for (i in infos.indices) {
                    if (infos[i].state == NetworkInfo.State.CONNECTED)
                        return true
                }
            }
        }
        return false
    }

}