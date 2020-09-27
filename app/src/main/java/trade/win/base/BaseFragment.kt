package trade.win.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.JsonIOException
import com.google.gson.JsonSyntaxException
import es.dmoral.toasty.Toasty
import trade.win.App
import trade.win.R
import trade.win.help.CustomProgressDialog
import trade.win.login.LoginActivity
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class BaseFragment : Fragment() {

    fun findNavController(): NavController {
        return NavHostFragment.findNavController(this)
    }

    open fun showWarning(message: String){
        Toasty.warning(App.applicationContext(), message).show()
    }

    open fun showProgress() {
        CustomProgressDialog.newInstance().progressDialogStart(context)
    }

    open fun dismissProgress() {
        CustomProgressDialog.newInstance().progressDialogStop()
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

    open fun showError(error: String) {
        dismissProgress()
        Toasty.error(App.applicationContext(), error).show()
    }

    open fun showSuccess(error: String) {
        dismissProgress()
        Toasty.success(App.applicationContext(), error).show()
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

    open fun expireToken(context: Context){
        startActivity(Intent(context, LoginActivity::class.java))
        (context as Activity).finish()
    }


    open fun onBackPressed(): Boolean {
        return false
    }


}