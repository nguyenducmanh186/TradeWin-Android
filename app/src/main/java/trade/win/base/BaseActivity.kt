package trade.win.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    open fun showProgress() {
//   CustomProgressDialog.newInstance().progressDialogStart(context)
    }

    open fun dismissProgress() {
//   CustomProgressDialog.newInstance().progressDialogStop()
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
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

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